package client.model;

public class Ability {

    AbilityConstants abilityConstants;
    private int remCooldown;

    Ability(AbilityConstants abilityConstants) {
        this.abilityConstants = abilityConstants;
        this.remCooldown = 0;
    }

    public AbilityConstants getAbilityConstants() {
        return abilityConstants;
    }

    public void setAbilityConstants(AbilityConstants abilityConstants) {
        this.abilityConstants = abilityConstants;
    }

    public int getRemCooldown() {
        return remCooldown;
    }

    public void setRemCooldown(int remCooldown) {
        this.remCooldown = remCooldown;
    }
}
