package item;

import interfaces.Movable;
import record.Position;

public class Item {
    static Position position;

    public Item(Position position) {
        Item.position = position;
    }

    public static Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + position.toString();
    }

    ///subklasser/////////////////////////

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
//            Position position = getPosition();
//            new Position(position.x() + x, position.y() + y);
            position = new Position(position.x() + x, position.y() + y);
//            Position position = new Position(position.x() + x, position.y() + y);
//
//            position = new Position(position.x() + x, position.y() + y);
//            return position;
        }
    }
}
