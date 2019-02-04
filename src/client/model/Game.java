package client.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import common.model.Event;
import common.network.Json;
import common.network.data.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Consumer;

public class Game implements World {
    private GameConstants gameConstants;
    private HeroConstants[] heroConstants;
    private AbilityConstants[] abilityConstants;
    private Map map;
    private Hero[] myHeroes;
    private Hero[] oppHeroes;
    private Hero[] myDeadHeroes;
    private Hero[] oppDeadHeroes;

    private CastAbility[] myCastAbilities;
    private CastAbility[] oppCastAbilities;
    private int AP;
    private int myScore;
    private int oppScore;
    private int currentTurn;

    private Phase currentPhase;
    private int movePhaseNum;

    private Consumer<Message> sender;

    public Game(Consumer<Message> sender) {
        this.sender = sender;
    }

    public Game(Game game) {
        this.gameConstants = game.gameConstants;
        this.heroConstants = game.heroConstants;
        this.abilityConstants = game.abilityConstants;
        this.map = game.map;
        this.sender = game.sender;
    }

    private AbilityConstants getAbilityConstants(AbilityName abilityName) {
        for (AbilityConstants abilityConstants : abilityConstants) {
            if (abilityConstants.getName() == abilityName) {
                return abilityConstants;
            }
        }
        return null;
    }

    private HeroConstants getHeroConstants(HeroType heroType) {
        for (HeroConstants heroConstants : heroConstants) {
            if (heroConstants.getType() == heroType) {
                return heroConstants;
            }
        }
        return null;
    }

    public void handleInitMessage(Message msg) {
        JsonArray messageArray = msg.args;
        JsonObject mainObject = messageArray.get(0).getAsJsonObject();

        InitJson initJson = Json.GSON.fromJson(mainObject, InitJson.class);
        gameConstants = initJson.getGameConstants();
        map = initJson.getMap();
        map.calculateZones();
        heroConstants = initJson.getHeroConstants();
        abilityConstants = initJson.getAbilityConstants();
    }

    public void handleTurnMessage(Message msg) {
        JsonObject jsonRoot = msg.args.get(0).getAsJsonObject();
        myScore = jsonRoot.get("myScore").getAsInt();
        oppScore = jsonRoot.get("oppScore").getAsInt();
        currentTurn = jsonRoot.get("currentTurn").getAsInt();
        currentPhase = Phase.valueOf(jsonRoot.get("currentPhase").getAsString());
        movePhaseNum = jsonRoot.get("movePhaseNum").getAsInt();
        AP = jsonRoot.get("AP").getAsInt();
        Cell[][] turnCells = Json.GSON.fromJson(jsonRoot.get("map").getAsJsonArray(), Cell[][].class);
        this.map.setCells(turnCells);
        myCastAbilities = Json.GSON.fromJson(jsonRoot.get("myCastAbilities").getAsJsonArray(), CastAbility[].class);
        oppCastAbilities = Json.GSON.fromJson(jsonRoot.get("oppCastAbilities").getAsJsonArray(), CastAbility[].class);
        JsonArray myHeroesJson = jsonRoot.getAsJsonArray("myHeroes");
        ArrayList<Hero> myHeroes = getHeroesFromJson(myHeroesJson);
        this.myHeroes = myHeroes.toArray(new Hero[0]);
        ArrayList<Hero> myDeadHeroes = new ArrayList<>();
        for (Hero hero : myHeroes) {
            if (hero.getCurrentHP() == 0) {
                myDeadHeroes.add(hero);
            }
        }
        this.myDeadHeroes = myDeadHeroes.toArray(new Hero[0]);
        JsonArray oppHeroesJson = jsonRoot.getAsJsonArray("oppHeroes");
        ArrayList<Hero> oppHeroes = getHeroesFromJson(oppHeroesJson);
        this.oppHeroes = oppHeroes.toArray(new Hero[0]);
        ArrayList<Hero> oppDeadHeroes = new ArrayList<>();
        for (Hero hero : oppHeroes) {
            if (hero.getCurrentHP() == 0) {
                oppDeadHeroes.add(hero);
            }
        }
        this.oppDeadHeroes = oppDeadHeroes.toArray(new Hero[0]);
    }

