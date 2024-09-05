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
        public Position move(int x, int y) {
            Position position = getPosition();
            return new Position(position.x() + x, position.y() + y);

//            this.position = new Position(position.x() + x, position.y() + y);
//            return this.position;
        }

//        private Position getPosition() {
//            return position;
//        }
    }
}


// skatter: svampmedel, rosjord, roshandskar, seckatör
//uppgraderingar: trädgårdsmästare, trädgårdsingenjör, trädgårdsarkitekt, landskapsarkitekt
//monster: bladlöss, mördarsnigel, rosrost, svartfläcksjuka, mjöldagg
//arv


//position(x, y)
//record position:
//finns i ett eget paket record


//Både Player och Maze? kan använda denna record record.Position (implements)

//en Array List som bara sparar kryssen; jag vill ställa mig på den här positionen, finns det något Item där?

