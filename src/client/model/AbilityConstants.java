package client.model;

public class AbilityConstants
{
    private AbilityName name;
    private String type;
    private int range;
    private int APCost;
    private int cooldown;

    public AbilityName getName()
    {
        return name;
    }

    public void setName(AbilityName name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public int getRange()
    {
        return range;
    }

    public void setRange(int range)
    {
        this.range = range;
    }

    public int getAPCost()
    {
        return APCost;
    }

    public void setAPCost(int APCost)
    {
        this.APCost = APCost;
    }

    public int getCooldown()
    {
        return cooldown;
    }

    public void setCooldown(int cooldown)
    {
        this.cooldown = cooldown;
    }
}
