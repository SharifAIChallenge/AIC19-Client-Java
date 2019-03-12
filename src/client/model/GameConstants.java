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
    private int initOvertime;
    private int maxScoreDiff;
    private int totalMovePhases;

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

    public int getInitOvertime() {
        return initOvertime;
    }

    public void setInitOvertime(int initOvertime) {
        this.initOvertime = initOvertime;
    }

    public int getMaxScoreDiff() {
        return maxScoreDiff;
    }

    void setMaxScoreDiff(int maxScoreDiff) {
        this.maxScoreDiff = maxScoreDiff;
    }

    public int getTotalMovePhases() {
        return totalMovePhases;
    }

    void setTotalMovePhases(int totalMovePhases) {
        this.totalMovePhases = totalMovePhases;
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
