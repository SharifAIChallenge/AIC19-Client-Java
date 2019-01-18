package client.model;

import java.util.Objects;

public class Cell {
    private boolean isWall;
    private boolean isInMyRespawnZone;
    private boolean isInOppRespawnZone;
    private boolean isInObjectiveZone;
    private boolean isInVision = true;
    private int row;
    private int column;

    public boolean isWall() {
        return isWall;
    }

    public void setWall(boolean wall) {
        isWall = wall;
    }

    public boolean isInMyRespawnZone() {
        return isInMyRespawnZone;
    }

    public void setInMyRespawnZone(boolean inMyRespawnZone) {
        isInMyRespawnZone = inMyRespawnZone;
    }

    public boolean isInOppRespawnZone() {
        return isInOppRespawnZone;
    }

    public void setInOppRespawnZone(boolean inOppRespawnZone) {
        isInOppRespawnZone = inOppRespawnZone;
    }

    public boolean isInObjectiveZone() {
        return isInObjectiveZone;
    }

    public void setInObjectiveZone(boolean inObjectiveZone) {
        isInObjectiveZone = inObjectiveZone;
    }

    public boolean isInVision() {
        return isInVision;
    }

    public void setInVision(boolean inVision) {
        isInVision = inVision;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
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
        return row*1000+column; //TODO
    }
}
