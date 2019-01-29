package client.model;

public class GameConstants
{
    private int timeout;
    private int maxAP;
    private int maxTurns;
    private int killScore;
    private int objectiveZoneScore;
    
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

    public int getKillScore() {
        return killScore;
    }

    public void setKillScore(int killScore) {
        this.killScore = killScore;
    }

    public int getObjectiveZoneScore() {
        return objectiveZoneScore;
    }

    public void setObjectiveZoneScore(int objectiveZoneScore) {
        this.objectiveZoneScore = objectiveZoneScore;
    }
}
