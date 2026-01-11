package org.example.World.Creatures;

import org.example.InteractionResult;
import org.example.World.Map.CoordinatesShift;
import org.example.World.Entity;
import org.example.World.Objects.Grass;

import java.util.HashSet;
import java.util.Set;

public class Herbivore extends Creature{

    public Herbivore(int hp, int speed) {
        super(hp,speed);
    }

    public InteractionResult interact(Entity entity){
        if (entity instanceof Grass grass){
            return InteractionResult.EAT;
        }
        return InteractionResult.BLOCK;
    }

    public Set<CoordinatesShift> getCreatureMoves() {
        Set<CoordinatesShift> result = new HashSet<>();
        result.add(new CoordinatesShift(-1 , 1));
        result.add(new CoordinatesShift(0 , 1));
        result.add(new CoordinatesShift(1 , 1));
        result.add(new CoordinatesShift(-1 , 0));
        result.add(new CoordinatesShift(1 , 0));
        result.add(new CoordinatesShift(-1 , -1));
        result.add(new CoordinatesShift(0 , -1));
        result.add(new CoordinatesShift(1 , -1));
        return result;
    }
}
