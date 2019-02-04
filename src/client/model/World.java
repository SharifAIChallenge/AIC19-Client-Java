package client.model;

public interface World {
    Hero getHero(int id);

    Hero getMyHero(Cell cell);

    Hero getMyHero(int cellRow, int cellColumn);

    Hero getOppHero(Cell cell);

    Hero getOppHero(int cellRow, int cellColumn);

    void castAbility(int heroId, AbilityName abilityName, int targetCellRow, int targetCellColumn);

    void castAbility(int heroId, AbilityName abilityName, Cell targetCell);

    void castAbility(Hero hero, AbilityName abilityName, Cell targetCell);

    void castAbility(Hero hero, AbilityName abilityName, int targetCellRow, int targetCellColumn);

    void castAbility(int heroId, Ability ability, Cell targetCell);

    void castAbility(int heroId, Ability ability, int targetCellRow, int targetCellColumn);

    void castAbility(Hero hero, Ability ability, Cell targetCell);

    void castAbility(Hero hero, Ability ability, int targetCellRow, int targetCellColumn);

    void moveHero(int heroId, Direction direction);

    void moveHero(Hero hero, Direction direction);

    void pickHero(HeroType heroType);

/*
    boolean isAccessible(int cellRow, int cellColumn);//TODO delete

    boolean isAccessible(Cell cell);
*/

    Direction[] getPathMoveDirections(Cell startCell, Cell endCell);

    Direction[] getPathMoveDirections(int startCellRow, int startCellColumn, int endCellRow, int endCellColumn);

/*
    boolean isReachable(Cell startCell, Cell targetCell);

    boolean isReachable(int startCellRow, int startCellColumn, int endCellRow, int endCellColumn); //TODO delete
*/

    int manhattanDistance(Cell startCell, Cell endCell);

    int manhattanDistance(int startCellRow, int startCellColumn, int endCellRow, int endCellColumn);

    /*Cell[] getImpactCells(AbilityName abilityName, Cell startCell, Cell targetCell);//TODO delete

    Cell[] getImpactCells(Ability ability, Cell startCell, Cell targetCell);

    Cell[] getImpactCells(AbilityName abilityName, int startCellRow, int startCellColumn,
                          int targetCellRow, int targetCellColumn);

    Cell[] getImpactCells(Ability ability, int startCellRow, int startCellColumn,
                          int targetCellRow, int targetCellColumn);*/

    Cell getImpactCell(AbilityName abilityName, Cell startCell, Cell targetCell);

    Cell getImpactCell(AbilityName abilityName, int startCellRow, int startCellColumn, int endCellRow,
                       int endCellColumn);

    Cell getImpactCell(Ability ability, Cell startCell, Cell targetCell);

    Cell getImpactCell(Ability ability, int startCellRow, int startCellColumn, int endCellRow,
                       int endCellColumn);

    Hero[] getAbilityTargets(AbilityName abilityName, Cell startCell, Cell targetCell);

    Hero[] getAbilityTargets(Ability ability, Cell startCell, Cell targetCell);

    Hero[] getAbilityTargets(AbilityName abilityName, int startCellRow, int startCellColumn, int targetCellRow,
                             int targetCellColumn);

    Hero[] getAbilityTargets(Ability ability, int startCellRow, int startCellColumn, int targetCellRow,
                             int targetCellColumn);

    boolean isInVision(Cell startCell, Cell endCell);

    boolean isInVision(int startCellRow, int startCellColumn, int endCellRow, int endCellColumn);

    Hero[] getMyHeroes();

    Hero[] getOppHeroes();

    Hero[] getMyDeadHeroes();

    Hero[] getOppDeadHeroes();

    Map getMap();

    CastAbility[] getMyCastAbilities();

    CastAbility[] getOppCastAbilities();

//    GameConstants getGameConstants(); //TODO delete

    HeroConstants[] getHeroConstants();

    AbilityConstants[] getAbilityConstants();

    int getAP();

    int getMyScore();

    int getOppScore();

    int getCurrentTurn();

    Phase getCurrentPhase();

    int getTimeout();//TODO

    int getMaxAP();

    int getMaxTurns();

    int getKillScore();

    int getObjectiveZoneScore();

    int getMaxScore();//TODO

    int getMovePhaseNum();
}
