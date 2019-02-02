package client;

import client.model.Direction;
import client.model.World;
import client.model.Hero;
import client.model.HeroName;

public class AI
{

    public void preProcess(World World)
    {
        System.out.println("Process started");
    }

    public void pickTurn(World World)
    {
        World.pickHero(HeroName.Ruhollah);
    }

    public void moveTurn(World World)
    {
        System.out.println("move started");
        Hero myHero = World.getMyHeroes()[0];

        World.moveHero(myHero.getId(), new Direction[]{Direction.UP, Direction.UP, Direction.LEFT});
    }

    public void actionTurn(World World)
    {
        System.out.println("action started");
        Hero myHero = World.getMyHeroes()[0];
        World.castAbility(myHero, myHero.getAbilities()[0], myHero.getCurrentCell());
    }

}
