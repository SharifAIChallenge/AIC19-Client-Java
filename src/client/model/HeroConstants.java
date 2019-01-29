package client.model;

public class HeroConstants
{
    private HeroName name;
    private AbilityName[] abilityNames;
    private int maxHP;
    private int moveAPCost;
    private int respawnTime;

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

    public int getRespawnTime() {
        return respawnTime;
    }

    public void setRespawnTime(int respawnTime) {
        this.respawnTime = respawnTime;
    }
}
