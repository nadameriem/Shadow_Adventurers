package Game;

import java.util.List;
import java.util.Random;

public class RandomEvent {

    // Different types of random events
    public static void triggerRandomEvent(Character player, List<Character> allCharacters){
        Random rand = new Random();
        int eventType = rand.nextInt(4);  // 0 to 3 for 4 types of events

        switch (eventType) {
            case 0:
                treasureChest(player);
                break;
            case 1:
                surpriseAttack(player);
                break;
            case 2:
                trapEvent(player, allCharacters);
                break;
            case 3:
                // No event, just a normal round
                System.out.println("No event occurred this round.");
                break;
            default:
                break;
        }
    }

    // Event: Treasure chest found, add a random item to the player's inventory
    private static void treasureChest(Character player) {
        System.out.println(player.getName() + " finds a treasure chest!");

        // Random item drops
        Random rand = new Random();
        int itemType = rand.nextInt(3);  // 0 for health, 1 for attack, 2 for special
        Item newItem = null;

        switch (itemType) {
            case 0:
                newItem = new Item("Health Potion", "Health", 20);
                break;
            case 1:
                newItem = new Item("Attack Boost", "Attack", 10);
                break;
            case 2:
                newItem = new Item("Mystical Amulet", "Special", 0);
                break;
            default:
                break;
        }
        if (newItem != null) {
            player.getInventory().addItem(newItem);
        }
    }

    // Event: Surprise attack, the player is attacked by a random opponent
    private static void surpriseAttack(Character player) {
        System.out.println(player.getName() + " is ambushed by a random opponent!");

        Random rand = new Random();
        int damage = rand.nextInt(10) + 10;  // Random damage between 10 and 20
        player.receiveDamage(damage);
    }

    // Event: Trap, everyone takes damage due to a trap
    private static void trapEvent(Character player, List<Character> allCharacters) {
        System.out.println("A trap is triggered! Everyone takes damage.");

        Random rand = new Random();
        int trapDamage = rand.nextInt(15) + 5;  // Random trap damage between 5 and 20

        // Apply damage to all characters in the game
        for (Character character : allCharacters) {
            if (character.isALive()) {
                character.receiveDamage(trapDamage);
            }
        }
    }
}
