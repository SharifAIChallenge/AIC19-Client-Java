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

    public AbilityName getName() {
        return abilityConstants.getName();
    }

    public void setName(AbilityName name) {
        abilityConstants.setName(name);
    }

    public AbilityType getType() {
        return abilityConstants.getType();
    }

    public void setType(AbilityType type) {
        abilityConstants.setType(type);
    }

    public int getRange() {
        return abilityConstants.getRange();
    }

    public void setRange(int range) {
        abilityConstants.setRange(range);
    }

    public int getAPCost() {
        return abilityConstants.getAPCost();
    }

    public void setAPCost(int APCost) {
        abilityConstants.setAPCost(APCost);
    }

    public int getCooldown() {
        return abilityConstants.getCooldown();
    }

    public void setCooldown(int cooldown) {
        abilityConstants.setCooldown(cooldown);
    }

    public int getAreaOfEffect() {
        return abilityConstants.getAreaOfEffect();
    }

    public void setAreaOfEffect(int areaOfEffect) {
        abilityConstants.setAreaOfEffect(areaOfEffect);
    }

    public int getPower() {
        return abilityConstants.getPower();
    }

    public void setPower(int power) {
        abilityConstants.setPower(power);
    }

    public boolean isLobbing() {
        return abilityConstants.isLobbing();
    }

    public void setLobbing(boolean lobbing) {
        abilityConstants.setLobbing(lobbing);
    }

    public boolean isPiercing() {
        return abilityConstants.isPiercing();
    }

    public void setPiercing(boolean piercing) {
        abilityConstants.setPiercing(piercing);
    }
}
