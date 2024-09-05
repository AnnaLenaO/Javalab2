import item.Item;
import record.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class Game {
    public static void main(String[] args) {
        //Instanser av Maze & player med new, skapa objekten
        RoseGarden roseGarden = new RoseGarden();
        List<Player> player = new ArrayList<>();
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
        Supplier<Item> itemSupplierUppgrade = () -> new Item.Upgrade(new Position(8, 9));
        Supplier<Item> itemSupplierRose = () -> new Item.Rose(new Position(8, 9));
        Supplier<Item> itemSupplierPest = () -> new Item.Pest(new Position(4, 3));


        roseGarden.addToRoseGarden(itemSupplierObstacle);
        roseGarden.addToRoseGarden(itemSupplierUppgrade);
        roseGarden.addToRoseGarden(itemSupplierRose);
        roseGarden.addToRoseGarden(itemSupplierPest);

        Position position = new Position(2, 5);
        Optional<Item> item = roseGarden.isPositionInRoseGarden(position);
        item.ifPresent(System.out::println);
        item.orElseGet(() -> {
            System.out.println("No items in " + position);

            return null;
        });
    }
}
