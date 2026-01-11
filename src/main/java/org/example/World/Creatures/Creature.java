package org.example.World.Creatures;

import org.example.InteractionResult;
import org.example.World.Map.CoordinatesShift;
import org.example.World.Entity;

import java.util.Set;

public abstract class Creature extends Entity {
    private int hp;
    private int speed;

    public Creature (int hp, int speed){
        this.hp = hp;
        this.speed = speed;
    }

    public InteractionResult interact(Entity entity){
        return InteractionResult.NONE;
    }

    public void takeDamage(int damage){
        hp -= damage;
        if (hp < 0){
            hp = 0;
        }
    }

    public int getHp(){
        return hp;
    }
    public int getSpeed(){
        return speed;
    }

    public abstract Set<CoordinatesShift> getCreatureMoves();
}
