package client.model;

public class Constants
{
    private int respawnTime, timeout, maxAP, maxTurns;

    public int getRespawnTime()
    {
        return respawnTime;
    }

    public void setRespawnTime(int respawnTime)
    {
        this.respawnTime = respawnTime;
    }

    public int getTimeout()
    {
        return timeout;
    }

    public void setTimeout(int timeout)
    {
        this.timeout = timeout;
    }

    public int getMaxAP()
    {
        return maxAP;
    }

    public void setMaxAP(int maxAP)
    {
        this.maxAP = maxAP;
    }

    public int getMaxTurns()
    {
        return maxTurns;
    }

    public void setMaxTurns(int maxTurns)
    {
        this.maxTurns = maxTurns;
    }
}
