package client;

import client.model.Game;
import client.model.Phase;
import common.model.Event;
import common.network.data.Message;
import common.util.Log;

import java.util.function.Consumer;

/**
 * Main controller. Controls execution of the program, e.g. checks time limit of
 * the client, handles incoming messages, controls network operations, etc.
 * This is an internal implementation and you do not need to know anything about
 * this class.
 * Please do not change this class.
 */
public class Controller
{

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

    /**
     * Constructor
     *
     * @param hostIP     host address
     * @param hostPort   host port
     * @param token      client token
     * @param retryDelay connection retry delay
     */
    public Controller(String hostIP, int hostPort, String token, long retryDelay)
    {
        this.terminator = new Object();
        this.host = hostIP;
        this.port = hostPort;
        this.token = token;
        this.retryDelay = retryDelay;
    }

    /**
     * Starts a client by connecting to the server and sending a token.
     */
    public void start()
    {
        try
        {
            network = new Network(this::handleMessage);
            sender = network::send;
            game = new Game(sender);
            ai = new AI();

            network.setConnectionData(host, port, token);
            while (!network.isConnected())
            {
                network.connect();
                Thread.sleep(retryDelay);
            }
            synchronized (terminator)
            {
                terminator.wait();
            }
            network.terminate();
        } catch (Exception e)
        {
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
    private void handleMessage(Message msg)
    {

        Log.v(TAG, msg.name + " received.");
        switch (msg.name)
        {
            case "init":
                handleInitMessage(msg);
                break;
            case "pick":
                handlePickMessage(msg);
                break;
            case "turn":
                handleTurnMessage(msg);
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
    private void handleInitMessage(Message msg)
    {
        game = new Game(game);
        game.handleInitMessage(msg);
        Event endEvent = new Event("init-end", new Object[]{});
        preProcess(game, endEvent);
    }

    private void handlePickMessage(Message msg)
    {
        Game newGame = new Game(game);
        newGame.handlePickMessage(msg);
        Event endEvent = new Event("pick-end", new Object[]{newGame.getCurrentTurn()});
        pickTurn(newGame, endEvent);
    }

    private void handleTurnMessage(Message msg)
    {
        Game newGame = new Game(game);
        newGame.handleTurnMessage(msg);
        Event endEvent;
        if (newGame.getCurrentPhase() == Phase.MOVE)
        {
            endEvent = new Event("move-end", new Object[]{newGame.getCurrentTurn(), newGame.getMovePhaseNum()});
            moveTurn(newGame, endEvent);
        } else
        {
            endEvent = new Event("action-end", new Object[]{newGame.getCurrentTurn()});
            actionTurn(newGame, endEvent);
        }
    }

    /**
     * Handles shutdown message.
     *
     * @param msg shutdown message
     */
    private void handleShutdownMessage(Message msg)
    {
        network.terminate();
        System.exit(0);
    }

    private void preProcess(Game game, Event endEvent)
    {
        new Thread(() -> {
            ai.preProcess(game);
            sendEndMsg(endEvent);
        }).start();
    }

    private void pickTurn(Game game, Event endEvent)
    {
        new Thread(() ->
        {
            ai.pickTurn(game);
            sendEndMsg(endEvent);
        }).start();
    }

    private void moveTurn(Game game, Event endEvent)
    {
        new Thread(() ->
        {
            ai.moveTurn(game);
            sendEndMsg(endEvent);
        }).start();
    }

    private void actionTurn(Game game, Event endEvent)
    {
        new Thread(() ->
        {
            ai.actionTurn(game);
            sendEndMsg(endEvent);
        }).start();
    }


    private void sendEndMsg(Event event)
    {
        sender.accept(new Message(Event.EVENT, event));
    }

}