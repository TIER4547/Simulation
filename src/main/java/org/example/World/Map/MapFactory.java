package org.example.World.Map;

import org.example.World.Creatures.Animals.Sheep;
import org.example.World.Creatures.Animals.Wolf;
import org.example.World.Entity;
import org.example.World.Objects.Grass;
import org.example.World.Objects.Rock;
import org.example.World.Objects.Tree;

import java.util.Random;

public class MapFactory {
    private final Random random = new Random();
    private final Map map = new Map(10, 10);

    private Coordinates getRandomFreeCoordinate(){
        Coordinates randomCoordinates;
        int x = random.nextInt(map.getWidth());
        int y = random.nextInt(map.getHeight());
        randomCoordinates = new Coordinates(x, y);
        return randomCoordinates;
    }

     public Map spawnRandomEntities(){
        for (int i = 0; i < 12; i ++){
            map.setEntities(getRandomEntities(), getRandomFreeCoordinate());
        }
        return map;
    }

    public Entity getRandomEntities(){
        int value = random.nextInt(5);
        switch (value){
            case 0 -> {
                return new Tree();
            }
            case 1 -> {
                return new Rock();
            }
            case 2 -> {
                return new Grass();
            }
            case 3 -> {
                return new Sheep();
            }
            case 4 -> {
                return new Wolf();
            }
            default -> throw new IllegalStateException();
        }
    }
}
