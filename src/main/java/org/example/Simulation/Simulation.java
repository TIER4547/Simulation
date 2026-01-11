package org.example.Simulation;

import org.example.World.Actions;
import org.example.World.Map.Map;
import org.example.World.Map.MapConsoleRenderer;
import org.example.World.Map.MapFactory;
import org.example.World.PathFinder;

public class Simulation{
    private Map map;
    private MapFactory mapFactory;
    private MapConsoleRenderer mapConsoleRenderer;
    private Actions actions;
    private PathFinder pathFinder = new PathFinder();


    public void start() {
        map = new MapFactory().spawnRandomEntities();
        mapConsoleRenderer = new MapConsoleRenderer();
        actions = new Actions(map, pathFinder);

        while (true) {
            mapConsoleRenderer.render(map);
            actions.turnActions();
            actions.makeMoves();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
