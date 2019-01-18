package client.model;

public class AbilityConstants {
    private AbilityName name;
    private AbilityType type;
    private int range;
    private int APCost;
    private int cooldown;
    private int areaOfEffect;
    private int power;
    private boolean isLobbing;
    private boolean isPiercing;

    public AbilityName getName() {
        return name;
    }

    public void setName(AbilityName name) {
        this.name = name;
    }

    public AbilityType getType() {
        return type;
    }

    public void setType(AbilityType type) {
        this.type = type;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getAPCost() {
        return APCost;
    }

    public void setAPCost(int APCost) {
        this.APCost = APCost;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {

        this.cooldown = cooldown;
    }

    public int getAreaOfEffect() {
        return areaOfEffect;
    }

    public void setAreaOfEffect(int areaOfEffect) {
        this.areaOfEffect = areaOfEffect;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public boolean isLobbing() {
        return isLobbing;
    }

    public void setLobbing(boolean lobbing) {
        isLobbing = lobbing;
    }

    public boolean isPiercing() {
        return isPiercing;
    }

    public void setPiercing(boolean piercing) {
        this.isPiercing = piercing;
    }
}
