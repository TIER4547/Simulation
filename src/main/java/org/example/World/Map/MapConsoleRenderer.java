package org.example.World.Map;

import org.example.World.Color;
import org.example.World.Entity;

public class MapConsoleRenderer {
    public static final String ANSI_WHITE_SQUARE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_RESET = "\u001B[0m";


    public void render(Map map) {
        for (int y  = 0; y < map.getHeight(); y++) {
            String line = " ";
            for (int x = 0; x < map.getWidth(); x++) {
                Coordinates coordinates = new Coordinates(x, y);
                if (map.isCoordinateEmpty(coordinates)) {
                    line += getSpriteForEmptyCoordinates(coordinates);
                } else {
                    Entity entity = map.getEntity(coordinates);
                    line += getSpriteForEntities(entity);
                }
            }
            line += ANSI_RESET;
            System.out.println(line);
        }
        System.out.println();

    }

    private String getSpriteForEmptyCoordinates(Coordinates coordinates){
        return colorizeSprite("   " , Color.WHITE);
    }
    private String getSpriteForEntities(Entity entities){
        return colorizeSprite("" + selectUnicodeSpriteForEntities(entities) + " ", Color.WHITE);
    }

    private String selectUnicodeSpriteForEntities(Entity entity){
        switch (entity.getClass().getSimpleName()){
            case "Wolf" :
                return "\uD83D\uDC3A";
            case "Sheep":
                return "\uD83D\uDC11";
            case "Tree":
                return "\uD83C\uDF32";
            case "Rock":
                return "⛰\uFE0F";
            case "Grass":
                return "\uD83C\uDF31";
        }
        return "";

    }
    private String colorizeSprite(String sprite, Color color) {
        String result = sprite;
        if (color == Color.WHITE){
            result = ANSI_WHITE_SQUARE_BACKGROUND + result;
        }
        return result;
    }
}
