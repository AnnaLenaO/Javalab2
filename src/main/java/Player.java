import interfaces.Movable;
import item.Item;

import record.Position;

public class Player implements Movable {
    private String name;
    private Position position;
    private int health;
    private int strength;
    //    private static Item item;
    private static Item item;

    public Player() {
        this.name = "Gardener";
        this.position = new Position(11, 7);
        this.health = 50;
        this.strength = 10;
        item = new Item.Rose(new Position(8, 9));
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
    public void move(int x, int y) {
//        Position position = getPosition();
//        return new Position(position.x() + x, position.y() + y);

        this.position = new Position(position.x() + x, position.y() + y);

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

    public static Item getItem() {
        return item;
    }

    public static void setItem(Item item) {
        Player.item = item;
    }
}
