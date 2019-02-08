package client.model;


import java.util.ArrayList;

public class Hero
{
    private int id;
    private HeroConstants heroConstants;
    private Ability[] abilities;
    private Ability[] dodgeAbilities;
    private Ability[] defensiveAbilities;
    private Ability[] offensiveAbilities;
    private int currentHP;
    private int remRespawnTime;
    private Cell currentCell;
    private Cell[] recentPath;

    Hero(HeroConstants heroConstants, int id, ArrayList<Ability> abilities)
    {
        this.id = id;
        this.heroConstants = heroConstants;
        ArrayList<Ability> dodgeAbilities = new ArrayList<>();
        ArrayList<Ability> defensiveAbilities = new ArrayList<>();
        ArrayList<Ability> offensiveAbilities = new ArrayList<>();
        for (Ability ability : abilities)
        {
            switch (ability.getAbilityConstants().getType())
            {
                case DEFENSIVE:
                    defensiveAbilities.add(ability);
                    break;
                case DODGE:
                    dodgeAbilities.add(ability);
                    break;
                case OFFENSIVE:
                    offensiveAbilities.add(ability);
                    break;
            }
        }
        this.abilities = abilities.toArray(new Ability[0]);
        this.dodgeAbilities = dodgeAbilities.toArray(new Ability[0]);
        this.defensiveAbilities = defensiveAbilities.toArray(new Ability[0]);
        this.offensiveAbilities = offensiveAbilities.toArray(new Ability[0]);
        currentHP = heroConstants.getMaxHP();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Hero hero = (Hero) obj;
        return id == hero.id;
    }

    @Override
    public int hashCode()
    {
        return id;
    }

    public Ability getAbility(AbilityName abilityName)
    {
        for (Ability ability : abilities)
        {
            if (ability.getName() == abilityName)
            {
                return ability;
            }
        }
        return null;
    }

    public int getId()
    {
        return id;
    }

    void setId(int id)
    {
        this.id = id;
    }

    HeroConstants getHeroConstants()
    {
        return heroConstants;
    }

    void setHeroConstants(HeroConstants heroConstants)
    {
        this.heroConstants = heroConstants;
    }

    public Ability[] getAbilities()
    {
        return abilities;
    }

    void setAbilities(Ability[] abilities)
    {
        this.abilities = abilities;
    }

    public Ability[] getDodgeAbilities()
    {
        return dodgeAbilities;
    }

    void setDodgeAbilities(Ability[] dodgeAbilities)
    {
        this.dodgeAbilities = dodgeAbilities;
    }

    public Ability[] getDefensiveAbilities()
    {
        return defensiveAbilities;
    }

    void setDefensiveAbilities(Ability[] defensiveAbilities)
    {
        this.defensiveAbilities = defensiveAbilities;
    }

    public Ability[] getOffensiveAbilities()
    {
        return offensiveAbilities;
    }

    void setOffensiveAbilities(Ability[] offensiveAbilities)
    {
        this.offensiveAbilities = offensiveAbilities;
    }

    public int getCurrentHP()
    {
        return currentHP;
    }

    void setCurrentHP(int currentHP)
    {
        this.currentHP = currentHP;
    }

    public Cell getCurrentCell()
    {
        return currentCell;
    }

    void setCurrentCell(Cell currentCell)
    {
        this.currentCell = currentCell;
    }

    public Cell[] getRecentPath()
    {
        return recentPath;
    }

    void setRecentPath(Cell[] recentPath)
    {
        this.recentPath = recentPath;
    }

    public HeroName getName()
    {
        return heroConstants.getName();
    }

    void setName(HeroName name)
    {
        heroConstants.setName(name);
    }

    public AbilityName[] getAbilityNames()
    {
        return heroConstants.getAbilityNames();
    }

    void setAbilityNames(AbilityName[] abilityNames)
    {
        heroConstants.setAbilityNames(abilityNames);
    }

    public int getMaxHP()
    {
        return heroConstants.getMaxHP();
    }

    void setMaxHP(int maxHP)
    {
        heroConstants.setMaxHP(maxHP);
    }

    public int getMoveAPCost()
    {
        return heroConstants.getMoveAPCost();
    }

    void setMoveAPCost(int moveAPCost)
    {
        heroConstants.setMoveAPCost(moveAPCost);
    }

    public int getHeroConstantsRespawnTime()
    {
        return heroConstants.getRespawnTime();
    }

    void setHeroConstantsRespawnTime(int respawnTime)
    {
        heroConstants.setRespawnTime(respawnTime);
    }

    public int getRemRespawnTime()
    {
        return remRespawnTime;
    }

    void setRemRespawnTime(int remRespawnTime)
    {
        this.remRespawnTime = remRespawnTime;
    }

    @Override
    public String toString()
    {
        if (currentCell == null)
            return "";

        return "Hero{" +
                "id=" + id +
                ", heroName=" + heroConstants.getName() +
                ", abilities=" + getAbilitiesLog() +
                ", currentHP=" + currentHP +
                ", remRespawnTime=" + remRespawnTime +
                ", currentCell={" + currentCell.getRow() + ", " + currentCell.getColumn() + "}" +
                ", recentPath=" + getRecentPathLog() +
                '}';
    }

    private String getRecentPathLog()
    {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < recentPath.length; i++)
        {
            Cell cell = recentPath[i];
            result.append("{" + cell.getRow() + ", " + cell.getColumn() + "}");
            if (i != recentPath.length - 1)
            {
                result.append(", ");
            }
        }
        result.append("]");
        return String.valueOf(result);
    }

    private String getAbilitiesLog()
    {
        if (recentPath == null)
            return "";

        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < abilities.length; i++)
        {
            Ability ability = abilities[i];
            result.append("{" + ability.getName() + ", " + ability.getRemCooldown() + "}");
            if (i != recentPath.length - 1)
            {
                result.append(", ");
            }
        }
        result.append("]");
        return String.valueOf(result);
    }
}

