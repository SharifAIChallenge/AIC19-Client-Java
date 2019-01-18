package client.model;

import com.google.gson.JsonObject;
import common.network.Json;

public class Test {
    public static void main(String[] args) {

    }

    private static void jsonTest2() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("rowNum", Json.GSON.toJsonTree(10));
        jsonObject.add("columnNum",Json.GSON.toJsonTree(10));
        Map map = Json.GSON.fromJson(jsonObject,Map.class);
        System.out.println(Json.GSON.toJson(map));
    }

    private static void jsonTest() {
        HeroConstants heroConstants = new HeroConstants();
        heroConstants.setAbilityNames(AbilityName.values());
        heroConstants.setMaxHP(100);
        heroConstants.setMoveAPCost(300);
        heroConstants.setName(HeroName.HEALER);
        JsonObject j = Json.GSON.toJsonTree(heroConstants).getAsJsonObject();

        HeroConstants heroConstants2 = Json.GSON.fromJson(j, HeroConstants.class);
        System.out.println(Json.GSON.toJson(heroConstants2));
    }
}
