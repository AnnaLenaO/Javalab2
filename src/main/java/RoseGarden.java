import item.Item;
import record.Position;

import java.util.*;
import java.util.function.Supplier;

public class RoseGarden {
    //Attribut: ArrayList som sparar labyrintens (väggar, gångar, skatter, monster)
    private final List<Item> itemsInRoseGarden;
//    Set<Position> positions = new HashSet<>();
//    Set<Item> items = new HashSet<>(itemsInRoseGarden);

//    private List<record.Position> positions;

//    private List<Set<String>> walls;
//    private List<Set<String>> paths;
//    private List<Set<String>> treasures;
//    private List<Set<String>> pests;

    //Constructor to initialise lists of the attributes/items?
    public RoseGarden() {
        itemsInRoseGarden = new ArrayList<>();
    }

    //Metoder för att:
    // -lägga till saker
    public void addToRoseGarden(Supplier<Item> itemSupplier) {
        Item item = itemSupplier.get();
//        itemsInRoseGarden.add(position);
        itemsInRoseGarden.add(item);
//        itemsInRoseGarden.add(new Item(positionSupplier.get()));
    }

//    public void addToRoseGarden(Item item) {
//        itemsInRoseGarden.add(item);
//    }
//    public void addToRoseGarden(Position position) {
//        items.add(new Item(position));
//    }

    //-ta bort saker
    public void deleteFromRoseGarden(Item item) {
        itemsInRoseGarden.remove(item);
    }
//    public void deleteFromRoseGarden(Position position) {
//        items.remove(new Item(position));
//    }

    //-kolla om en viss position är upptagen och returnera aktuellt item
    public Optional<Item> isPositionInRoseGarden(Position position) {
        for (Item item : itemsInRoseGarden) {
            if (item.getPosition().equals(position)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

//    //-kolla om en viss position är upptagen
//    public boolean isPositionInRoseGarden(Position position) {
//        return positions.contains(position);
//    }
}
