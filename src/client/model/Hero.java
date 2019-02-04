package client.model;


import java.util.ArrayList;

public class Hero {
    private int id;
    private HeroConstants heroConstants;
    private Ability[] abilities;
    private Ability[] dashAbilities;
    private Ability[] healAbilities;
    private Ability[] attackAbilities;
    private int currentHP;
    private int remRespawnTime;
    private Cell currentCell;
    private Cell[] recentPath;

    Hero(HeroConstants heroConstants, int id, ArrayList<Ability> abilities) {
        this.id = id;
        this.heroConstants = heroConstants;
        ArrayList<Ability> dashAbilities = new ArrayList<>();
        ArrayList<Ability> healAbilities = new ArrayList<>();
        ArrayList<Ability> attackAbilities = new ArrayList<>();
        for (Ability ability : abilities) {
            switch (ability.getAbilityConstants().getType()) {
                case HEAL:
                    healAbilities.add(ability);
                    break;
                case DASH:
                    dashAbilities.add(ability);
                    break;
                case ATTACK:
                    attackAbilities.add(ability);
                    break;
            }
        }
        this.abilities = abilities.toArray(new Ability[0]);
        this.dashAbilities = dashAbilities.toArray(new Ability[0]);
        this.healAbilities = healAbilities.toArray(new Ability[0]);
        this.attackAbilities = attackAbilities.toArray(new Ability[0]);
        currentHP = heroConstants.getMaxHP();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Hero hero = (Hero) obj;
        return id == hero.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public Ability getAbility(AbilityName abilityName) {
        for (Ability ability : abilities) {
            if (ability.getName() == abilityName) {
                return ability;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    HeroConstants getHeroConstants() {
        return heroConstants;
    }

    void setHeroConstants(HeroConstants heroConstants) {
        this.heroConstants = heroConstants;
    }

    public Ability[] getAbilities() {
        return abilities;
    }

    void setAbilities(Ability[] abilities) {
        this.abilities = abilities;
    }

    public Ability[] getDashAbilities() {
        return dashAbilities;
    }

    void setDashAbilities(Ability[] dashAbilities) {
        this.dashAbilities = dashAbilities;
    }

    public Ability[] getHealAbilities() {
        return healAbilities;
    }

    void setHealAbilities(Ability[] healAbilities) {
        this.healAbilities = healAbilities;
    }

    public Ability[] getAttackAbilities() {
        return attackAbilities;
    }

    void setAttackAbilities(Ability[] attackAbilities) {
        this.attackAbilities = attackAbilities;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    public Cell[] getRecentPath() {
        return recentPath;
    }

    void setRecentPath(Cell[] recentPath) {
        this.recentPath = recentPath;
    }

    public HeroName getName() {
        return heroConstants.getName();
    }

    void setName(HeroName name) {
        heroConstants.setName(name);
    }

    public AbilityName[] getAbilityNames() {
        return heroConstants.getAbilityNames();
    }

    void setAbilityNames(AbilityName[] abilityNames) {
        heroConstants.setAbilityNames(abilityNames);
    }

    public int getMaxHP() {
        return heroConstants.getMaxHP();
    }

    void setMaxHP(int maxHP) {
        heroConstants.setMaxHP(maxHP);
    }

    public int getMoveAPCost() {
        return heroConstants.getMoveAPCost();
    }

    void setMoveAPCost(int moveAPCost) {
        heroConstants.setMoveAPCost(moveAPCost);
    }

    public int getHeroConstantsRespawnTime() {
        return heroConstants.getRespawnTime();
    }

    void setHeroConstantsRespawnTime(int respawnTime) {
        heroConstants.setRespawnTime(respawnTime);
    }

    public int getRemRespawnTime() {
        return remRespawnTime;
    }

    void setRemRespawnTime(int remRespawnTime) {
        this.remRespawnTime = remRespawnTime;
    }
}

