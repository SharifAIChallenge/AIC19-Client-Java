package client.model;

public class GameConstants {
    private int normalTimeout;
    private int preprocessTimeout;
    private int firstMoveTimeout;
    private int maxAP;
    private int maxTurns;
    private int killScore;
    private int objectiveZoneScore;
    private int maxScore;

    int getNormalTimeout() {
        return normalTimeout;
    }

    void setNormalTimeout(int normalTimeout) {
        this.normalTimeout = normalTimeout;
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

    public int getPreprocessTimeout() {
        return preprocessTimeout;
    }

    public int getFirstMoveTimeout() {
        return firstMoveTimeout;
    }

    void setPreprocessTimeout(int preprocessTimeout) {
        this.preprocessTimeout = preprocessTimeout;
    }

    void setFirstMoveTimeout(int firstMoveTimeout) {
        this.firstMoveTimeout = firstMoveTimeout;
    }

    @Override
    public String toString()
    {
        return "GameConstants{" +
                "normalTimeout=" + normalTimeout +
                ", preprocessTimeout=" + preprocessTimeout +
                ", firstMoveTimeout=" + firstMoveTimeout +
                ", maxAP=" + maxAP +
                ", maxTurns=" + maxTurns +
                ", killScore=" + killScore +
                ", objectiveZoneScore=" + objectiveZoneScore +
                ", maxScore=" + maxScore +
                '}';
    }
}
