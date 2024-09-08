import item.Item;
import record.Position;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class RoseGarden {
    private final List<Item> itemsInRoseGarden;

    public RoseGarden() {
        itemsInRoseGarden = new ArrayList<>();
    }

    public void addToRoseGarden(Supplier<Item> itemSupplier) {
        Item item = itemSupplier.get();
        itemsInRoseGarden.add(item);
    }

    public List<Item> isPositionInRoseGarden(Position position) {
        return itemsInRoseGarden.stream()
                .filter(item -> item.getPosition().equals(position))
                .collect(Collectors.toList());
    }

    public void movePest(Position playerDirection, int x, int y) {
        itemsInRoseGarden.stream()
                .filter(Item.Pest.class::isInstance)
                .map(Item.Pest.class::cast)
                .forEach(pest -> {
                    Position position = pest.getPosition();
                    Position newPestPosition = new Position(position.x() + x, position.y() + y);
                    if (!newPestPosition.equals(playerDirection)) {
                        pest.move(x, y);
                    }
                });
    }
}
