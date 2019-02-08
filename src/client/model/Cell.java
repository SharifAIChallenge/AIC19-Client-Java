package client.model;

public class Cell {
    private boolean isWall;
    private boolean isInMyRespawnZone;
    private boolean isInOppRespawnZone;
    private boolean isInObjectiveZone;
    private boolean isInVision = true;
    private int row;
    private int column;
    
    Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.isWall = false;
        this.isInMyRespawnZone = false;
        this.isInOppRespawnZone = false;
        this.isInObjectiveZone = false;
        this.isInVision = false;
    }

    public boolean isWall() {
        return isWall;
    }

    void setWall(boolean wall) {
        isWall = wall;
    }

    public boolean isInMyRespawnZone() {
        return isInMyRespawnZone;
    }

    void setInMyRespawnZone(boolean inMyRespawnZone) {
        isInMyRespawnZone = inMyRespawnZone;
    }

    public boolean isInOppRespawnZone() {
        return isInOppRespawnZone;
    }

    void setInOppRespawnZone(boolean inOppRespawnZone) {
        isInOppRespawnZone = inOppRespawnZone;
    }

    public boolean isInObjectiveZone() {
        return isInObjectiveZone;
    }

    void setInObjectiveZone(boolean inObjectiveZone) {
        isInObjectiveZone = inObjectiveZone;
    }

    public boolean isInVision() {
        return isInVision;
    }

    void setInVision(boolean inVision) {
        isInVision = inVision;
    }

    public int getRow() {
        return row;
    }

    void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    void setColumn(int column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return row == cell.row && column == cell.column;
    }

    @Override
    public int hashCode() {
        return row * 1000 + column;
    }

    @Override
    public String toString()
    {
        return "Cell{" +
                "isWall=" + isWall +
                ", isInMyRespawnZone=" + isInMyRespawnZone +
                ", isInOppRespawnZone=" + isInOppRespawnZone +
                ", isInObjectiveZone=" + isInObjectiveZone +
                ", isInVision=" + isInVision +
                '}';
    }
}
