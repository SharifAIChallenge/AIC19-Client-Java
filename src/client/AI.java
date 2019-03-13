package client;

import client.model.*;

import java.util.Random;

public class AI
{

    private Random random = new Random();

    public void preProcess(World world)
    {
        System.out.println("pre process started");
    }

    public void pickTurn(World world)
    {
        System.out.println("pick started");
        world.pickHero(HeroName.values()[world.getCurrentTurn()]);
    }

    public void moveTurn(World world)
    {
        System.out.println("move started");
        Hero[] heroes = world.getMyHeroes();

        for (Hero hero : heroes)
        {
            world.moveHero(hero, Direction.values()[random.nextInt(4)]);
        }
    }

    public void actionTurn(World world) {
        System.out.println("action started");
        Hero[] heroes = world.getMyHeroes();
        Map map = world.getMap();
        for (Hero hero : heroes)
        {
            int row = random.nextInt(map.getRowNum());
            int column = random.nextInt(map.getColumnNum());

            world.castAbility(hero, hero.getAbilities()[random.nextInt(3)], row, column);
        }
    }

}