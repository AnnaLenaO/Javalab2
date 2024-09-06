import item.Item;
import record.Position;

import java.util.Optional;
import java.util.Scanner;
import java.util.function.Supplier;


public class Game {
    public static void main(String[] args) {
        RoseGarden roseGarden = new RoseGarden();
        Player player = new Player();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to RoseGarden!" + "\n" + "What is your name?");
        String name = scanner.nextLine();
        player.setName(name);

        System.out.println("Ok, " + name + ", you have:");
        System.out.println("Health: " + player.getHealth() + "\n" + "Strength: " + player.getStrength() + "\n" + Player.getItem() + "\n" + "and start at " + player.getPosition());
        System.out.println("Be aware so you don´t go to infinity without finding any roses!");

        /////loop//////////////////////////////////////

        boolean restartGame = true;
        while (restartGame) {
            System.out.print("In what direction do you like to go?\n");
            System.out.print("Press " + "\"u\" " + "for upp, " + "\"r\" " + "for right, " + "\"d\" " + "for down, " + "\"l\" " + "for left " + "or " + "\"e\" " + "for Exit, " + "\n");
            String direction = scanner.nextLine();

            try {
                switch (direction) {
                    case "u":
                        player.move(0, 1);
                        Item.Pest.move(0, -1);
                        findIfItemInRoseGarden(player, roseGarden);
                        break;
                    case "r":
                        player.move(1, 0);
                        Item.Pest.move(-1, 0);
                        findIfItemInRoseGarden(player, roseGarden);
                        break;
                    case "d":
                        player.move(0, -1);
                        Item.Pest.move(0, 1);
                        findIfItemInRoseGarden(player, roseGarden);
                        break;
                    case "l":
                        player.move(-1, 0);
                        Item.Pest.move(1, 0);
                        findIfItemInRoseGarden(player, roseGarden);
                        break;
                    case "e":
                        exitGame();
                        restartGame = false;
                        break;
                    default:
                        System.out.println("Invalid direction");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (player.getHealth() <= 0 || player.getStrength() <= 0) {
                System.out.println("Game over\n" + "Yor\n" + "Health: " + player.getHealth() + "\n" + "Strength: " + player.getStrength() + "\n");
                System.out.println("Your roses: \n" + Player.getItem() + "\n");
                exitGame();
            }
        }
    }

    private static void findIfItemInRoseGarden(Player player, RoseGarden roseGarden) {
        adItemPositionToRoseGarden(roseGarden);
        Position position = player.getPosition();
        Optional<Item> item = roseGarden.isPositionInRoseGarden(position);
        item.ifPresent(itemFound -> updatePlayerItems(player, itemFound, roseGarden));
        item.orElseGet(() -> {
            System.out.println("No items in " + position);
            return null;
        });
    }

    private static void updatePlayerItems(Player player, Item itemFound, RoseGarden roseGarden) {
        switch (itemFound) {
            case Item.Obstacle ignored -> handleObstacle();
            case Item.Upgrade ignored -> handleUpgrade(player);
            case Item.Rose ignored -> handleRose(player, itemFound, roseGarden);
            case Item.Pest ignored -> handlePest(player);
            default -> throw new IllegalStateException("Unexpected value: " + itemFound);
        }
    }

    private static void handleObstacle() {
        System.out.print("You bumped in to an obstacle, chose another direction?\n");
    }

    private static void handleUpgrade(Player player) {
        System.out.print("Congratulation! You upgrade to next level?\n");
        player.setHealth(player.getHealth() + 10);
        player.setStrength(player.getStrength() - 5);
    }

    private static void handleRose(Player player, Item itemFound, RoseGarden roseGarden) {
        System.out.print("Oh Yes! Yo got a rose!\n" + Player.getItem());
        player.setStrength(player.getStrength() + 1);
        Player.setItem(new Item.Rose(player.getPosition()));
        roseGarden.deleteFromRoseGarden(itemFound);
    }

    private static void handlePest(Player player) {
        System.out.print("Oh No! The pest killed a rose. You are getting weaker" + "\n");
        player.setHealth(player.getHealth() - 5);
        player.setStrength(player.getStrength() - 8);
        System.out.print("Your strength is now: " + player.getStrength() + "\n");
    }

    private static void adItemPositionToRoseGarden(RoseGarden roseGarden) {
        final ItemSupplier itemSupplier = getItemSupplier();

        roseGarden.addToRoseGarden(itemSupplier.itemSupplierObstacle());
        roseGarden.addToRoseGarden(itemSupplier.itemSupplierUppgrade());
        roseGarden.addToRoseGarden(itemSupplier.itemSupplierRose());
        roseGarden.addToRoseGarden(itemSupplier.itemSupplierPest());
    }

    private static ItemSupplier getItemSupplier() {
        Supplier<Item> itemSupplierObstacle = () -> new Item.Obstacle(new Position(2, 5));
        Supplier<Item> itemSupplierUppgrade = () -> new Item.Upgrade(new Position(8, 9));
        Supplier<Item> itemSupplierRose = () -> new Item.Rose(new Position(12, 7));
        Supplier<Item> itemSupplierPest = () -> new Item.Pest(new Position(4, 3));
        return new ItemSupplier(itemSupplierObstacle, itemSupplierUppgrade, itemSupplierRose, itemSupplierPest);
    }

    private record ItemSupplier(Supplier<Item> itemSupplierObstacle, Supplier<Item> itemSupplierUppgrade,
                                Supplier<Item> itemSupplierRose, Supplier<Item> itemSupplierPest) {
    }

    private static void exitGame() {
        System.out.println("Spelet avslutas");
    }
}
