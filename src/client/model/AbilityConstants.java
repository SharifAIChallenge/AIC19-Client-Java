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

    void setName(AbilityName name) {
        this.name = name;
    }

    public AbilityType getType() {
        return type;
    }

    void setType(AbilityType type) {
        this.type = type;
    }

    public int getRange() {
        return range;
    }

    void setRange(int range) {
        this.range = range;
    }

    public int getAPCost() {
        return APCost;
    }

    void setAPCost(int APCost) {
        this.APCost = APCost;
    }

    public int getCooldown() {
        return cooldown;
    }

    void setCooldown(int cooldown) {

        this.cooldown = cooldown;
    }

    public int getAreaOfEffect() {
        return areaOfEffect;
    }

    void setAreaOfEffect(int areaOfEffect) {
        this.areaOfEffect = areaOfEffect;
    }

    public int getPower() {
        return power;
    }

    void setPower(int power) {
        this.power = power;
    }

    public boolean isLobbing() {
        return isLobbing;
    }

    void setLobbing(boolean lobbing) {
        isLobbing = lobbing;
    }

    public boolean isPiercing() {
        return isPiercing;
    }

    void setPiercing(boolean piercing) {
        isPiercing = piercing;
    }

    @Override
    public String toString()
    {
        return "AbilityConstants{" +
                "name=" + name +
                ", type=" + type +
                ", range=" + range +
                ", APCost=" + APCost +
                ", cooldown=" + cooldown +
                ", areaOfEffect=" + areaOfEffect +
                ", power=" + power +
                ", isLobbing=" + isLobbing +
                '}';
    }
}
