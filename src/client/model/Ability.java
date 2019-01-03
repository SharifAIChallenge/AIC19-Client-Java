package client.model;

public abstract class Ability
{
    AbilityConstants abilityConstants;
    private int remCooldown;

    public int getRemCooldown()
    {
        return remCooldown;
    }

    public void setRemCooldown(int remCooldown)
    {
        this.remCooldown = remCooldown;
    }
}
