package client.model;

public abstract class Ability
{

    AbilityConstants abilityConstants;
    private int remCooldown;

    public AbilityConstants getAbilityConstants()
    {
        return abilityConstants;
    }

    public void setAbilityConstants(AbilityConstants abilityConstants)
    {
        this.abilityConstants = abilityConstants;
    }

    public int getRemCooldown()
    {
        return remCooldown;
    }

    public void setRemCooldown(int remCooldown)
    {
        this.remCooldown = remCooldown;
    }
}
