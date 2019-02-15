package client.model;

import java.util.ArrayList;

public class Map {
    private Cell[][] cells;
    private int rowNum;
    private int columnNum;
    private Cell[] myRespawnZone;
    private Cell[] oppRespawnZone;
    private Cell[] objectiveZone;

    public Cell getCell(int row, int column) {
        if (!isInMap(row, column)) return null;
        return cells[row][column];
    }

    public boolean isInMap(int row, int column) {
        return (row >= 0 && row < rowNum && column >= 0 && column < columnNum);
    }

    void calculateZones() {
        ArrayList<Cell> myRespawnZone = new ArrayList<>();
        ArrayList<Cell> oppRespawnZone = new ArrayList<>();
        ArrayList<Cell> objectiveZone = new ArrayList<>();
        for (int row = 0; row < rowNum; row++) {
            for (int column = 0; column < columnNum; column++) {
                Cell cell = cells[row][column];
                if (cell.isInMyRespawnZone()) myRespawnZone.add(cell);
                if (cell.isInOppRespawnZone()) oppRespawnZone.add(cell);
                if (cell.isInObjectiveZone()) objectiveZone.add(cell);
            }
        }
        this.myRespawnZone = myRespawnZone.toArray(new Cell[0]);
        this.oppRespawnZone = oppRespawnZone.toArray(new Cell[0]);
        this.objectiveZone = objectiveZone.toArray(new Cell[0]);
    }

    public Cell[][] getCells() {
        return cells;
    }

    void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public int getRowNum() {
        return rowNum;
    }

    void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getColumnNum() {
        return columnNum;
    }

    void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }

    public Cell[] getMyRespawnZone() {
        return myRespawnZone;
    }

    void setMyRespawnZone(Cell[] myRespawnZone) {
        this.myRespawnZone = myRespawnZone;
    }

    public Cell[] getOppRespawnZone() {
        return oppRespawnZone;
    }

    void setOppRespawnZone(Cell[] oppRespawnZone) {
        this.oppRespawnZone = oppRespawnZone;
    }

    public Cell[] getObjectiveZone() {
        return objectiveZone;
    }

    void setObjectiveZone(Cell[] objectiveZone) {
        this.objectiveZone = objectiveZone;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Map: rowNum=" + rowNum + " columnNum=" + columnNum + "\n");
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < columnNum; j++) {
                result.append(cells[i][j].toString());
                if (j != columnNum - 1) {
                    result.append(", ");
                }
            }
            result.append("\n");
        }
        return String.valueOf(result);
    }

    Cell getCell(Cell cell) {
        return getCell(cell.getRow(), cell.getColumn());
    }
}
