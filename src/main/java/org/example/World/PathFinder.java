package org.example.World;

import org.example.World.Creatures.Creature;
import org.example.World.Map.Coordinates;
import org.example.World.Map.CoordinatesShift;
import org.example.World.Map.Map;
import org.example.World.Objects.Rock;
import org.example.World.Objects.Tree;

import java.util.*;

public class PathFinder {

    public List<Coordinates> bfs(Creature creature, Class<? extends  Entity> targetType, Map map){
        Queue<Coordinates> queue = new ArrayDeque<>();
        Set<Coordinates> visited = new HashSet<>();
        java.util.Map<Coordinates, Coordinates> cameFrom = new HashMap<>();
        Coordinates start = map.getEntityCoordinates(creature);
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()){
            Coordinates current = queue.poll();
            Entity entity = map.getEntity(current);
            if (entity != null && !current.equals(start) && targetType.isInstance(entity)) {
                return restoreFullPath(start, current, cameFrom);
            }
            for (CoordinatesShift shift : creature.getCreatureMoves()){
                Coordinates step = current.shift(shift);
                if (!map.isInside(step)){
                    continue;
                }
                if (visited.contains(step)){
                    continue;
                }
                Entity target = map.getEntity(step);
                if (target instanceof Tree || target instanceof Rock){
                    continue;
                }
                visited.add(step);
                cameFrom.put(step, current);
                queue.add(step);
            }
        }
        return null;
    }
    public List<Coordinates> restoreFullPath(Coordinates start, Coordinates target, java.util.Map<Coordinates, Coordinates> cameFrom){
        List<Coordinates> path = new ArrayList<>();
        Coordinates current = target;
        // a > b > c > target > M
        while(!current.equals(start)){
            path.add(current);
            current = cameFrom.get(current);
        }
        path.add(start);
        Collections.reverse(path);
        return path;
    }
}
