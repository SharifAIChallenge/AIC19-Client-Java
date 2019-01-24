package client.model;


import java.util.ArrayList;

public class Hero {
    private int id;
    private HeroConstants heroConstants;
    private Ability[] abilities;
    private Ability[] dodgeAbilities;
    private Ability[] healAbilities;
    private Ability[] attackAbilities;
    private int currentHP;
    private int respawnTime;
    private Cell currentCell;
    private Cell[] recentPath;

    Hero(HeroConstants heroConstants, int id, ArrayList<Ability> abilities) {
        this.id = id;
        this.heroConstants = heroConstants;
        ArrayList<Ability> dodgeAbilities = new ArrayList<>();
        ArrayList<Ability> healAbilities = new ArrayList<>();
        ArrayList<Ability> attackAbilities = new ArrayList<>();
        for (Ability ability : abilities) {
            switch (ability.getAbilityConstants().getType()) {
                case HEAL:
                    healAbilities.add(ability);
                    break;
                case DODGE:
                    dodgeAbilities.add(ability);
                    break;
                case ATTACK:
                    attackAbilities.add(ability);
                    break;
            }
        }
        this.abilities = abilities.toArray(new Ability[0]);
        this.dodgeAbilities = dodgeAbilities.toArray(new Ability[0]);
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

    public Ability getAbility(AbilityName abilityName)
    {
        for(Ability ability:abilities)
        {
            if(ability.getName()==abilityName)
            {
                return ability;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HeroConstants getHeroConstants() {
        return heroConstants;
    }

    public void setHeroConstants(HeroConstants heroConstants) {
        this.heroConstants = heroConstants;
    }

    public Ability[] getAbilities() {
        return abilities;
    }

    public void setAbilities(Ability[] abilities) {
        this.abilities = abilities;
    }

    public Ability[] getDodgeAbilities() {
        return dodgeAbilities;
    }

    public void setDodgeAbilities(Ability[] dodgeAbilities) {
        this.dodgeAbilities = dodgeAbilities;
    }

    public Ability[] getHealAbilities() {
        return healAbilities;
    }

    public void setHealAbilities(Ability[] healAbilities) {
        this.healAbilities = healAbilities;
    }

    public Ability[] getAttackAbilities() {
        return attackAbilities;
    }

    public void setAttackAbilities(Ability[] attackAbilities) {
        this.attackAbilities = attackAbilities;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    public Cell[] getRecentPath() {
        return recentPath;
    }

    public void setRecentPath(Cell[] recentPath) {
        this.recentPath = recentPath;
    }

    public HeroName getName() {
        return heroConstants.getName();
    }

    public void setName(HeroName name) {
        heroConstants.setName(name);
    }

    public AbilityName[] getAbilityNames() {
        return heroConstants.getAbilityNames();
    }

    public void setAbilityNames(AbilityName[] abilityNames) {
        heroConstants.setAbilityNames(abilityNames);
    }

    public int getMaxHP() {
        return heroConstants.getMaxHP();
    }

    public void setMaxHP(int maxHP) {
        heroConstants.setMaxHP(maxHP);
    }

    public int getMoveAPCost() {
        return heroConstants.getMoveAPCost();
    }

    public void setMoveAPCost(int moveAPCost) {
        heroConstants.setMoveAPCost(moveAPCost);
    }

    public int getRespawnTime() {
        return respawnTime;
    }

    public void setRespawnTime(int respawnTime) {
        this.respawnTime = respawnTime;
    }
}
