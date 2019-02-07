package client;

import client.model.Cell;
import client.model.Direction;
import client.model.HeroName;
import client.model.World;

public class AI {

    public void preProcess(World world) {
        System.out.println("pre process started");
        /*world.pickHero(HeroName.GUARDIAN);
        world.pickHero(HeroName.HEALER);
        world.castAbility(1, world.getAbilityConstants()[0].getName(), 1, 1);
        world.moveHero(1, Direction.DOWN);
        */
    }

    public void pickTurn(World world) {
        //System.out.println("pick started");
        int numberOfHeroes = world.getMyHeroes().length;
        world.pickHero(HeroName.values()[numberOfHeroes % (HeroName.values().length)]);
    }

    public void moveTurn(World world) {
        /*System.out.println("move started");
        for (Hero hero : world.getMyHeroes()) {
            world.moveHero(hero.getId(), Direction.DOWN);
        }*/
        Cell startCell = world.getMyHeroes()[0].getCurrentCell();
        Cell endCell = world.getMap().getObjectiveZone()[0];
        Direction[] directions = world.getPathMoveDirections(startCell, endCell);
        System.err.println("StartCell: " + startCell.getRow() + " " + startCell.getColumn() + " " + startCell.isWall());
        System.err.println("EndCell: " + endCell.getRow() + " " + endCell.getColumn() + " " + endCell.isWall());
        System.err.println(directions.length);

    }

    public void actionTurn(World world) {
        /*System.out.println("action started");
        for (Hero hero : world.getMyHeroes()) {
            world.castAbility(hero, hero.getAbilities()[(new Random().nextInt()) % hero.getAbilities().length],
                    world.getMap().getCell((new Random().nextInt()) % world.getMap().getRowNum(),
                            (new Random().nextInt()) % world.getMap().getColumnNum()));
        }*/
    }

}
