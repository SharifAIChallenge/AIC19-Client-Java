package client.model;

import common.network.data.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Consumer;

public class Game
{
    private Map map;
    private GameConstants gameConstants;
    private AbilityConstants[] abilityConstants;
    private HeroConstants[] heroConstants;

    private Hero[] myHeroes;
    private Hero[] oppHeroes;
    private Hero[] myDeadHeroes;
    private Hero[] oppDeadHeroes;

    private Cell[] brokenWalls;
    private Cell[] createdWalls;

    private int AP;
    private int score;


    public Game(Consumer<Message> sender)
    {

    }

    public void handleInitMessage(Message msg)
    {
        /* TODO */// get objects from msg.args.get(0).getAsJsonObject()

    }


    public void handleTurnMessage(Message msg)
    {

    }

    public void handlePickMessage(Message msg)
    {

    }

    public Hero getHero(int id)
    {
        for (int i = 0; i < myHeroes.length; i++)
        {
            if (myHeroes[i].getId() == id)
                return myHeroes[i];
        }
        for (int i = 0; i < oppHeroes.length; i++)
        {
            if (oppHeroes[i].getId() == id)
                return oppHeroes[i];
        }
        return null;
    }

    public Hero getMyHero(Cell cell)
    {
        for (Hero hero : myHeroes)
        {
            if (hero.getCurrentCell() == cell)
                return hero;
        }
        return null;
    }
    public Hero getMyHero(int cellRow,int cellColumn)
    {
        if(!isInMap(cellRow,cellColumn))return null;
        return getMyHero(map.getCell(cellRow,cellColumn));
    }

    public Hero getOppHero(Cell cell)
    {
        for (Hero hero : oppHeroes)
        {
            if (hero.getCurrentCell() == cell)
                return hero;
        }
        return null;
    }
    public Hero getOppHero(int cellRow,int cellColumn)
    {
        if(!isInMap(cellRow,cellColumn))return null;
        return getOppHero(map.getCell(cellRow,cellColumn));
    }

    public void castAbility(int heroId, AbilityName abilityName, Cell targetCell)
    {
        /* TODO */
    }

    public void castAbility(int heroId, AbilityName abilityName, int targetCellRow, int targetCellColumn)
    {
        castAbility(heroId, abilityName, map.getCell(targetCellRow, targetCellColumn));
    }

    public void castAbility(Hero hero, AbilityName abilityName, Cell targetCell)
    {
        castAbility(hero.getId(), abilityName, targetCell);
    }

    public void castAbility(Hero hero, AbilityName abilityName, int targetCellRow, int targetCellColumn)
    {
        castAbility(hero.getId(), abilityName, map.getCell(targetCellRow, targetCellColumn));
    }

    public void castAbility(int heroId, Ability ability, Cell targetCell)
    {
        castAbility(heroId, ability.getAbilityConstants().getName(),targetCell);
    }

    public void castAbility(int heroId, Ability ability, int targetCellRow, int targetCellColumn)
    {
        castAbility(heroId, ability.getAbilityConstants().getName(), map.getCell(targetCellRow, targetCellColumn));
    }

    public void castAbility(Hero hero, Ability ability, Cell targetCell)
    {
        castAbility(hero.getId(), ability.getAbilityConstants().getName(), targetCell);
    }

    public void castAbility(Hero hero, Ability ability, int targetCellRow, int targetCellColumn)
    {
        castAbility(hero.getId(), ability.getAbilityConstants().getName(), map.getCell(targetCellRow, targetCellColumn));
    }

    public void moveHero(int heroId, Direction direction)
    {
        /* TODO */
    }

    public void moveHero(Hero hero, Direction direction)
    {
        moveHero(hero.getId(), direction);
    }

    public void pickHero(HeroName heroName)
    {
        /* TODO */
    }

    private boolean isInMap(int cellRow,int cellColumn)
    {
        return cellRow >= 0 && cellColumn >= 0 && cellRow < map.getRowNum() && cellColumn < map.getColumnNum();
    }

    public boolean isAccessible(int cellRow, int cellColumn)
    {
        if (cellRow < 0 || cellColumn < 0 || cellRow >= map.getRowNum() || cellColumn >= map.getColumnNum())
            return false;
        return !map.getCell(cellRow, cellColumn).isWall();
    }

