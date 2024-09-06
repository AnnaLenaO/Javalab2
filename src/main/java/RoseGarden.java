import item.Item;
import record.Position;

import java.util.*;
import java.util.function.Supplier;

public class RoseGarden {
    private final List<Item> itemsInRoseGarden;

    public RoseGarden() {
        itemsInRoseGarden = new ArrayList<>();
    }

    public void addToRoseGarden(Supplier<Item> itemSupplier) {
        Item item = itemSupplier.get();
        itemsInRoseGarden.add(item);
    }

    public void deleteFromRoseGarden(Item item) {
        itemsInRoseGarden.remove(item);
    }
//    public void deleteFromRoseGarden(Position position) {
//        items.remove(new Item(position));
//    }

    public Optional<Item> isPositionInRoseGarden(Position position) {
        for (Item item : itemsInRoseGarden) {
            if (Item.getPosition().equals(position)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }
}
