import item.Item;
import record.Position;

import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

public class Game {
    public static void main(String[] args) {
        RoseGarden roseGarden = new RoseGarden();
        Player player = new Player();
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        var welcomeLines = sb
                .append("Welcome to RoseGarden!")
                .append("\n")
                .append("What is your name?")
                .append("\n");

        System.out.println(welcomeLines);
        String name = scanner.nextLine();
        player.setName(name);

        sb.setLength(0);
        var infoLines = sb
                .append("Ok, ")
                .append(name)
                .append(", you have")
                .append("\n")
                .append("Health: ")
                .append(player.getHealth())
                .append("\n")
                .append("Strength: ")
                .append(player.getStrength())
                .append("\n")
                .append(Player.getItem())
                .append("\n")
                .append("and start at ")
                .append(player.getPosition())
                .append("\n");

        System.out.println(infoLines);
        System.out.println("Be aware so you don´t go to infinity without finding any roses!\n");
        adItemPositionToRoseGarden(roseGarden);

        boolean restartGame = true;
        while (restartGame) {
            sb.setLength(0);
            var directionOptionLines = sb
                    .append("In what direction do you like to go?")
                    .append("\n")
                    .append("Press ")
                    .append("\"u\" ")
                    .append("for upp, ")
                    .append("\"r\" ")
                    .append("for right, ")
                    .append("\"d\" ")
                    .append("for down, ")
                    .append("\"l\" ")
                    .append("for left ")
                    .append("or ")
                    .append("\"e\" ")
                    .append("for Exit ")
                    .append("\n");

            System.out.print(directionOptionLines);
            String direction = scanner.nextLine();

            try {
                switch (direction) {
                    case "u" -> handleMove(player, roseGarden, (new Position(0, 1)));
                    case "r" -> handleMove(player, roseGarden, (new Position(1, 0)));
                    case "d" -> handleMove(player, roseGarden, (new Position(0, -1)));
                    case "l" -> handleMove(player, roseGarden, (new Position(-1, 0)));
                    case "e" -> {
                        restartGame = false;
                        exitGame();
                    }
                    default -> System.out.println("Invalid direction");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (player.getHealth() <= 0 || player.getStrength() <= 0) {
                sb.setLength(0);
                var gameOverLines = sb
                        .append("Game over!")
                        .append("\n")
                        .append("Your")
                        .append("\n")
                        .append("Health: ")
                        .append(player.getHealth())
                        .append("\n")
                        .append("Strength: ")
                        .append(player.getStrength())
                        .append("\n")
                        .append("Last rose: ")
                        .append(Player.getItem())
                        .append("\n")
                        .append("Welcome back to try again, ")
                        .append(Player.getName())
                        .append("\n");

                System.out.println(gameOverLines);
                restartGame = false;
                exitGame();
            }
        }
        scanner.close();
    }

    private static void handleMove(Player player, RoseGarden roseGarden, Position playerDirection) {
        player.move(playerDirection.x(), playerDirection.y());
        roseGarden.movePest(player, -playerDirection.x(), -playerDirection.y());
        findIfItemInRoseGarden(player, roseGarden, playerDirection);
    }

    private static void findIfItemInRoseGarden(Player player, RoseGarden roseGarden, Position playerDirection) {
        Position position = player.getPosition();
        List<Item> items = roseGarden.getItemsAtPositionInRoseGarden(position);
        if (items.isEmpty()) {
            System.out.println("No items in " + position);
        }
        items.stream()
                .peek(item -> updatePlayerItems(player, item))
                .filter(Item.Obstacle.class::isInstance)
                .findAny()
                .ifPresent(ignored -> handleMoveBack(player, playerDirection));
    }

    private static void handleMoveBack(Player player, Position playerDirection) {
        if (playerDirection.x() != 0) {
            player.move(-playerDirection.x(), 0);
        }

        if (playerDirection.y() != 0) {
            player.move(0, -playerDirection.y());

        }
    }

    private static void updatePlayerItems(Player player, Item item) {
        switch (item) {
            case Item.Obstacle ignored -> handleObstacle();
            case Item.Upgrade ignored -> handleUpgrade(player);
            case Item.Rose ignored -> handleRose(player);
            case Item.Pest ignored -> handlePest(player);
            default -> throw new IllegalStateException("Unexpected value: " + item);
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

    private static void handleRose(Player player) {
        player.setStrength(player.getStrength() + 1);
        Player.setItem(new Item.Rose(player.getPosition()));
        System.out.print("Oh Yes! Yo got a rose!\n" + Player.getItem() + "\n");
    }

    private static void handlePest(Player player) {
        System.out.print("Oh No! The pest is everywhere. You are getting weaker\n");
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
