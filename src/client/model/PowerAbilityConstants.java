package client.model;

public class PowerAbilityConstants extends AbilityConstants
{
    private int areaOfEffect;
    private int power;
    private boolean isLobbing;

    public int getAreaOfEffect()
    {
        return areaOfEffect;
    }

    public void setAreaOfEffect(int areaOfEffect)
    {
        this.areaOfEffect = areaOfEffect;
    }

    public int getPower()
    {
        return power;
    }

    public void setPower(int power)
    {
        this.power = power;
    }

    public boolean isLobbing()
    {
        return isLobbing;
    }

    public void setLobbing(boolean lobbing)
    {
        isLobbing = lobbing;
    }
}