    private ArrayList<Hero> getHeroesFromJson(JsonArray heroesJson) {
        ArrayList<Hero> heroes = new ArrayList<>();
        for (int i = 0; i < heroesJson.size(); i++) {
            JsonObject heroJson = heroesJson.get(i).getAsJsonObject();
            int id = heroJson.get("id").getAsInt();
            HeroType name = HeroType.valueOf(heroJson.get("type").getAsString());
            int currentHP = heroJson.get("currentHP").getAsInt();
            int respawnTime = heroJson.get("respawnTime").getAsInt();

            Cell currentCell = new Cell(-1, -1);
            if (heroJson.get("currentCell") != null) {
                JsonObject currentCellJson = heroJson.get("currentCell").getAsJsonObject();
                int row = currentCellJson.get("row").getAsInt();
                int column = currentCellJson.get("column").getAsInt();
                currentCell = map.getCell(row, column);
            }
            JsonArray recentPathJson = heroJson.get("recentPath").getAsJsonArray();
            ArrayList<Cell> recentCells = new ArrayList<>();
            for (int j = 0; j < recentPathJson.size(); j++) {
                JsonObject recentCellJson = recentPathJson.get(j).getAsJsonObject();
                int recentRow = recentCellJson.get("row").getAsInt();
                int recentColumn = recentCellJson.get("column").getAsInt();
                Cell recentCell = map.getCell(recentRow, recentColumn);
                recentCells.add(recentCell);
            }
            ArrayList<Ability> abilities = new ArrayList<>();
            JsonArray cooldownsJson = heroJson.get("cooldowns").getAsJsonArray();
            for (int j = 0; j < cooldownsJson.size(); j++) {
                JsonObject cooldownJson = cooldownsJson.get(j).getAsJsonObject();
                AbilityName abilityName = AbilityName.valueOf(cooldownJson.get("name").getAsString());
                int remCooldown = cooldownJson.get("remCooldown").getAsInt();
                Ability ability = new Ability(getAbilityConstants(abilityName));
                ability.setRemCooldown(remCooldown);
                abilities.add(ability);
            }
            Hero hero = new Hero(getHeroConstants(name), id, abilities);
            hero.setCurrentHP(currentHP);
            hero.setCurrentCell(currentCell);
            hero.setRemRespawnTime(respawnTime);
            hero.setRecentPath(recentCells.toArray(new Cell[0]));
            heroes.add(hero);
        }
        return heroes;
    }

    public void handlePickMessage(Message msg) {
        JsonObject jsonRoot = msg.args.get(0).getAsJsonObject();
        myHeroes = parseHeroes(jsonRoot, "myHeroes");
        oppHeroes = parseHeroes(jsonRoot, "oppHeroes");
        currentTurn = jsonRoot.get("currentTurn").getAsInt();
        currentPhase = Phase.PICK;
    }

    private Hero[] parseHeroes(JsonObject rootJson, String owner) {
        ArrayList<Hero> heroes = new ArrayList<>();
        JsonArray heroesJson = rootJson.getAsJsonArray(owner);
        for (int i = 0; i < heroesJson.size(); i++) {
            int id = heroesJson.get(i).getAsJsonObject().get("id").getAsInt();
            HeroType heroType = HeroType.valueOf(heroesJson.get(i).getAsJsonObject().get("type").getAsString());
            HeroConstants heroConstants = getHeroConstants(heroType);
            ArrayList<Ability> abilities = new ArrayList<>();
            for (AbilityName abilityName : heroConstants.getAbilityNames()) {
                abilities.add(new Ability(getAbilityConstants(abilityName)));
            }
            heroes.add(new Hero(heroConstants, id, abilities));
        }
        return heroes.toArray(new Hero[0]);
    }

