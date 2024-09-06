package item;

import interfaces.Movable;
import record.Position;

public class Item {
    private final Position position;

    public Item(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + position.toString();
    }

    public static class Obstacle extends Item {
        public Obstacle(Position position) {
            super(position);
        }
    }

    public static class Upgrade extends Item {
        public Upgrade(Position position) {
            super(position);
        }
    }

    public static class Rose extends Item {
        public Rose(Position position) {
            super(position);
        }
    }

    public static class Pest extends Item implements Movable {
        public Pest(Position position) {
            super(position);
        }

        @Override
        public void move(int x, int y) {
        }
    }
}
