package org.example.World.Map;

public class Move {
    public final Coordinates from, to;

    public Move(Coordinates moveFrom, Coordinates moveTo) {
        this.from = moveFrom;
        this.to = moveTo;
    }
}