    @Override
    public Hero getHero(int id) {
        for (int i = 0; i < myHeroes.length; i++) {
            if (myHeroes[i].getId() == id)
                return myHeroes[i];
        }
        for (int i = 0; i < oppHeroes.length; i++) {
            if (oppHeroes[i].getId() == id)
                return oppHeroes[i];
        }
        return null;
    }

    @Override
    public Hero getMyHero(Cell cell) {
        for (Hero hero : myHeroes) {
            if (hero.getCurrentCell() == cell)
                return hero;
        }
        return null;
    }

    @Override
    public Hero getMyHero(int cellRow, int cellColumn) {
        if (!map.isInMap(cellRow, cellColumn)) return null;
        return getMyHero(map.getCell(cellRow, cellColumn));
    }

    @Override
    public Hero getOppHero(Cell cell) {
        for (Hero hero : oppHeroes) {
            if (hero.getCurrentCell() == cell)
                return hero;
        }
        return null;
    }

    @Override
    public Hero getOppHero(int cellRow, int cellColumn) {
        if (!map.isInMap(cellRow, cellColumn)) return null;
        return getOppHero(map.getCell(cellRow, cellColumn));
    }

    @Override
    public void castAbility(int heroId, AbilityName abilityName, int targetCellRow, int targetCellColumn) {
        Event event = new Event("cast", new Object[]{heroId, abilityName.toString(), targetCellRow,
                targetCellColumn});
        sender.accept(new Message(Event.EVENT, event));
    }

    @Override
    public void castAbility(int heroId, AbilityName abilityName, Cell targetCell) {
        castAbility(heroId, abilityName, targetCell.getRow(), targetCell.getColumn());
    }

    @Override
    public void castAbility(Hero hero, AbilityName abilityName, Cell targetCell) {
        castAbility(hero.getId(), abilityName, targetCell.getRow(), targetCell.getColumn());
    }

    @Override
    public void castAbility(Hero hero, AbilityName abilityName, int targetCellRow, int targetCellColumn) {
        castAbility(hero.getId(), abilityName, targetCellRow, targetCellColumn);
    }

    @Override
    public void castAbility(int heroId, Ability ability, Cell targetCell) {
        castAbility(heroId, ability.getAbilityConstants().getName(), targetCell.getRow(), targetCell.getColumn());
    }

    @Override
    public void castAbility(int heroId, Ability ability, int targetCellRow, int targetCellColumn) {
        castAbility(heroId, ability.getAbilityConstants().getName(), targetCellRow, targetCellColumn);
    }

    @Override
    public void castAbility(Hero hero, Ability ability, Cell targetCell) {
        castAbility(hero.getId(), ability.getAbilityConstants().getName(), targetCell.getRow(),
                targetCell.getColumn());
    }

    @Override
    public void castAbility(Hero hero, Ability ability, int targetCellRow, int targetCellColumn) {
        castAbility(hero.getId(), ability.getAbilityConstants().getName(), targetCellRow, targetCellColumn);
    }

    @Override
    public void moveHero(int heroId, Direction direction) {
        Event event = new Event("move", new Object[]{heroId, Json.GSON.toJson(direction)}); //TODO is this ok?
        sender.accept(new Message(Event.EVENT, event));
    }

    @Override
    public void moveHero(Hero hero, Direction direction) {
        moveHero(hero.getId(), direction);
    }

    @Override
    public void pickHero(HeroType heroType) {
        Event event = new Event("pick", new Object[]{heroType.toString()});
        sender.accept(new Message(Event.EVENT, event));
    }

    public boolean isAccessible(int cellRow, int cellColumn) {
        if (!map.isInMap(cellRow, cellColumn))
            return false;
        return !map.getCell(cellRow, cellColumn).isWall();
    }

    public boolean isAccessible(Cell cell) {
        return !cell.isWall();
    }

