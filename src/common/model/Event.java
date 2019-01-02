package common.model;

/**
 * Event class.
 */
public class Event {
    public static final String EVENT = "event";

    protected String type; // the type of the event
    protected Object[] args; // arguments of the event

    public Event(String type, Object[] args) {
        this.type = type;
        this.args = args;
    }

    public String getType() {
        return type;
    }

    public Object[] getArgs() {
        return args;
    }
}