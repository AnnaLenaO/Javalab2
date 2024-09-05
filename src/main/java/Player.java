import interfaces.Movable;
import item.Item;

import record.Position;

public abstract class Player implements Movable {
    private String name;
    private Position position;
    private int health;
    private int strength;
    private Item item;

    public Player(String name, Position position, int health, int strength, Item item) {
        this.name = name;
        this.position = position;
        this.health = health;
        this.strength = strength;
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public Position move(int x, int y) {
        Position position = getPosition();
        return new Position(position.x() + x, position.y() + y);
//        Position newPosition = RoseGarden.move(position, x, y);
//        setPosition(newPosition);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
