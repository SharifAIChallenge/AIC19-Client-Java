//package client.model;
//
//import com.google.gson.JsonObject;
//import common.network.Json;
//
//public class Test {
//    public static void main(String[] args) {
//        Game game = new Game();
//        Map map = new Map();
//        map.setRowNum(5);
//        map.setColumnNum(5);
//        Cell[][] cells = new Cell[5][5];
//        for(int i=0;i<5;i++){
//            for(int j=0;j<5;j++){
//                Cell cell = new Cell();
//                cell.setColumn(j);
//                cell.setRow(i);
//                if(i == 2 && j == 3){
//                    cell.setWall(true);
//                }else
//                {
//                    cell.setWall(false);
//                }
//                cells[i][j]=cell;
//            }
//        }
//        map.setCells(cells);
//        game.setMap(map);
//        for(Cell cell:game.getRayCells(cells[1][1],cells[3][4])){
//            System.out.println(cell.getRow()+" "+cell.getColumn());
//        }
//    }
//
//    private static void jsonTest2() {
//        JsonObject jsonObject = new JsonObject();
//        jsonObject.add("rowNum", Json.GSON.toJsonTree(10));
//        jsonObject.add("columnNum",Json.GSON.toJsonTree(10));
//        Map map = Json.GSON.fromJson(jsonObject,Map.class);
//        System.out.println(Json.GSON.toJson(map));
//    }
//
//    private static void jsonTest() {
//        HeroConstants heroConstants = new HeroConstants();
//        heroConstants.setAbilityNames(AbilityName.values());
//        heroConstants.setMaxHP(100);
//        heroConstants.setMoveAPCost(300);
//        heroConstants.setName(HeroName.HEALER);
//        JsonObject j = Json.GSON.toJsonTree(heroConstants).getAsJsonObject();
//
//        HeroConstants heroConstants2 = Json.GSON.fromJson(j, HeroConstants.class);
//        System.out.println(Json.GSON.toJson(heroConstants2));
//    }
//}
