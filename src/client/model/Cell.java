package client.model;

public class Cell
{
    private boolean isWall,isInMyRespawnZone,isInOppRespawnZone,isInMainZone;
    private boolean isInVision;
    private int row,column;

    public boolean isWall()
    {
        return isWall;
    }

    public void setWall(boolean wall)
    {
        isWall = wall;
    }

    public boolean isInMyRespawnZone()
    {
        return isInMyRespawnZone;
    }

    public void setInMyRespawnZone(boolean inMyRespawnZone)
    {
        isInMyRespawnZone = inMyRespawnZone;
    }

    public boolean isInOppRespawnZone()
    {
        return isInOppRespawnZone;
    }

    public void setInOppRespawnZone(boolean inOppRespawnZone)
    {
        isInOppRespawnZone = inOppRespawnZone;
    }

    public boolean isInMainZone()
    {
        return isInMainZone;
    }

    public void setInMainZone(boolean inMainZone)
    {
        isInMainZone = inMainZone;
    }

    public boolean isInVision()
    {
        return isInVision;
    }

    public void setInVision(boolean inVision)
    {
        isInVision = inVision;
    }

    public int getRow()
    {
        return row;
    }

    public void setRow(int row)
    {
        this.row = row;
    }

    public int getColumn()
    {
        return column;
    }

    public void setColumn(int column)
    {
        this.column = column;
    }
}
