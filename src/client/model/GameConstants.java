package client.model;

public class GameConstants {
    private int timeout;
    private int maxAP;
    private int maxTurns;
    private int killScore;
    private int objectiveZoneScore;
    private int maxScore;

    int getTimeout() {
        return timeout;
    }

    void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    int getMaxAP() {
        return maxAP;
    }

    void setMaxAP(int maxAP) {
        this.maxAP = maxAP;
    }

    int getMaxTurns() {
        return maxTurns;
    }

    void setMaxTurns(int maxTurns) {
        this.maxTurns = maxTurns;
    }

    int getKillScore() {
        return killScore;
    }

    void setKillScore(int killScore) {
        this.killScore = killScore;
    }

    int getObjectiveZoneScore() {
        return objectiveZoneScore;
    }

    void setObjectiveZoneScore(int objectiveZoneScore) {
        this.objectiveZoneScore = objectiveZoneScore;
    }

    int getMaxScore() {
        return maxScore;
    }

    void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }
}
