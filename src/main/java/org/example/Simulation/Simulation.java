package org.example.Simulation;

import org.example.World.Actions;
import org.example.World.Map.Map;
import org.example.World.Map.MapConsoleRenderer;
import org.example.World.Map.MapFactory;

public class Simulation{
    private Map map;
    private MapFactory mapFactory;
    private MapConsoleRenderer mapConsoleRenderer;
    private Actions actions;


    public void start() {
        map = new MapFactory().spawnRandomEntities();
        mapConsoleRenderer = new MapConsoleRenderer();
        actions = new Actions(map);

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