    private Cell getNextCell(Cell cell, Direction direction)
    {
        switch (direction)
        {
            case UP:
                if (isAccessible(cell.getRow() - 1, cell.getColumn()))
                    return map.getCell(cell.getRow() - 1, cell.getColumn());
                else
                    return null;
            case DOWN:
                if (isAccessible(cell.getRow() + 1, cell.getColumn()))
                    return map.getCell(cell.getRow() + 1, cell.getColumn());
                else
                    return null;
            case LEFT:
                if (isAccessible(cell.getRow(), cell.getColumn() - 1))
                    return map.getCell(cell.getRow(), cell.getColumn() - 1);
                else
                    return null;
            case RIGHT:
                if (isAccessible(cell.getRow(), cell.getColumn() + 1))
                    return map.getCell(cell.getRow(), cell.getColumn() + 1);
                else
                    return null;
        }
        return null; // never happens
    }

    /**
     * In case of start cell and end cell being the same cells, return empty array.
     *
     * @param startCell
     * @param endCell
     * @return
     */
    public Direction[] getPathMoveDirections(Cell startCell, Cell endCell)
    {
        if (startCell == endCell) return new Direction[0];
        if (startCell.isWall() || endCell.isWall()) return null;

        HashMap<Cell,Pair<Cell,Direction>> lastMoveInfo=new HashMap<>(); // saves parent cell and direction to go from parent cell to current cell
        Cell[] bfsQueue=new Cell[map.getRowNum() * map.getColumnNum() + 10];
        int queueHead = 0, queueTail = 0;

        lastMoveInfo.put(startCell,new Pair<Cell,Direction>(null,null));
        bfsQueue[queueTail++] = startCell;

        while (queueHead != queueTail)
        {
            Cell currentCell = bfsQueue[queueHead++];
            if(currentCell==endCell)
            {
                ArrayList<Direction> directions=new ArrayList<>();
                while(currentCell!=startCell)
                {
                    directions.add(lastMoveInfo.get(currentCell).getSecond());
                    currentCell=lastMoveInfo.get(currentCell).getFirst();
                }
                Collections.reverse(directions);
                Direction[] directionsArray=new Direction[directions.size()];
                return directions.toArray(directionsArray);
            }
            Cell upCell = getNextCell(currentCell, Direction.UP);
            if (upCell != null && !lastMoveInfo.containsKey(upCell))
            {
                lastMoveInfo.put(upCell,new Pair<>(currentCell,Direction.UP));
                bfsQueue[queueTail++]=upCell;
            }
            Cell downCell = getNextCell(currentCell, Direction.DOWN);
            if (downCell != null && !lastMoveInfo.containsKey(downCell))
            {
                lastMoveInfo.put(downCell,new Pair<>(currentCell,Direction.DOWN));
                bfsQueue[queueTail++]=downCell;
            }
            Cell leftCell = getNextCell(currentCell, Direction.LEFT);
            if (leftCell != null && !lastMoveInfo.containsKey(leftCell))
            {
                lastMoveInfo.put(leftCell,new Pair<>(currentCell,Direction.LEFT));
                bfsQueue[queueTail++]=leftCell;
            }
            Cell rightCell = getNextCell(currentCell, Direction.RIGHT);
            if (rightCell != null && !lastMoveInfo.containsKey(rightCell))
            {
                lastMoveInfo.put(rightCell,new Pair<>(currentCell,Direction.RIGHT));
                bfsQueue[queueTail++]=rightCell;
            }
        }
        return null;
    }


    public Direction[] getPathMoveDirections(int startCellRow, int startCellColumn, int endCellRow, int endCellColumn)
    {
        if(!isInMap(startCellRow,startCellColumn) || !isInMap(endCellRow,endCellColumn))return null;
        return getPathMoveDirections(map.getCell(startCellRow, startCellColumn), map.getCell(endCellRow, endCellColumn));
    }

    /* TODO */// Mahdavi Check

    /**
     * Get all the cells that collide with the ray line in at least one non corner point, before reaching a wall.
     * If it hits a wall cell just in the corner, it would also stop too.
     *
     * @param startCell
     * @param endCell
     * @return
     */
    private Cell[] getRayCells(Cell startCell, Cell endCell)
    {

        return null;
    }

    public int manhattanDistance(Cell startCell, Cell endCell)
    {
        return Math.abs(startCell.getRow() - endCell.getRow()) + Math.abs(startCell.getColumn() - endCell.getColumn());
    }

