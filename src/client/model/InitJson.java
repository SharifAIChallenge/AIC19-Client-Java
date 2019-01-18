package client.model;

public class InitJson {
    private Map map;
    private GameConstants gameConstants;
    private HeroConstants[] heroes;
    private AbilityConstants[] abilities;

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public GameConstants getGameConstants() {
        return gameConstants;
    }

    public void setGameConstants(GameConstants gameConstants) {
        this.gameConstants = gameConstants;
    }

    public HeroConstants[] getHeroes() {
        return heroes;
    }

    public void setHeroes(HeroConstants[] heroes) {
        this.heroes = heroes;
    }

    public AbilityConstants[] getAbilities() {
        return abilities;
    }

    public void setAbilities(AbilityConstants[] abilities) {
        this.abilities = abilities;
    }
}
