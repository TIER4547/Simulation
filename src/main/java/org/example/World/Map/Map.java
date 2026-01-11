package org.example.World.Map;

import org.example.World.Entity;

import java.util.*;

public class Map {
    private final java.util.Map<Coordinates, Entity> mapByCoordinates = new HashMap<>();
    private final java.util.Map<Entity, Coordinates>  mapByEntities = new HashMap<>();
    private final int width;
    private final int height;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Entity getEntity(Coordinates coordinates) {
        Entity entity = mapByCoordinates.get(coordinates);
        return  entity;
    }
    public Coordinates getEntityCoordinates(Entity entity){
        Coordinates coordinates = mapByEntities.get(entity);
        return coordinates;
    }

    public boolean isInside(Coordinates coordinates){
        return coordinates.x >= 0 && coordinates.x < width &&
                coordinates.y >= 0 && coordinates.y < height;

    }

    public boolean isCoordinateEmpty(Coordinates coordinates){
        return !mapByCoordinates.containsKey(coordinates);
    }

    public void setEntities(Entity entity, Coordinates coordinates){
        if (!isInside(coordinates)) {
            throw new IllegalStateException("Coordinates outside map: " + coordinates);
        }
        mapByCoordinates.put(coordinates, entity);
        mapByEntities.put(entity, coordinates);
    }

    public void removeEntityAndCoordinates(Coordinates coordinates,Entity entity){
        mapByCoordinates.remove(coordinates);
        mapByEntities.remove(entity);
    }

    public Collection<Entity> getEntities(){
        return mapByCoordinates.values();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}