    /**
     * In case of start cell and end cell being the same cells, return empty array.
     * If there is no path, return empty array.
     *
     * @param startCell
     * @param endCell
     * @return
     */
    @Override
    public Direction[] getPathMoveDirections(Cell startCell, Cell endCell) {
        if (startCell == endCell || startCell.isWall() || endCell.isWall()) return new Direction[0];

        HashMap<Cell, Pair<Cell, Direction>> lastMoveInfo = new HashMap<>(); // saves parent cell and direction to go from parent cell to current cell
        Cell[] bfsQueue = new Cell[map.getRowNum() * map.getColumnNum() + 10];
        int queueHead = 0, queueTail = 0;

        lastMoveInfo.put(startCell, new Pair<>(null, null));
        bfsQueue[queueTail++] = startCell;

        while (queueHead != queueTail) {
            Cell currentCell = bfsQueue[queueHead++];
            if (currentCell == endCell) {
                ArrayList<Direction> directions = new ArrayList<>();
                while (currentCell != startCell) {
                    directions.add(lastMoveInfo.get(currentCell).getSecond());
                    currentCell = lastMoveInfo.get(currentCell).getFirst();
                }
                Collections.reverse(directions);
                return directions.toArray(new Direction[0]);
            }
            for (Direction direction : Direction.values()) {
                Cell nextCell = getNextCell(currentCell, direction);
                if (nextCell != null && isAccessible(nextCell) && !lastMoveInfo.containsKey(nextCell)) {
                    lastMoveInfo.put(nextCell, new Pair<>(currentCell, direction));
                    bfsQueue[queueTail++] = nextCell;
                }
            }
        }
        return new Direction[0];
    }

    @Override
    public Direction[] getPathMoveDirections(int startCellRow, int startCellColumn, int endCellRow, int endCellColumn) {
        if (!map.isInMap(startCellRow, startCellColumn) || !map.isInMap(endCellRow, endCellColumn))
            return new Direction[0];
        return getPathMoveDirections(map.getCell(startCellRow, startCellColumn),
                map.getCell(endCellRow, endCellColumn));
    }

    public boolean isReachable(Cell startCell, Cell targetCell) {
        return (getPathMoveDirections(startCell, targetCell) != new Direction[0]) || startCell == targetCell;
    }

    public boolean isReachable(int startCellRow, int startCellColumn, int endCellRow, int endCellColumn) {
        if (!map.isInMap(startCellRow, startCellColumn) || !map.isInMap(endCellRow, endCellColumn))
            return false;
        return isReachable(map.getCell(startCellRow, startCellColumn), map.getCell(endCellRow, endCellColumn));
    }

    @Override
    public int manhattanDistance(Cell startCell, Cell endCell) {
        return Math.abs(startCell.getRow() - endCell.getRow()) + Math.abs(startCell.getColumn() - endCell.getColumn());
    }

    @Override
    public int manhattanDistance(int startCellRow, int startCellColumn, int endCellRow, int endCellColumn) {
        if (!map.isInMap(startCellRow, startCellColumn) || !map.isInMap(endCellRow, endCellColumn)) return -1;
        return manhattanDistance(map.getCell(startCellRow, startCellColumn), map.getCell(endCellRow, endCellColumn));
    }

    public Cell[] getImpactCells(AbilityName abilityName, Cell startCell, Cell targetCell) {
        AbilityConstants abilityConstants = getAbilityConstants(abilityName);
        if ((!abilityConstants.isLobbing() && startCell.isWall()) || startCell == targetCell) {
            return new Cell[]{startCell};
        }
        ArrayList<Cell> impactCells = new ArrayList<>();
        Cell[] rayCells = getRayCells(startCell, targetCell, abilityConstants.isLobbing());
        Cell lastCell = null;
        for (Cell cell : rayCells) {
            if (manhattanDistance(startCell, cell) > abilityConstants.getRange())
                break;
            lastCell = cell;
            if ((getOppHero(cell) != null && !abilityConstants.getType().equals(AbilityType.DEFENSIVE))
                    || (getMyHero(cell) != null && abilityConstants.getType().equals(AbilityType.DEFENSIVE))) {
                impactCells.add(cell);
                if (!abilityConstants.isLobbing()) break;
            }
        }
        if (!impactCells.contains(lastCell))
            impactCells.add(lastCell);
        return impactCells.toArray(new Cell[0]);
    }

