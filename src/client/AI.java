package client;

import client.model.Direction;
import client.model.Game;
import client.model.Hero;
import client.model.HeroName;

public class AI
{

    public void preProcess(Game game)
    {
        System.out.println("Process started");
    }

    public void pickTurn(Game game)
    {
        System.out.println("pick started");

        game.pickHero(HeroName.Ruhollah);
    }

    public void moveTurn(Game game)
    {
        System.out.println("move started");
        Hero myHero = game.getMyHeroes()[0];

        game.moveHero(myHero.getId(), new Direction[]{Direction.UP, Direction.UP, Direction.LEFT});
    }

    public void actionTurn(Game game)
    {
        System.out.println("action started");
        Hero myHero = game.getMyHeroes()[0];
        game.castAbility(myHero, myHero.getAbilities()[0], myHero.getCurrentCell());
    }

}
