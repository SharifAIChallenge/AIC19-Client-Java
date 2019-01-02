package client.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import common.network.data.Message;

import java.util.function.Consumer;

public class Game
{

    public Game(Consumer<Message> sender)
    {

    }

    public void handleInitMessage(Message msg)
    {
        /* TODO */// get objects from msg.args.get(0).getAsJsonObject()

    }

    public void handleTurnMessage(Message msg)
    {

    }

    public void handlePickMessage(Message msg)
    {

    }

    public int getCurrentTurn()
    {
        return 0;
    }

    public void setCurrentTurn(int i)
    {

    }

}
