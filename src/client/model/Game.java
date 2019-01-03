package client.model;

import common.network.data.Message;

import java.util.function.Consumer;

public class Game
{
    private Hero[] myHeroes, oppHeroes;
    private Hero[] myDeadHeroes, oppDeadHeroes;
    private Map map;
    private Constants constants;
    private int AP, score;


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
        for(int i=0;i<myHeroes.length;i++)
        {
            if(myHeroes[i].getId()==id)
                return myHeroes[i];
        }
        for(int i=0;i<oppHeroes.length;i++)
        {
            if(oppHeroes[i].getId()==id)
                return oppHeroes[i];
        }
        return null;
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

    public Constants getConstants()
    {
        return constants;
    }

    public void setConstants(Constants constants)
    {
        this.constants = constants;
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
