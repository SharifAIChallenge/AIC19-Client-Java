package client.model;

import java.util.Collection;

public interface World {

    /**
     * Returns the hero with given id.
     * Returns null in case it doesn't exist.
     *
     * @param id Id of hero
     * @return
     */
    Hero getHero(int id);


    /**
     * If one of our heroes is in this specific cell the method returns that hero, if none of our heroes are in
     * this cell the method returns null.
     *
     * @param cell Specific cell we want to get the hero of
     * @return
     */
    Hero getMyHero(Cell cell);

    Hero getMyHero(int cellRow, int cellColumn);


    /**
     * If one of enemy heroes is in this specific cell the method returns that hero, if none of enemy heroes are in
     * this cell the method returns null.
     *
     * @param cell Specific cell we want to get the hero of
     * @return
     */
    Hero getOppHero(Cell cell);

    Hero getOppHero(int cellRow, int cellColumn);

    /**
     * Cast one of this hero's abilities.
     * It can only be used in ACTION phase otherwise it won't do anything.
     *
     * @param heroId           Id of hero
     * @param abilityName      Name of the ability we want to cast
     * @param targetCellRow    Row number of the cell we want to cast the ability to
     * @param targetCellColumn Column number of the cell we want to cast the ability to
     */
    void castAbility(int heroId, AbilityName abilityName, int targetCellRow, int targetCellColumn);

    void castAbility(int heroId, AbilityName abilityName, Cell targetCell);

    void castAbility(Hero hero, AbilityName abilityName, Cell targetCell);

    void castAbility(Hero hero, AbilityName abilityName, int targetCellRow, int targetCellColumn);

    void castAbility(int heroId, Ability ability, Cell targetCell);

    void castAbility(int heroId, Ability ability, int targetCellRow, int targetCellColumn);

    void castAbility(Hero hero, Ability ability, Cell targetCell);

    void castAbility(Hero hero, Ability ability, int targetCellRow, int targetCellColumn);

    /**
     * Moves the hero in the given direction.
     * It can only be used in MOVE phase otherwise it won't do anything.
     *
     * @param heroId    Id of hero
     * @param direction The direction we want the hero to move, it has one of UP, DOWN, LEFT or RIGHT values.
     */
    void moveHero(int heroId, Direction direction);

    void moveHero(Hero hero, Direction direction);


    /**
     * Picks one of the heroes in the game for you.
     * It can only be used in PICK phase otherwise it won't do anything.
     *
     * @param heroName Type of one the heroes in the game
     */
    void pickHero(HeroName heroName);

    Direction[] getPathMoveDirections(Cell startCell, Cell endCell, Cell[] blockedCells);

    Direction[] getPathMoveDirections(int startCellRow, int startCellColumn, int endCellRow, int endCellColumn,
                                      Cell[] blockedCells);

    Direction[] getPathMoveDirections(Cell startCell, Cell endCell, Collection<Cell> blockedCells);

    Direction[] getPathMoveDirections(int startCellRow, int startCellColumn, int endCellRow, int endCellColumn,
                                      Collection<Cell> blockedCells);

    /**
     * Returns an array of directions to reach the end cell from start cell.
     * In case of start cell and end cell being the same cells, return empty array.
     * If there is no path, return empty array.
     *
     * @param startCell The cell we start path from
     * @param endCell   The cell we want to reach
     * @return
     */
    Direction[] getPathMoveDirections(Cell startCell, Cell endCell);

    Direction[] getPathMoveDirections(int startCellRow, int startCellColumn, int endCellRow, int endCellColumn);


    /**
     * Returns the Manhattan distance between two cells.
     * It is actually the difference between row number and column number of the cells.
     * Returns zero if one of the inputs is null.
     *
     * @param firstCell  First cell
     * @param secondCell Second cell
     * @return
     */
    int manhattanDistance(Cell firstCell, Cell secondCell);

    int manhattanDistance(int firstCellRow, int firstCellColumn, int secondCellRow, int secondCellColumn);

    /**
     * Returns the cell that our ability will land on.
     * If the ability is a lobbing ability it would pass all the walls and hits the last cell in ability's range.
     * Otherwise it may hit a wall or a target hero and land on the cell before it, or if there is no wall or hero,
     * it would also hit the last cell in ability's range.
     *
     * @param abilityName Name of the ability we want to cast
     * @param startCell   The cell we want to cast from
     * @param targetCell  The cell we are aiming to
     * @return
     */
    Cell getImpactCell(AbilityName abilityName, Cell startCell, Cell targetCell);

    Cell getImpactCell(AbilityName abilityName, int startCellRow, int startCellColumn, int endCellRow,
                       int endCellColumn);

    Cell getImpactCell(Ability ability, Cell startCell, Cell targetCell);

    Cell getImpactCell(Ability ability, int startCellRow, int startCellColumn, int endCellRow,
                       int endCellColumn);

    /**
     * Returns all the heroes an ability will affect, if we cast it from a specified cell to another.
     * For OFFENSIVE or DODGE abilities it will return enemy heroes,
     * and for DEFENSIVE abilities it will return ally heroes in cells around the ability's impact cell
     * (If they are in ability's AOE).
     *
     * @param abilityName Name of the ability we want to cast
     * @param startCell   The cell we want to cast from
     * @param targetCell  The cell we are aiming to
     * @return
     */
    Hero[] getAbilityTargets(AbilityName abilityName, Cell startCell, Cell targetCell);

    Hero[] getAbilityTargets(Ability ability, Cell startCell, Cell targetCell);

    Hero[] getAbilityTargets(AbilityName abilityName, int startCellRow, int startCellColumn, int targetCellRow,
                             int targetCellColumn);

    Hero[] getAbilityTargets(Ability ability, int startCellRow, int startCellColumn, int targetCellRow,
                             int targetCellColumn);

    /**
     * The return value is true if we have vision from the start cell to end cell.
     * The true value means that if there is an ally hero in start cell and an opponent hero in end cell,
     * we will know he's there.
     *
     * @param startCell The cell we want to check the vision from
     * @param endCell   The cell we want to check the vision to
     * @return
     */
    boolean isInVision(Cell startCell, Cell endCell);

    boolean isInVision(int startCellRow, int startCellColumn, int endCellRow, int endCellColumn);

    Hero[] getMyHeroes();

    Hero[] getOppHeroes();

    Hero[] getMyDeadHeroes();

    Hero[] getOppDeadHeroes();

    Map getMap();

    // Returns abilities cast in the last round by your own heroes.
    CastAbility[] getMyCastAbilities();

    // Returns abilities cast in the last round by enemy heroes.
    CastAbility[] getOppCastAbilities();

    HeroConstants[] getHeroConstants();

    AbilityConstants[] getAbilityConstants();

    int getAP();

    int getMyScore();

    int getOppScore();

    int getCurrentTurn();

    Phase getCurrentPhase();

    int getNormalTimeout();

    int getPreprocessTimeout();

    int getFirstMoveTimeout();

    int getMaxAP();

    int getMaxTurns();

    int getKillScore();

    int getObjectiveZoneScore();

    int getMaxScore();

    int getMovePhaseNum();

    int getMaxOvertime();

    int getRemainingOvertime();

    int getInitOvertime();

    int getMaxScoreDiff();

    int getTotalMovePhases();
}
