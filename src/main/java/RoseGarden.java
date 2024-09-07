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

    public Optional<Item> isPositionInRoseGarden(Position position) {
        for (Item item : itemsInRoseGarden) {
            if (item.getPosition().equals(position)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    public void movePest(int x, int y) {
//        for (Item item : itemsInRoseGarden) {
//            if (item instanceof Item.Pest pest) {
////                pest.move(x, y);
//            }
//        }
        itemsInRoseGarden.stream()
//                .filter(item -> item instanceof Item.Pest)
                .filter(Item.Pest.class::isInstance)
//                .map(item -> (Item.Pest) item)
                .map(Item.Pest.class::cast)
                .forEach(pest -> pest.move(x, y));
    }
}
