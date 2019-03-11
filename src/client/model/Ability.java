package client.model;

public class Ability {

    private AbilityConstants abilityConstants;
    private int remCooldown;

    Ability(AbilityConstants abilityConstants) {
        this.abilityConstants = abilityConstants;
        this.remCooldown = 0;
    }

    public boolean isReady() {
        return this.remCooldown == 0;
    }

    AbilityConstants getAbilityConstants() {
        return abilityConstants;
    }

    void setAbilityConstants(AbilityConstants abilityConstants) {
        this.abilityConstants = abilityConstants;
    }

    public int getRemCooldown() {
        return remCooldown;
    }

    void setRemCooldown(int remCooldown) {
        this.remCooldown = remCooldown;
    }

    public AbilityName getName() {
        return abilityConstants.getName();
    }

    void setName(AbilityName name) {
        abilityConstants.setName(name);
    }

    public AbilityType getType() {
        return abilityConstants.getType();
    }

    void setType(AbilityType type) {
        abilityConstants.setType(type);
    }

    public int getRange() {
        return abilityConstants.getRange();
    }

    void setRange(int range) {
        abilityConstants.setRange(range);
    }

    public int getAPCost() {
        return abilityConstants.getAPCost();
    }

    void setAPCost(int APCost) {
        abilityConstants.setAPCost(APCost);
    }

    public int getCooldown() {
        return abilityConstants.getCooldown();
    }

    void setCooldown(int cooldown) {
        abilityConstants.setCooldown(cooldown);
    }

    public int getAreaOfEffect() {
        return abilityConstants.getAreaOfEffect();
    }

    void setAreaOfEffect(int areaOfEffect) {
        abilityConstants.setAreaOfEffect(areaOfEffect);
    }

    public int getPower() {
        return abilityConstants.getPower();
    }

    void setPower(int power) {
        abilityConstants.setPower(power);
    }

    public boolean isLobbing() {
        return abilityConstants.isLobbing();
    }

    void setLobbing(boolean lobbing) {
        abilityConstants.setLobbing(lobbing);
    }

    public boolean isPiercing() {
        return abilityConstants.isPiercing();
    }
}
