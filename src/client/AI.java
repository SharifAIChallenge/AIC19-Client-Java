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
//        world.pickHero(HeroName.values()[world.getCurrentTurn() + 1]);
        world.pickHero(HeroName.SHADOW);
    }

    public void moveTurn(World world)
    {
        System.out.println("move started");
//        Hero[] heroes = world.getMyHeroes();
//        Cell[] objective = world.getMap().getObjectiveZone();
        Map map = world.getMap();

        if (world.getCurrentTurn() > 10)
        {
            Hero[] targets = world.getAbilityTargets(AbilityName.SHADOW_SLASH, map.getCell(29, 1),
                    map.getCell(29, 3));
            for (Hero hero : targets)
            {
                System.out.println(hero);
            }
            return;
        }

        for (Hero myHero : world.getMyHeroes())
        {
            world.moveHero(myHero, Direction.LEFT);
        }

//        for (Hero hero : heroes)
//        {
//            Direction[] directions = world.getPathMoveDirections(hero.getCurrentCell(),
//                    objective[random.nextInt(objective.length)]);
//            if (directions.length == 0)
//                continue;
//            world.moveHero(hero, directions[0]);
//        }
    }

    public void actionTurn(World world)
    {
        System.out.println("action started");
//        System.out.println("Turn: " + world.getCurrentTurn());
//        System.out.println("Max overtime: " + world.getMaxOvertime());
//        System.out.println("Remaining overtime: " + world.getRemainingOvertime());
//        System.out.println("My score: " + world.getMyScore());
//        System.out.println("Opp score: " + world.getOppScore());
//        System.out.println("Used ap: " + world.getAP());
//        System.out.println("Total move phases: " + world.getTotalMovePhases());
//
//        Random random = new Random();
//        Cell[] objectives = world.getMap().getObjectiveZone();
//        for (Hero hero : world.getMyHeroes())
//        {
//            if (hero.getName() == HeroName.SHADOW && world.getCurrentTurn() > 40)
//                castSlash(hero, world);
//            else
//                world.castAbility(hero, hero.getDodgeAbilities()[0], objectives[random.nextInt(objectives.length)]);
//        }
//
//        Hero[] myHeroes = world.getMyHeroes();
//        for (Hero hero : myHeroes)
//        {
//            switch (hero.getName())
//            {
//                case SENTRY:
//                    sentryCast(hero, world);
//                    break;
//                case BLASTER:
//                    blasterCast(hero, world);
//                    break;
//                case HEALER:
//                    healerCast(hero, world);
//                    break;
//                case GUARDIAN:
//                    guardianCast(hero, world);
//                    break;
//            }
//        }


//        Map map = world.getMap();
//
//        for (int i = 0; i < map.getRowNum(); i++)
//        {
//            for (int j = 0; j < map.getColumnNum(); j++)
//            {
//                Cell cell = map.getCell(i, j);
//                String ids = "";
//                Hero myHero = world.getMyHero(cell);
//                Hero oppHero = world.getOppHero(cell);
//                if (myHero != null)
//                    ids += myHero.getId();
//                if (oppHero != null)
//                    ids += oppHero.getId();
//                if (!ids.equals(""))
//                {
//                    System.out.print(ids + "\t");
//                    continue;
//                }
//
//                if (cell.isWall())
//                    System.out.print("W\t");
//                else if (cell.isInMyRespawnZone())
//                    System.out.print("M\t");
//                else if (cell.isInOppRespawnZone())
//                    System.out.print("O\t");
//                else if (cell.isInVision())
//                    System.out.print(":\t");
//                else
//                    System.out.print(".\t");
//            }
//            System.out.println();
//        }
    }

    private void castSlash(Hero hero, World world)
    {
        Hero[] oppHeroes = world.getOppHeroes();
        Cell[] objective = world.getMap().getObjectiveZone();

        for (Hero oppHero : oppHeroes)
        {
            if (oppHero.getCurrentHP() <= 0)
                continue;

            Hero[] targets = world.getAbilityTargets(AbilityName.SHADOW_SLASH, hero.getCurrentCell(), oppHero.getCurrentCell());
            if (targets.length == 0)
                continue;

            world.castAbility(hero, AbilityName.SHADOW_SLASH, oppHero.getCurrentCell());
        }
        world.castAbility(hero, AbilityName.SHADOW_SLASH, objective[random.nextInt(objective.length)]);
    }

    private void guardianCast(Hero hero, World world)
    {
        Hero target = findLowestHPHero(world.getMyHeroes());
        if (target == null)
            return;

        world.castAbility(hero, AbilityName.GUARDIAN_FORTIFY, target.getCurrentCell());
        world.castAbility(hero, AbilityName.GUARDIAN_ATTACK, target.getCurrentCell());
    }

    private void healerCast(Hero hero, World world)
    {
        Hero healTarget = findLowestHPHero(world.getMyHeroes());
        Hero attackTarget = findHighestHPHero(world.getOppHeroes());
        if (healTarget == null || attackTarget == null)
            return;

        if (healTarget.getCurrentHP() != healTarget.getMaxHP())
            world.castAbility(hero, AbilityName.HEALER_HEAL, healTarget.getCurrentCell());
        world.castAbility(hero, AbilityName.HEALER_ATTACK, attackTarget.getCurrentCell());
    }

    private void blasterCast(Hero hero, World world)
    {
        Hero target = findLowestHPHero(world.getOppHeroes());
        if (target == null)
            return;

        world.castAbility(hero, AbilityName.BLASTER_BOMB, target.getCurrentCell());
        world.castAbility(hero, AbilityName.BLASTER_ATTACK, target.getCurrentCell());
    }

    private Hero findLowestHPHero(Hero[] possibleTargets)
    {
        int health = Integer.MAX_VALUE;
        Hero target = null;

        for (Hero hero : possibleTargets)
        {
            if (hero.getCurrentHP() > 0 && hero.getCurrentHP() < health)
            {
                target = hero;
                health = hero.getCurrentHP();
            }
        }

        return target;
    }

    private void sentryCast(Hero hero, World world)
    {
        Hero target = findHighestHPHero(world.getOppHeroes());
        if (target == null)
            return;

        world.castAbility(hero, AbilityName.SENTRY_RAY, target.getCurrentCell());
        world.castAbility(hero, AbilityName.SENTRY_ATTACK, target.getCurrentCell());
    }

    private Hero findHighestHPHero(Hero[] possibleHeroes)
    {
        int health = Integer.MIN_VALUE;
        Hero target = null;

        for (Hero hero : possibleHeroes)
        {
            if (hero.getCurrentHP() > 0 && hero.getCurrentHP() > health)
            {
                target = hero;
                health = hero.getCurrentHP();
            }
        }

        return target;
    }

}