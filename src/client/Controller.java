package client;

import client.model.Game;
import common.model.Event;
import common.network.data.Message;
import common.util.Log;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Main controller. Controls execution of the program, e.g. checks time limit of
 * the client, handles incoming messages, controls network operations, etc.
 * This is an internal implementation and you do not need to know anything about
 * this class.
 * Please do not change this class.
 */
public class Controller {

    // Logging tag
    private static final String TAG = "Controller";

    // File encoding for connection details
    private static String detailsEnc = "UTF-8";

    // Connection details
    private int port;
    private String host;
    private String token;
    private long retryDelay;

    // AI (participant's) class
    private AI ai;

    // Game model
    private Game game;

    // Client side network
    private Network network;

    // Terminator. Controller waits for this object to be notified. Then it will be terminated.
    private final Object terminator;

    Consumer<Message> sender;

    Event event;

    /**
     * Constructor
     *
     * @param hostIP     host address
     * @param hostPort   host port
     * @param token      client token
     * @param retryDelay connection retry delay
     */
    public Controller(String hostIP, int hostPort, String token, long retryDelay) {
        this.terminator = new Object();
        this.host = hostIP;
        this.port = hostPort;
        this.token = token;
        this.retryDelay = retryDelay;
    }

    /**
     * Starts a client by connecting to the server and sending a token.
     */
    public void start() {
        try {
            network = new Network(this::handleMessage);
            sender=network::send;
            ai = new AI();
            network.setConnectionData(host, port, token);
            while (!network.isConnected()) {
                network.connect();
                Thread.sleep(retryDelay);
            }
            synchronized (terminator) {
                terminator.wait();
            }
            network.terminate();
        } catch (Exception e) {
            Log.e(TAG, "Can not start the client.", e);
            e.printStackTrace();
        }
    }

    /**
     * Handles incoming message. This method will be called from
     * {@link client.Network} when a new message is received.
     *
     * @param msg incoming message
     */
    private void handleMessage(Message msg) {

        Log.v(TAG, msg.name + " received.");
        switch (msg.name) {
            case "pick":
                handlePickMessage(msg);
                break;
            case "turn":
                handleTurnMessage(msg);
                break;
            case "init":
                handleInitMessage(msg);
                break;
            case "shutdown":
                handleShutdownMessage(msg);
                break;
            default:
                Log.w(TAG, "Undefined message received: " + msg.name);
                break;
        }
        Log.v(TAG, msg.name + " handle finished.");
    }

    /**
     * Handles init message.
     *
     * @param msg init message
     */
    private void handleInitMessage(Message msg) {
        game=new Game(sender);
        game.handleInitMessage(msg);

    }

    /**
     * Handles turn message. Gives the message to the model and then executes
     * client's code to do next turn.
     *
     * @param msg turn message
     */
    private void handleTurnMessage(Message msg)
    {
     //   game.setCurrentTurn(game.getCurrentTurn() + 1);
     //   event=new Event("end", new Object[]{game.getCurrentTurn()});
        game=new Game(sender);
        game.handleTurnMessage(msg);

        /* TODO */// log for debug
        /* TODO */// handle moveTurn, actionTurn
    }

    private void handlePickMessage(Message msg)
    {
        game=new Game(sender);
        game.handlePickMessage(msg);
    }

    /**
     * Handles shutdown message.
     *
     * @param msg shutdown message
     */
    private void handleShutdownMessage(Message msg) {
        network.terminate();
        System.exit(0);
    }

    private void preProccess() //TODO
    {

    }

    private void pickTurn() //TODO
    {

    }

    private void moveTurn() //TODO
    {

    }

    private void actionTurn() //TODO
    {

    }


    private void sendEndMsg(Event event){
        sender.accept(new Message(Event.EVENT, event));
    }

}