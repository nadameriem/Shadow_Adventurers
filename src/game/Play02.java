package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Play02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initial storyline introduction with ASCII frame
        System.out.println("****************************************************");
        System.out.println("*                                                  *");
        System.out.println("*          🌟 WELCOME TO THE WORLD OF AVALON 🌟    *");
        System.out.println("*   A land of heroes, magic, and endless battles.  *");
        System.out.println("*                                                  *");
        System.out.println("****************************************************");
        System.out.println("\nYour journey begins as an aspiring warrior, mage, or robber,");
        System.out.println("fighting to protect Avalon from looming threats.\n");

        Character warrior = new Warrior("Thor");
        Character mage = new Mage("Gandalf");
        Character robber = new Robber("Loki");

        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║               CHOOSE YOUR HERO             ║");
        System.out.println("╚════════════════════════════════════════════╝");
        System.out.println("1. ⚔️ Warrior");
        System.out.println("2. 🪄 Mage");
        System.out.println("3. 🗡️ Robber");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        // Ask for player's name after choosing the character
        System.out.println("\nEnter your character's name:");
        scanner.nextLine();  // Consume newline left by nextInt()
        String playerName = scanner.nextLine();

        // Assign player character and set opponents
        Character player;
        Character opponent1;
        Character opponent2;

        switch (choice) {
            case 1:
                player = new Warrior(playerName); // Use the player's custom name for the warrior
                opponent1 = mage;
                opponent2 = robber;
                break;
            case 2:
                player = new Mage(playerName); // Use the player's custom name for the mage
                opponent1 = warrior;
                opponent2 = robber;
                break;
            case 3:
                player = new Robber(playerName); // Use the player's custom name for the robber
                opponent1 = mage;
                opponent2 = warrior;
                break;
            default:
                System.out.println("\n⚠️ Invalid choice! Defaulting to Warrior.");
                player = new Warrior(playerName); // Default to warrior if invalid choice
                opponent1 = mage;
                opponent2 = robber;
        }

        // Storyline introduction after character selection
        System.out.println("\n✨ You are " + player.getName() + ", the legendary " + player.getClass().getSimpleName().toLowerCase() + "!");
        System.out.println("Your quest: to eliminate the threats to Avalon and prove your might.");
        System.out.println("\n🌟 The arena gates open... Your journey begins! 🌟");

        // ASCII Art for Character's Inventory
        System.out.println("═══════════════════════════════════════════════");
        System.out.println("           🛡️  INVENTORY READY 🛡️");
        if (player instanceof Warrior) {
            player.getInventory().addItem(new Item("Health Potion", "Health", 20));
            player.getInventory().addItem(new Item("Iron Sword", "Attack", 5));
        } else if (player instanceof Mage) {
            player.getInventory().addItem(new Item("Mana Potion", "Special", 0));
            player.getInventory().addItem(new Item("Fire Wand", "Attack", 10));
        } else {
            player.getInventory().addItem(new Item("Smoke Bomb", "Special", 0));
            player.getInventory().addItem(new Item("Dagger", "Attack", 8));
        }
        System.out.println("═══════════════════════════════════════════════");

        List<Character> allCharacters = new ArrayList<>();
        allCharacters.add(player);
        allCharacters.add(opponent1);
        allCharacters.add(opponent2);

        boolean gameRunning = true;
        // Game loop with additional ASCII Art for actions
        while (player.isALive() && (opponent1.isALive() || opponent2.isALive())) {
            System.out.println("\n╔══════════════════════╗");
            System.out.println("║      🌟 ROUND 🌟    ║");
            System.out.println("╚══════════════════════╝");
            System.out.println("💖 Health: " + player.getHealth() + " | ⚡ Level: " + player.getLevel());
            System.out.println("Cooldown: " + player.skillCooldown + " | Poisoned: " + (player.isPoisoned ? "Yes" : "No"));
            System.out.println("Stunned: " + (player.isStunned ? "Yes" : "No"));
            System.out.println("\nChoose your action:");
            System.out.println("1. ⚔️ Attack");
            System.out.println("2. ✨ Use Special Skill");
            System.out.println("3. 🧪 Use Item");
            System.out.println("4. 🛡️ Defend");
            System.out.println("5. 🕒 Pass Turn");
            System.out.println("6. 🚪 Exit Game");
            System.out.print("Enter your choice: ");
            int action = scanner.nextInt();

            // Action selection with ASCII frames
            System.out.println("─────────────────────────────");
            switch (action) {
                case 1:
                    System.out.println("Choose an opponent:");
                    if (opponent1.isALive()) {
                        System.out.println("1. " + opponent1.getName());
                    } else {
                        System.out.println("1. " + opponent1.getName() + " is defeated.");
                    }

                    if (opponent2.isALive()) {
                        System.out.println("2. " + opponent2.getName());
                    } else {
                        System.out.println("2. " + opponent2.getName() + " is defeated.");
                    }

                    int target = scanner.nextInt();
                    if (target == 1 && opponent1.isALive()) {
                        player.attack(opponent1);
                    } else if (target == 2 && opponent2.isALive()) {
                        player.attack(opponent2);
                    } else {
                        System.out.println("Invalid target.");
                    }
                    break;
                case 2:
                    System.out.println("Choose an opponent:");
                    if (opponent1.isALive()) {
                        System.out.println("1. " + opponent1.getName());
                    } else {
                        System.out.println("1. " + opponent1.getName() + " is defeated.");
                    }

                    if (opponent2.isALive()) {
                        System.out.println("2. " + opponent2.getName());
                    } else {
                        System.out.println("2. " + opponent2.getName() + " is defeated.");
                    }

                    int target2 = scanner.nextInt();
                    if (target2 == 1 && opponent1.isALive()) {
                        player.useSkill(opponent1);
                    } else if (target2 == 2 && opponent2.isALive()) {
                        player.useSkill(opponent2);
                    } else {
                        System.out.println("Invalid target.");
                    }
                    break;
                case 3:
                    player.getInventory().displayItems();
                    System.out.println("Choose an item to use (0 to cancel):");
                    int itemIndex = scanner.nextInt() - 1;
                    if (itemIndex >= 0) {
                        Item selectedItem = player.getInventory().getItem(itemIndex);
                        player.useItem(selectedItem);
                        player.getInventory().removeItem(selectedItem);
                    }
                    break;

                case 4: // Defend
                    player.defend();
                    break;
                case 5: // Pass turn
                    System.out.println(player.getName() + " passes their turn.");
                    break;
                case 6:
                    System.out.println("🚪 You have chosen to leave the adventure. Farewell, hero!");
                    gameRunning = false;
                    break;
                default:
                    System.out.println("Invalid action.");
                    break;
            }
            System.out.println("─────────────────────────────");

            if (!gameRunning) break;


            // Randomly pick an opponent who is still alive to attack the player
            Random random = new Random();
            Character[] opponents = {opponent1, opponent2};
            Character chosenOpponent = null;

            // Check if any of the opponents are alive
            while (chosenOpponent == null) {
                Character randomOpponent = opponents[random.nextInt(opponents.length)];
                if (randomOpponent.isALive()) {
                    chosenOpponent = randomOpponent;
                }
            }

            // Opponent's action
            int chance = random.nextInt(100); // Generate a random number between 0 and 99
            if (chance < 30) { // 30% chance to use a skill
                System.out.println(chosenOpponent.getName() + " decides to use their skill!");
                chosenOpponent.useSkill(player);
            } else { // 70% chance to perform a basic attack
                System.out.println(chosenOpponent.getName() + " decides to attack!");
                chosenOpponent.attack(player);
            }

            if (!player.isALive()) {
                System.out.println("\n💔 The hero has fallen. Avalon is doomed...");
                System.out.println("🕊️  GAME OVER 🕊️");
                break;
            }

            // Check if all opponents are dead and print status
            displayStatus(player, opponent1, opponent2);
            if (!opponent1.isALive() && !opponent2.isALive()) {
                System.out.println("\n🎉 VICTORY 🎉");
                System.out.println(player.getName() + " has triumphed over their enemies!");
                System.out.println("Avalon is safe... for now.");
                break;
            }
            player.resetDefense();

            player.decrementCooldown();
            opponent1.decrementCooldown();
            opponent2.decrementCooldown();

            RandomEvent.triggerRandomEvent(player, allCharacters);
        }
        if (gameRunning) {
            scanner.close();
            System.out.println("\n🌟 The fight is over! Thank you for playing! 🌟");
        }
    }

    private static void displayStatus(Character player, Character opponent1, Character opponent2) {
        System.out.println("\n📜 STATUS 📜");
        System.out.println("═══════════════════════════════════");
        System.out.println(player.getName() + " - 💖 Health: " + player.getHealth() + " | ⚡ Level: " + player.getLevel());
        if (opponent1.isALive()) {
            System.out.println(opponent1.getName() + " - 💖 Health: " + opponent1.getHealth() + " | ⚡ Level: " + opponent1.getLevel());
        } else {
            System.out.println(opponent1.getName() + " is defeated. ❌");
        }

        if (opponent2.isALive()) {
            System.out.println(opponent2.getName() + " - 💖 Health: " + opponent2.getHealth() + " | ⚡ Level: " + opponent2.getLevel());
        } else {
            System.out.println(opponent2.getName() + " is defeated. ❌");
        }
        System.out.println("═══════════════════════════════════");
    }
}