    public Cell[] getImpactCells(Ability ability, Cell startCell, Cell targetCell) {
        return getImpactCells(ability.getName(), startCell, targetCell);
    }

    public Cell[] getImpactCells(AbilityName abilityName, int startCellRow, int startCellColumn,
                                 int targetCellRow, int targetCellColumn) {
        if (!map.isInMap(startCellRow, startCellColumn) || !map.isInMap(targetCellRow, targetCellColumn))
            return new Cell[0];
        Cell startCell = map.getCell(startCellRow, startCellColumn);
        Cell targetCell = map.getCell(targetCellRow, targetCellColumn);
        return getImpactCells(abilityName, startCell, targetCell);
    }

    public Cell[] getImpactCells(Ability ability, int startCellRow, int startCellColumn,
                                 int targetCellRow, int targetCellColumn) {
        if (!map.isInMap(startCellRow, startCellColumn) || !map.isInMap(targetCellRow, targetCellColumn))
            return new Cell[0];
        Cell startCell = map.getCell(startCellRow, startCellColumn);
        Cell targetCell = map.getCell(targetCellRow, targetCellColumn);
        return getImpactCells(ability.getName(), startCell, targetCell);
    }

    @Override
    public Cell getImpactCell(AbilityName abilityName, Cell startCell, Cell targetCell) {
        Cell[] impactCells = getImpactCells(abilityName, startCell, targetCell);
        return impactCells[impactCells.length - 1];
    }

    @Override
    public Cell getImpactCell(AbilityName abilityName, int startCellRow, int startCellColumn, int endCellRow,
                              int endCellColumn) {
        if (!map.isInMap(startCellRow, startCellColumn) || !map.isInMap(endCellRow, endCellColumn)) return null;
        return getImpactCell(abilityName, map.getCell(startCellRow, startCellColumn), map.getCell(endCellRow,
                endCellColumn));
    }

    @Override
    public Cell getImpactCell(Ability ability, Cell startCell, Cell targetCell) {
        return getImpactCell(ability.getAbilityConstants().getName(), startCell, targetCell);
    }

    @Override
    public Cell getImpactCell(Ability ability, int startCellRow, int startCellColumn, int endCellRow,
                              int endCellColumn) {
        if (!map.isInMap(startCellRow, startCellColumn) || !map.isInMap(endCellRow, endCellColumn)) return null;
        return getImpactCell(ability.getAbilityConstants().getName(), map.getCell(startCellRow, startCellColumn),
                map.getCell(endCellRow, endCellColumn));
    }

    /*TODO*/// handle isLobbing
    @Override
    public Hero[] getAbilityTargets(AbilityName abilityName, Cell startCell, Cell targetCell) {
        AbilityConstants abilityConstants = getAbilityConstants(abilityName);
        Cell[] impactCells = getImpactCells(abilityName, startCell, targetCell);
        ArrayList<Cell> affectedCells = getCellsInAOE(impactCells[impactCells.length - 1],
                abilityConstants.getAreaOfEffect());
        if (abilityConstants.getType() == AbilityType.DEFENSIVE) {
            return getMyHeroesInCells(affectedCells.toArray(new Cell[0]));
        } else {
            return getOppHeroesInCells(affectedCells.toArray(new Cell[0]));
        }
    }

    @Override
    public Hero[] getAbilityTargets(Ability ability, Cell startCell, Cell targetCell) {
        return getAbilityTargets(ability.getName(), startCell, targetCell);
    }

