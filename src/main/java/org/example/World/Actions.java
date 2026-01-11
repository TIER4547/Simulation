package org.example.World;

import org.example.World.Creatures.Animals.Sheep;
import org.example.World.Creatures.Creature;
import org.example.World.Creatures.Herbivore;
import org.example.World.Creatures.Predator;
import org.example.World.Map.Coordinates;
import org.example.World.Map.CoordinatesShift;
import org.example.World.Map.Map;
import org.example.World.Map.Move;
import org.example.World.Objects.Grass;
import org.example.World.Objects.Rock;
import org.example.World.Objects.Tree;

import java.util.*;

public class Actions {
    private final org.example.World.Map.Map map;
    private Random random = new Random();
    private final List<Move> plannedMoves = new ArrayList<>();
    private final PathFinder pathFinder;

    public Actions(Map map, PathFinder pathFinder) {
        this.map = map;
        this.pathFinder = pathFinder;
    }

    public void turnActions() {
        plannedMoves.clear();
        Set<Coordinates> reservedCoordinates = new HashSet<>();
        Set<Coordinates> targetForAttack = new HashSet<>();
        for (Entity entity : new ArrayList<>(map.getEntities())) {
            if (entity instanceof Creature creature){
                Set<CoordinatesShift> moveList = creature.getCreatureMoves();
                Coordinates target = map.getEntityCoordinates(creature);
                boolean hasAction = false;
                if (targetForAttack.contains(target)){
                    continue;
                }
                if (creature instanceof Predator predator){
                    if (tryAttack(predator, reservedCoordinates, targetForAttack)){
                        hasAction = true;
                        continue;}
                }
                List<Coordinates> path = null;
                if (!hasAction){
                if (creature instanceof Predator){
                    path = pathFinder.bfs(creature, Herbivore.class, map);
                }
                if (creature instanceof Herbivore){
                    path = pathFinder.bfs(creature, Grass.class, map);
                }
                if (path != null && path.size() > 1) {
                    Coordinates from = path.get(0);
                    Coordinates to = path.get(1);
                    if (!reservedCoordinates.contains(to)) {
                        plannedMoves.add(new Move(from, to));
                        reservedCoordinates.add(to);
                    }
                }
                }
                if (!hasAction){
                    randomMoves(creature, moveList, reservedCoordinates);
                }
                }
                }
            }
    private void randomMoves(Creature creature, Set<CoordinatesShift> moves, Set<Coordinates> reservedCoordinates){
                List<CoordinatesShift> moveList = new ArrayList<>(moves);
                CoordinatesShift randomShift = moveList.get(random.nextInt(moveList.size()));
                Coordinates from = map.getEntityCoordinates(creature);
                Coordinates to = from.shift(randomShift);
                if (map.isInside(to) && !reservedCoordinates.contains(to)) {
                        plannedMoves.add(new Move(from, to));
                        reservedCoordinates.add(to);
                    }
            }
    public void makeMoves() {
        for (Move move : plannedMoves) {
            Entity mover = map.getEntity(move.from);
            Entity target = map.getEntity(move.to);
            if (mover instanceof Creature creature){
                if (target == null){
                    moveEntity(mover, move);
                    continue;
                }
                switch (creature.interact(target)){
                    case EAT -> eat(creature, target, move);
                    case ATTACK -> attack(creature, target, move);
                    case MOVE -> moveEntity(creature, move);
                    case BLOCK -> {}
                }
            }
        }
    }

    private void eat(Entity herbivore, Entity grass, Move move){
        map.removeEntityAndCoordinates(move.to, grass);
        moveEntity(herbivore, move);
    }

    private void attack(Entity attacker, Entity victim, Move move){
        Predator predator = (Predator) attacker;
        Herbivore herbivore = (Herbivore) victim;
        herbivore.takeDamage(predator.getDamage());
        if (herbivore.getHp() <= 0) {
            map.removeEntityAndCoordinates(move.to, victim);
            moveEntity(attacker, move);
        }
    }
    private void moveEntity(Entity mover, Move move){
        map.removeEntityAndCoordinates(move.from, mover);
        map.setEntities(mover, move.to);
    }

    private boolean tryAttack(Predator predator, Set<Coordinates> reservedCoordinates, Set<Coordinates> targetToAttack){
        Coordinates from = map.getEntityCoordinates(predator);
        for (CoordinatesShift shift : predator.getCreatureMoves()){
            Coordinates to = from.shift(shift);
            if (!map.isInside(to)){
                continue;
            }
            if (reservedCoordinates.contains(to)){
                continue;
            }
            if (map.getEntity(to) instanceof Herbivore){
                plannedMoves.add(new Move(from, to));
                reservedCoordinates.add(to);
                targetToAttack.add(to);
                return true;
            }
        }
        return false;
    }
}
