package client.model;

public class Map
{
    private Cell[][] cells;
    private int rowNum;
    private int columnNum;
    private Cell[] myRespawnZone;
    private Cell[] oppRespawnZone;
    private Cell[] objectiveZone;

    public Cell getCell(int row,int column)
    {
        return cells[row][column];
    }

    public Cell[][] getCells()
    {
        return cells;
    }

    public void setCells(Cell[][] cells)
    {
        this.cells = cells;
    }

    public int getRowNum()
    {
        return rowNum;
    }

    public void setRowNum(int rowNum)
    {
        this.rowNum = rowNum;
    }

    public int getColumnNum()
    {
        return columnNum;
    }

    public void setColumnNum(int columnNum)
    {
        this.columnNum = columnNum;
    }

    public Cell[] getMyRespawnZone()
    {
        return myRespawnZone;
    }

    public void setMyRespawnZone(Cell[] myRespawnZone)
    {
        this.myRespawnZone = myRespawnZone;
    }

    public Cell[] getOppRespawnZone()
    {
        return oppRespawnZone;
    }

    public void setOppRespawnZone(Cell[] oppRespawnZone)
    {
        this.oppRespawnZone = oppRespawnZone;
    }

    public Cell[] getObjectiveZone()
    {
        return objectiveZone;
    }

    public void setObjectiveZone(Cell[] objectiveZone)
    {
        this.objectiveZone = objectiveZone;
    }
}
