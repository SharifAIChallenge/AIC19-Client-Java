package client;

import client.model.Direction;
import client.model.Hero;
import client.model.HeroName;
import client.model.World;

import java.util.Random;

public class AI {

    public void preProcess(World world) {
        System.out.println("pre process started");
        world.pickHero(HeroName.GUARDIAN);
        world.pickHero(HeroName.HEALER);
        world.castAbility(1, world.getAbilityConstants()[0].getName(), 1, 1);
        world.moveHero(1, Direction.DOWN);
    }

    public void pickTurn(World world) {
        System.out.println("pick started");
        int numberOfHeroes = world.getMyHeroes().length;
        world.pickHero(HeroName.values()[numberOfHeroes % (HeroName.values().length)]);
    }

    public void moveTurn(World world) {
        System.out.println("move started");
        for (Hero hero : world.getMyHeroes()) {
            world.moveHero(hero.getId(), Direction.DOWN);
        }
    }

    public void actionTurn(World world) {
        System.out.println("action started");
        for (Hero hero : world.getMyHeroes()) {
            world.castAbility(hero, hero.getAbilities()[Math.abs(new Random().nextInt()) % hero.getAbilities().length],
                    world.getMap().getCell(Math.abs(new Random().nextInt()) % world.getMap().getRowNum(),
                            Math.abs(new Random().nextInt()) % world.getMap().getColumnNum()));
        }
    }

}
