package client.model;

public class CastAbility {
    private int casterId;
    private int[] targetHeroIds;
    private Cell startCell;
    private Cell endCell;
    private AbilityName abilityName;

    public int getCasterId() {
        return casterId;
    }

    public void setCasterId(int casterId) {
        this.casterId = casterId;
    }

    public int[] getTargetHeroIds() {
        return targetHeroIds;
    }

    public void setTargetHeroIds(int[] targetHeroIds) {
        this.targetHeroIds = targetHeroIds;
    }

    public Cell getStartCell() {
        return startCell;
    }

    public void setStartCell(Cell startCell) {
        this.startCell = startCell;
    }

    public Cell getEndCell() {
        return endCell;
    }

    public void setEndCell(Cell endCell) {
        this.endCell = endCell;
    }

    public AbilityName getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(AbilityName abilityName) {
        this.abilityName = abilityName;
    }
}
