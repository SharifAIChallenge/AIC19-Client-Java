package client.model;

import java.util.Arrays;

public class HeroConstants {
    private HeroName name;
    private AbilityName[] abilityNames;
    private int maxHP;
    private int moveAPCost;
    private int respawnTime;

    public HeroName getName() {
        return name;
    }

    void setName(HeroName name) {
        this.name = name;
    }

    public AbilityName[] getAbilityNames() {
        return abilityNames;
    }

    void setAbilityNames(AbilityName[] abilityNames) {
        this.abilityNames = abilityNames;
    }

    public int getMaxHP() {
        return maxHP;
    }

    void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getMoveAPCost() {
        return moveAPCost;
    }

    void setMoveAPCost(int moveAPCost) {
        this.moveAPCost = moveAPCost;
    }

    public int getRespawnTime() {
        return respawnTime;
    }

    void setRespawnTime(int respawnTime) {
        this.respawnTime = respawnTime;
    }

    @Override
    public String toString()
    {
        return "HeroConstants{" +
                "name=" + name +
                ", abilityNames=" + Arrays.toString(abilityNames) +
                ", maxHP=" + maxHP +
                ", moveAPCost=" + moveAPCost +
                ", respawnTime=" + respawnTime +
                '}';
    }
}
