package common.network.data;

import com.google.gson.JsonArray;
import common.network.Json;


/**
 * Message class.
 */
public class Message
{
    public final String name;
    public final JsonArray args;

    public Message(String name, JsonArray args)
    {
        this.name = name;
        this.args = args;
    }

    public Message(String name, Object... args)
    {
        this(name, Json.GSON.toJsonTree(args).getAsJsonArray());
    }
}