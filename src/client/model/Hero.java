package client.model;


import java.util.Objects;

public class Hero
{
    private int id;
    private HeroConstants heroConstants;
    private Ability[] abilities;
    private DodgeAbility[] dodgeAbilities;
    private PowerAbility[] healAbilities;
    private PowerAbility[] attackAbilities;
    private int currentHP;
    private Cell currentCell;
    private Cell[] recentPath;

    @Override
    public boolean equals(Object obj)
    {
        if(this==obj)
            return true;
        if(obj==null)
            return false;
        if(getClass()!=obj.getClass())
            return false;
        Hero hero=(Hero)obj;
        return Objects.equals(id,hero.id);
    }

    @Override
    public int hashCode()
    {
        return id;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id=id;
    }

    public HeroConstants getHeroConstants()
    {
        return heroConstants;
    }

    public void setHeroConstants(HeroConstants heroConstants)
    {
        this.heroConstants=heroConstants;
    }

    public Ability[] getAbilities()
    {
        return abilities;
    }

    public void setAbilities(Ability[] abilities)
    {
        this.abilities = abilities;
    }

    public DodgeAbility[] getDodgeAbilities()
    {
        return dodgeAbilities;
    }

    public void setDodgeAbilities(DodgeAbility[] dodgeAbilities)
    {
        this.dodgeAbilities = dodgeAbilities;
    }

    public PowerAbility[] getHealAbilities()
    {
        return healAbilities;
    }

    public void setHealAbilities(PowerAbility[] healAbilities)
    {
        this.healAbilities = healAbilities;
    }

    public PowerAbility[] getAttackAbilities()
    {
        return attackAbilities;
    }

    public void setAttackAbilities(PowerAbility[] attackAbilities)
    {
        this.attackAbilities = attackAbilities;
    }

    public int getCurrentHP()
    {
        return currentHP;
    }

    public void setCurrentHP(int currentHP)
    {
        this.currentHP = currentHP;
    }

    public Cell getCurrentCell()
    {
        return currentCell;
    }

    public void setCurrentCell(Cell currentCell)
    {
        this.currentCell = currentCell;
    }

    public Cell[] getRecentPath()
    {
        return recentPath;
    }

    public void setRecentPath(Cell[] recentPath)
    {
        this.recentPath = recentPath;
    }
}
