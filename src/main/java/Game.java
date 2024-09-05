import item.Item;
import record.Position;

import java.util.ArrayList;
import java.util.List;
//import item.Item.Obstacle;

import java.util.Optional;
import java.util.function.Supplier;

public class Game {
    public static void main(String[] args) {
        //Instanser av Maze & player med new, skapa objekten
        RoseGarden roseGarden = new RoseGarden();
        List<Player> player = new ArrayList<Player>();
//        List<RoseGarden> roseGarden = new ArrayList<>();

        //tex säga åt den att ta ett steg framåt (sedan returnera & skriva ut tex de nya positionerna/ändringen i förflyttning)
        //koordinera:
        //-inmatningar
        //-uppdateringar
        //-utskrifter
        //En loop i ett spel
        //
        //PrintLine, tex xx för att skriva ut monster osv, UTf-8 tecken.

        Supplier<Item> itemSupplierObstacle = () -> new Item.Obstacle(new Position(2, 5));

//        Supplier<Position> positionSupplierObstacle = new Supplier<Position>() {
////            @Override
////            public Position get() {
////                return new Position(2, 5);
//            }
//        };
        Supplier<Position> positionSupplierUppgrade = new Supplier<Position>() {
            @Override
            public Position get() {
                return new Position(8, 9);
            }
        };
        Supplier<Position> positionSupplierRose = new Supplier<Position>() {
            @Override
            public Position get() {
                return new Position(5, 6);
            }
        };
        Supplier<Position> positionSupplierPest = new Supplier<Position>() {
            @Override
            public Position get() {
                return new Position(4, 3);
            }
        };

        roseGarden.addToRoseGarden(itemSupplierObstacle);
//        roseGarden.addToRoseGarden(positionSupplierObstacle);
//        roseGarden.addToRoseGarden(positionSupplierUppgrade);
//        roseGarden.addToRoseGarden(positionSupplierRose);
//        roseGarden.addToRoseGarden(positionSupplierPest);

        Position position = new Position(2, 5);
        Optional<Item> item = roseGarden.isPositionInRoseGarden(position);
        item.ifPresent(System.out::println);
        item.orElseGet(() -> {
            System.out.println("No items in " + position);

            return null;
        });
    }
}
