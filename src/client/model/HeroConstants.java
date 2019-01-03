package client.model;

public class HeroConstants
{
    private HeroName name;
    private AbilityName[] abilityNames;
    private int maxHP;
    private int moveAPCost;

    public HeroName getName()
    {
        return name;
    }

    public void setName(HeroName name)
    {
        this.name = name;
    }

    public AbilityName[] getAbilityNames()
    {
        return abilityNames;
    }

    public void setAbilityNames(AbilityName[] abilityNames)
    {
        this.abilityNames = abilityNames;
    }

    public int getMaxHP()
    {
        return maxHP;
    }

    public void setMaxHP(int maxHP)
    {
        this.maxHP = maxHP;
    }

    public int getMoveAPCost()
    {
        return moveAPCost;
    }

    public void setMoveAPCost(int moveAPCost)
    {
        this.moveAPCost = moveAPCost;
    }
}
