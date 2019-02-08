package client.model;

import java.util.Arrays;

public class CastAbility {
    private int casterId;
    private int[] targetHeroIds;
    private Cell startCell;
    private Cell endCell;
    private AbilityName abilityName;

    public int getCasterId() {
        return casterId;
    }

    void setCasterId(int casterId) {
        this.casterId = casterId;
    }

    public int[] getTargetHeroIds() {
        return targetHeroIds;
    }

    void setTargetHeroIds(int[] targetHeroIds) {
        this.targetHeroIds = targetHeroIds;
    }

    public Cell getStartCell() {
        return startCell;
    }

    void setStartCell(Cell startCell) {
        this.startCell = startCell;
    }

    public Cell getEndCell() {
        return endCell;
    }

    void setEndCell(Cell endCell) {
        this.endCell = endCell;
    }

    public AbilityName getAbilityName() {
        return abilityName;
    }

    void setAbilityName(AbilityName abilityName) {
        this.abilityName = abilityName;
    }

    @Override
    public String toString()
    {
        return "CastAbility{" +
                "casterId=" + casterId +
                ", targetHeroIds=" + Arrays.toString(targetHeroIds) +
                ", startCell=" + startCell +
                ", endCell=" + endCell +
                ", abilityName=" + abilityName +
                '}';
    }
}