    public int manhattanDistance(int startCellRow,int startCellColumn,int endCellRow,int endCellColumn)
    {
        if(!isInMap(startCellRow,startCellColumn) || !isInMap(endCellRow,endCellColumn))return -1;
        return manhattanDistance(map.getCell(startCellRow,startCellColumn),map.getCell(endCellRow,endCellColumn));
    }

    /**
     * If start cell is a wall we would return it as the impact point.
     *
     * @param ability
     * @param startCell
     * @param targetCell
     * @return
     */
    public Cell getImpactCell(Ability ability, Cell startCell, Cell targetCell)/* TODO */// add abilityName
    {
        if (startCell.isWall())
            return startCell;
        if (startCell == targetCell)
            return startCell;
        Cell[] rayCells = getRayCells(startCell, targetCell);
        Cell lastValidCell = null; // would not remain null cause range is not zero
        for (Cell cell : rayCells)
        {
            if (getOppHero(cell) != null)
                return cell;
            if (manhattanDistance(startCell, cell) > ability.getAbilityConstants().getRange())
                return lastValidCell;
            lastValidCell = cell;
        }
        return lastValidCell;
    }

    public Cell getImpactCell(Ability ability,int startCellRow,int startCellColumn,int endCellRow,int endCellColumn)
    {
        if(!isInMap(startCellRow,startCellColumn) || !isInMap(endCellRow,endCellColumn))return null;
        return getImpactCell(ability,map.getCell(startCellRow,startCellColumn),map.getCell(endCellRow,endCellColumn));
    }

    public boolean isInVision(Cell startCell, Cell endCell)
    {
        if (startCell.isWall() || endCell.isWall())
            return false;
        Cell[] rayCells = getRayCells(startCell, endCell);
        Cell lastCell = rayCells[rayCells.length - 1];
        return lastCell == endCell;
    }


    public boolean isInVision(int startCellRow, int startCellColumn, int endCellRow, int endCellColumn)
    {
        if(!isInMap(startCellRow,startCellColumn) || !isInMap(endCellRow,endCellColumn))return false;
        return isInVision(map.getCell(startCellRow, startCellColumn), map.getCell(endCellRow, endCellColumn));
    }

    public Hero[] getMyHeroes()
    {
        return myHeroes;
    }

    public void setMyHeroes(Hero[] myHeroes)
    {
        this.myHeroes = myHeroes;
    }

    public Hero[] getOppHeroes()
    {
        return oppHeroes;
    }

    public void setOppHeroes(Hero[] oppHeroes)
    {
        this.oppHeroes = oppHeroes;
    }

    public Hero[] getMyDeadHeroes()
    {
        return myDeadHeroes;
    }

    public void setMyDeadHeroes(Hero[] myDeadHeroes)
    {
        this.myDeadHeroes = myDeadHeroes;
    }

    public Hero[] getOppDeadHeroes()
    {
        return oppDeadHeroes;
    }

    public void setOppDeadHeroes(Hero[] oppDeadHeroes)
    {
        this.oppDeadHeroes = oppDeadHeroes;
    }

    public Map getMap()
    {
        return map;
    }

    public void setMap(Map map)
    {
        this.map = map;
    }

    public GameConstants getGameConstants()
    {
        return gameConstants;
    }

    public void setGameConstants(GameConstants gameConstants)
    {
        this.gameConstants = gameConstants;
    }

    public AbilityConstants[] getAbilityConstants()
    {
        return abilityConstants;
    }

    public void setAbilityConstants(AbilityConstants[] abilityConstants)
    {
        this.abilityConstants = abilityConstants;
    }

    public HeroConstants[] getHeroConstants()
    {
        return heroConstants;
    }

    public void setHeroConstants(HeroConstants[] heroConstants)
    {
        this.heroConstants = heroConstants;
    }

    public Cell[] getBrokenWalls()
    {
        return brokenWalls;
    }

    public void setBrokenWalls(Cell[] brokenWalls)
    {
        this.brokenWalls = brokenWalls;
    }

    public Cell[] getCreatedWalls()
    {
        return createdWalls;
    }

    public void setCreatedWalls(Cell[] createdWalls)
    {
        this.createdWalls = createdWalls;
    }

    public int getAP()
    {
        return AP;
    }

    public void setAP(int AP)
    {
        this.AP = AP;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }
}
