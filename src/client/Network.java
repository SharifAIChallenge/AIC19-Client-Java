package client;

import common.network.JsonSocket;
import common.network.data.Message;
import common.util.Log;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.Consumer;

/**
 * {@link Network} is responsible for connecting to server, and
 * sending/receiving messages to/from server.
 * Please do not change this class, it is a piece of the internal implementation
 * and you do not need to know anything about this class.
 */
public class Network {

    // Logging tag
    private static final String TAG = "Network";

    // Maximum number of exceptions could occur during connection. After that client will be closed.
    public static final int MAX_NUM_EXCEPTIONS = 20;

    // Handles incoming messages
    private Consumer<Message> messageHandler;

    // Send queue
    private LinkedBlockingDeque<Message> messagesToSend;

    // Connection details
    private int port;
    private String host;
    private String token;

    // Client socket
    private JsonSocket socket;

    // Connection flag
    private boolean isConnected;

    // Executor used to receive messages
    private ExecutorService executor;

    // Termination flag
    private boolean terminateFlag;

    // Number of exceptions occurred during communication
    private int numOfExceptions;


    /**
     * Constructor.
     *
     * @param messageHandler handles incoming messages
     */
    public Network(Consumer<Message> messageHandler) {
        this.messageHandler = messageHandler;
        messagesToSend = new LinkedBlockingDeque<>();
        executor = Executors.newCachedThreadPool();
        startSending();
    }

    /**
     * Setter for connection details.
     *
     * @param host  server's host (ip)
     * @param port  server's port
     * @param token client's token
     */
    public void setConnectionData(String host, int port, String token) {
        this.host = host;
        this.port = port;
        this.token = token;
    }

    /**
     * Tries to connect and send token to the server.
     */
    public void connect() {
        isConnected = false;
        JsonSocket client;
        Message init;
        try {
            client = new JsonSocket(host, port);
            client.send(new Message("token", token));
            init = client.get(Message.class);
            if (!init.name.equals("init")) {
                client.close();
                throw new Exception("First message of the server was not init message.");
            }
        } catch (IOException e) {
            Log.e(TAG, "Can not connect to server.", e);
            handleIOE(e);
            return;
        } catch (Exception e) {
            Log.e(TAG, "Can not connect to server.");
            return;
        }
        isConnected = true;
        this.socket = client;
        messageHandler.accept(init);
        startReceiving();
    }

    /**
     * Starts listening for the server messages.
     */
    private void startReceiving() {
        executor.submit(() -> {
            while (!terminateFlag)
                doReceive();
            executor.shutdownNow();
            executor = null;
        });
    }

    /**
     * Listens for a single message of the server.
     */
    private void doReceive() {
        try {
            messageHandler.accept(socket.get(Message.class));
        } catch (IOException e) {
            Log.e(TAG, "Can not receive server's message.", e);
            e.printStackTrace();
            handleIOE(e);
        } catch (Exception e) {
            Log.e(TAG, "Can not recieve server's message.", e);
            e.printStackTrace();
        }
    }

    /**
     * Starts sending messages to server.
     */
    private void startSending() {
        executor.submit(() -> {
            while (!terminateFlag) {
                try {
                    Message msg = messagesToSend.take();
                    socket.send(msg);
                } catch (InterruptedException ignored) {
                } catch (IOException e) {
                    Log.e(TAG, "Can not send client's message.");
                    handleIOE(e);
                }
            }
        });
    }

    /**
     * Sends a message to the server.
     *
     * @param msg message to send
     */
    public void send(Message msg) {
        messagesToSend.add(msg);
    }

    /**
     * Terminates operations of the network.
     */
    public void terminate() {
        terminateFlag = true;
        try {
            socket.close();
        } catch (IOException e) {
            Log.e(TAG, "Can not terminate the client.");
        }
    }

    public boolean isConnected() {
        return isConnected;
    }

    public boolean isTerminated() {
        return terminateFlag;
    }

    private void handleIOE(IOException e) {
        numOfExceptions++;
        if (numOfExceptions > MAX_NUM_EXCEPTIONS)
            terminate();
    }

}