    @Override
    public Hero[] getAbilityTargets(AbilityName abilityName, int startCellRow, int startCellColumn, int targetCellRow,
                                    int targetCellColumn) {
        if (!map.isInMap(startCellRow, startCellColumn) || !map.isInMap(targetCellRow, targetCellColumn))
            return new Hero[0];
        return getAbilityTargets(abilityName, map.getCell(startCellRow, startCellColumn), map.getCell(targetCellRow,
                targetCellColumn));
    }

    @Override
    public Hero[] getAbilityTargets(Ability ability, int startCellRow, int startCellColumn, int targetCellRow,
                                    int targetCellColumn) {
        if (!map.isInMap(startCellRow, startCellColumn) || !map.isInMap(targetCellRow, targetCellColumn))
            return new Hero[0];
        return getAbilityTargets(ability.getName(), map.getCell(startCellRow, startCellColumn),
                map.getCell(targetCellRow, targetCellColumn));
    }

    private ArrayList<Cell> getCellsInAOE(Cell impactCell, int AOE) {
        ArrayList<Cell> cells = new ArrayList<>();
        for (int row = impactCell.getRow() - AOE; row <= impactCell.getRow() + AOE; row++) {
            for (int col = impactCell.getColumn() - AOE; col <= impactCell.getColumn() + AOE; col++) {
                if (!map.isInMap(row, col)) continue;
                Cell cell = map.getCell(row, col);
                if (manhattanDistance(impactCell, cell) <= AOE)
                    cells.add(cell);
            }
        }
        return cells;
    }

    private Hero[] getOppHeroesInCells(Cell[] cells) {
        ArrayList<Hero> heroes = new ArrayList<>();
        for (Cell cell : cells) {
            Hero hero = getOppHero(cell);
            if (hero != null) {
                heroes.add(hero);
            }
        }
        return heroes.toArray(new Hero[0]);
    }

    private Hero[] getMyHeroesInCells(Cell[] cells) {
        ArrayList<Hero> heroes = new ArrayList<>();
        for (Cell cell : cells) {
            Hero hero = getMyHero(cell);
            if (hero != null) {
                heroes.add(hero);
            }
        }
        return heroes.toArray(new Hero[0]);
    }

    /**
     * Get all the cells that collide with the ray line in at least one non corner point, before reaching a wall.
     * If it hits a wall cell just in the corner, it would also stop too.
     *
     * @param startCell
     * @param targetCell
     * @return
     */
    private Cell[] getRayCells(Cell startCell, Cell targetCell, boolean wallPiercing) {
        ArrayList<Cell> path = new ArrayList<>();
        dfs(startCell, startCell, targetCell, new HashMap<>(), path, wallPiercing);
        return path.toArray(new Cell[0]);
    }

    private void dfs(Cell currentCell, Cell startCell, Cell targetCell, HashMap<Cell, Boolean> isSeen,
                     ArrayList<Cell> path, boolean wallPiercing) {
        isSeen.put(currentCell, true);
        path.add(currentCell);
        for (Direction direction : Direction.values()) {
            Cell nextCell = getNextCell(currentCell, direction);
            if (nextCell != null && !isSeen.containsKey(nextCell) && isCloser(currentCell, targetCell, nextCell)) {
                int collisionState = squareCollision(startCell, targetCell, nextCell);
                if ((collisionState == 0 || collisionState == 1) && (!wallPiercing && nextCell.isWall()))
                    return;
                if (collisionState == 1) {
                    dfs(nextCell, startCell, targetCell, isSeen, path, wallPiercing);
                    return;
                }
            }
        }
        for (int dRow = -1; dRow <= 1; dRow += 2)
            for (int dColumn = -1; dColumn <= 1; dColumn += 2) {
                int newRow = currentCell.getRow() + dRow;
                int newColumn = currentCell.getColumn() + dColumn;
                Cell nextCell = null;
                if (map.isInMap(newRow, newColumn)) nextCell = map.getCell(newRow, newColumn);
                if (nextCell != null && !isSeen.containsKey(nextCell) && isCloser(currentCell, targetCell, nextCell)) {
                    int collisionState = squareCollision(startCell, targetCell, nextCell);
                    if (collisionState == 0 || collisionState == 1 && (!wallPiercing && nextCell.isWall()))
                        return;
                    if (collisionState == 1) {
                        dfs(nextCell, startCell, targetCell, isSeen, path, wallPiercing);
                    }
                }
            }
    }

