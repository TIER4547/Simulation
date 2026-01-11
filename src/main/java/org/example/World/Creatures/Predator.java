package org.example.World.Creatures;

import org.example.InteractionResult;
import org.example.World.Map.CoordinatesShift;
import org.example.World.Entity;

import java.util.HashSet;
import java.util.Set;

public class Predator extends Creature{
    private int damage;

    public Predator(int hp, int speed, int damage) {
        super(hp, speed);
        this.damage = damage;
    }

    public InteractionResult interact(Entity entity){
        if (entity instanceof Herbivore herbivore){
            return InteractionResult.ATTACK;
        }
        return InteractionResult.BLOCK;
    }

    @Override
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

        result.add(new CoordinatesShift(-2 , 2));
        result.add(new CoordinatesShift(0 , 2));
        result.add(new CoordinatesShift(2 , 2));
        result.add(new CoordinatesShift(-2 , 0));
        result.add(new CoordinatesShift(2 , 0));
        result.add(new CoordinatesShift(-2 , -2));
        result.add(new CoordinatesShift(0 , -2));
        result.add(new CoordinatesShift(2 , -2));

        return result;
    }

    public Set<CoordinatesShift> getCreatureAttack(){
        Set<CoordinatesShift> result = new HashSet<>();
        result.add(new CoordinatesShift(-1 , 1));
        result.add(new CoordinatesShift(0 , 1));
        result.add(new CoordinatesShift(1 , 1));
        result.add(new CoordinatesShift(-1 , 0));
        result.add(new CoordinatesShift(1 , 0));
        result.add(new CoordinatesShift(-1 , -1));
        result.add(new CoordinatesShift(0 , -1));
        result.add(new CoordinatesShift(1 , -1));

        result.add(new CoordinatesShift(-2 , 2));
        result.add(new CoordinatesShift(0 , 2));
        result.add(new CoordinatesShift(2 , 2));
        result.add(new CoordinatesShift(-2 , 0));
        result.add(new CoordinatesShift(2 , 0));
        result.add(new CoordinatesShift(-2 , -2));
        result.add(new CoordinatesShift(0 , -2));
        result.add(new CoordinatesShift(2 , -2));

        return result;
    }
    public int getDamage(){
        return damage;
    }

}

