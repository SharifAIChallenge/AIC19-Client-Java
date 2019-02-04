package client.model;

public class InitJson {
    private Map map;
    private GameConstants gameConstants;
    private HeroConstants[] heroConstants;
    private AbilityConstants[] abilityConstants;

    public Map getMap() {
        return map;
    }

    void setMap(Map map) {
        this.map = map;
    }

    public GameConstants getGameConstants() {
        return gameConstants;
    }

    void setGameConstants(GameConstants gameConstants) {
        this.gameConstants = gameConstants;
    }

    public HeroConstants[] getHeroConstants() {
        return heroConstants;
    }

    void setHeroConstants(HeroConstants[] heroConstants) {
        this.heroConstants = heroConstants;
    }

    public AbilityConstants[] getAbilityConstants() {
        return abilityConstants;
    }

    void setAbilityConstants(AbilityConstants[] abilityConstants) {
        this.abilityConstants = abilityConstants;
    }
}