    private boolean isCloser(Cell currentCell, Cell targetCell, Cell nextCell) {
        return manhattanDistance(nextCell, targetCell) <= manhattanDistance(currentCell, targetCell);
    }

    /**
     * Checks the state of collision between the start cell to target cell line and cell square.
     * -1 -> doesn't pass through square at all
     * 0 -> passes through just one corner
     * 1 -> passes through the square
     *
     * @param startCell
     * @param targetCell
     * @param cell
     * @return
     */
    private int squareCollision(Cell startCell, Cell targetCell, Cell cell) {
        boolean hasNegative = false;
        boolean hasPositive = false;
        boolean hasZero = false;
        for (int row = 2 * cell.getRow(); row <= 2 * (cell.getRow() + 1); row += 2)
            for (int column = 2 * cell.getColumn(); column <= 2 * (cell.getColumn() + 1); column += 2) {
                int crossProduct = crossProduct(2 * startCell.getRow() + 1, 2 * startCell.getColumn() + 1,
                        2 * targetCell.getRow() + 1, 2 * targetCell.getColumn() + 1, row, column);
                if (crossProduct < 0) hasNegative = true;
                else if (crossProduct > 0) hasPositive = true;
                else hasZero = true;
            }
        if (hasNegative && hasPositive) return 1;
        if (hasZero) return 0;
        return -1;
    }

    /**
     * This method calculates the cross product.
     * negative return value -> point1-point2 line is on the left side of point1-point3 line
     * zero return value -> the three points lie on the same line
     * positive return value -> point1-point2 line is on the right side of point1-point3 line
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x3
     * @param y3
     * @return
     */
    private int crossProduct(int x1, int y1, int x2, int y2, int x3, int y3) {
        return (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
    }

    private Cell getNextCell(Cell cell, Direction direction) {
        switch (direction) {
            case UP:
                if (map.isInMap(cell.getRow() - 1, cell.getColumn()))
                    return map.getCell(cell.getRow() - 1, cell.getColumn());
                else
                    return null;
            case DOWN:
                if (map.isInMap(cell.getRow() + 1, cell.getColumn()))
                    return map.getCell(cell.getRow() + 1, cell.getColumn());
                else
                    return null;
            case LEFT:
                if (map.isInMap(cell.getRow(), cell.getColumn() - 1))
                    return map.getCell(cell.getRow(), cell.getColumn() - 1);
                else
                    return null;
            case RIGHT:
                if (map.isInMap(cell.getRow(), cell.getColumn() + 1))
                    return map.getCell(cell.getRow(), cell.getColumn() + 1);
                else
                    return null;
        }
        return null; // never happens
    }


    @Override
    public boolean isInVision(Cell startCell, Cell endCell) {
        if (startCell.isWall() || endCell.isWall())
            return false;
        Cell[] rayCells = getRayCells(startCell, endCell, false);
        Cell lastCell = rayCells[rayCells.length - 1];
        return lastCell == endCell;
    }

    @Override
    public boolean isInVision(int startCellRow, int startCellColumn, int endCellRow, int endCellColumn) {
        if (!map.isInMap(startCellRow, startCellColumn) || !map.isInMap(endCellRow, endCellColumn)) return false;
        return isInVision(map.getCell(startCellRow, startCellColumn), map.getCell(endCellRow, endCellColumn));
    }

    @Override
    public Hero[] getMyHeroes() {
        return myHeroes;
    }

    void setMyHeroes(Hero[] myHeroes) {
        this.myHeroes = myHeroes;
    }

    @Override
    public Hero[] getOppHeroes() {
        return oppHeroes;
    }

    void setOppHeroes(Hero[] oppHeroes) {
        this.oppHeroes = oppHeroes;
    }

    @Override
    public Hero[] getMyDeadHeroes() {
        return myDeadHeroes;
    }

    void setMyDeadHeroes(Hero[] myDeadHeroes) {
        this.myDeadHeroes = myDeadHeroes;
    }

    @Override
    public Hero[] getOppDeadHeroes() {
        return oppDeadHeroes;
    }

    void setOppDeadHeroes(Hero[] oppDeadHeroes) {
        this.oppDeadHeroes = oppDeadHeroes;
    }

    @Override
    public Map getMap() {
        return map;
    }

    void setMap(Map map) {
        this.map = map;
    }

    @Override
    public CastAbility[] getMyCastAbilities() {
        return myCastAbilities;
    }

    void setMyCastAbilities(CastAbility[] myCastAbilities) {
        this.myCastAbilities = myCastAbilities;
    }

    public GameConstants getGameConstants() {
        return gameConstants;
    }

    void setGameConstants(GameConstants gameConstants) {
        this.gameConstants = gameConstants;
    }

    @Override
    public HeroConstants[] getHeroConstants() {
        return heroConstants;
    }

    void setHeroConstants(HeroConstants[] heroConstants) {
        this.heroConstants = heroConstants;
    }

    @Override
    public AbilityConstants[] getAbilityConstants() {
        return abilityConstants;
    }

    void setAbilityConstants(AbilityConstants[] abilityConstants) {
        this.abilityConstants = abilityConstants;
    }

    @Override
    public CastAbility[] getOppCastAbilities() {
        return oppCastAbilities;
    }

    void setOppCastAbilities(CastAbility[] oppCastAbilities) {
        this.oppCastAbilities = oppCastAbilities;
    }

    @Override
    public int getAP() {
        return AP;
    }

    void setAP(int AP) {
        this.AP = AP;
    }

    @Override
    public int getMyScore() {
        return myScore;
    }

    void setMyScore(int myScore) {
        this.myScore = myScore;
    }

    @Override
    public int getOppScore() {
        return oppScore;
    }

    void setOppScore(int oppScore) {
        this.oppScore = oppScore;
    }

    @Override
    public int getCurrentTurn() {
        return currentTurn;
    }

    void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    @Override
    public Phase getCurrentPhase() {
        return currentPhase;
    }

    void setCurrentPhase(Phase currentPhase) {
        this.currentPhase = currentPhase;
    }

    @Override
    public int getNormalTimeout() {
        return gameConstants.getNormalTimeout();
    }

    void setTimeout(int timeout) {
        gameConstants.setNormalTimeout(timeout);
    }

    @Override
    public int getMaxAP() {
        return gameConstants.getMaxAP();
    }

    void setMaxAP(int maxAP) {
        gameConstants.setMaxAP(maxAP);
    }

    @Override
    public int getMaxTurns() {
        return gameConstants.getMaxTurns();
    }

    void setMaxTurns(int maxTurns) {
        gameConstants.setMaxTurns(maxTurns);
    }

    @Override
    public int getKillScore() {
        return gameConstants.getKillScore();
    }

    void setKillScore(int killScore) {
        gameConstants.setKillScore(killScore);
    }

    @Override
    public int getObjectiveZoneScore() {
        return gameConstants.getObjectiveZoneScore();
    }

    void setObjectiveZoneScore(int objectiveZoneScore) {
        gameConstants.setObjectiveZoneScore(objectiveZoneScore);
    }

    @Override
    public int getMaxScore() {
        return gameConstants.getMaxScore();
    }

    void setMaxScore(int maxScore) {
        gameConstants.setMaxScore(maxScore);
    }

    @Override
    public int getMovePhaseNum() {
        return movePhaseNum;
    }

    public int getPreprocessTimeout() {
        return gameConstants.getPreprocessTimeout();
    }

    public int getFirstMoveTimeout() {
        return gameConstants.getFirstMoveTimeout();
    }
}
