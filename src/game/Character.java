package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

abstract class Character implements Attackable {
    protected String name;
    protected int health;
    protected int level;
    protected int experience;
    protected int attackPower;
    protected Inventory inventory;
    protected int skillCooldown;
    protected boolean isDefending;
    protected boolean isPoisoned;
    protected boolean isStunned;
    protected int poisonDamagePerTurn;


    public Character(String name, int lifePoints, int attackPower) {
        this.name = name;
        this.health = lifePoints;
        this.level = 1;
        this.experience = 0;
        this.attackPower = attackPower;
        this.inventory = new Inventory();
        this.skillCooldown = 0;
        this.isDefending = false;
        this.isPoisoned = false;
        this.isStunned = false;
        this.poisonDamagePerTurn = 5;
    }

    public void receiveDamage(int damage) {
        // If stunned, the character can't take damage in this turn
        if (isStunned) {
            System.out.println(name + " is stunned and can't take damage this turn!");
            isStunned = false; // End the stun after the turn
            return;
        }

        health -= damage;
            System.out.println(name + " takes " + damage + " damage. Remaining health: " + Math.max(health, 0));
    }


        public int getHealth() {
        return health;
    }

    public void heal(int amount) {
        health += amount;
        System.out.println(name + " heals for " + amount + " health. Total health: " + health);
    }


    public boolean isALive() {
        return this.health > 0;
    }

    public void attack(Character opponent) {
        int damage = attackPower;

        // If the opponent is defending, reduce damage
        if (opponent.isDefending) {
            damage = damage / 2; // Reduce damage by half if defending
            System.out.println(opponent.getName() + " is defending! Incoming damage is reduced.");
        }

        // Apply poison damage if the opponent is poisoned
        if (opponent.isPoisoned) {
            opponent.receiveDamage(opponent.poisonDamagePerTurn);
        }

        System.out.println(name + " attacks " + opponent.getName() + " for " + damage + " damage.");
        opponent.receiveDamage(damage);
        gainExperience(damage);

        if (!opponent.isALive()) {
            System.out.println(opponent.getName() + " is defeated!");
            gainExperience(50);// Gain 50 XP for defeating an opponent
            dropItem(opponent); // Drop an item when defeated
        }
    }

    public void defend() {
        this.isDefending = true;
        System.out.println(name + " is defending! Damage will be reduced this turn.");
    }

    public void applyPoison() {
        isPoisoned = true;
        System.out.println(name + " has been poisoned! Will take damage over time.");
    }

    public void applyStun() {
        isStunned = true;
        System.out.println(name + " is stunned and can't act next turn.");
    }



    public void resetDefense() {
        isDefending = false;
    }


    public String getName() {
        return this.name;
    }

    public void gainExperience(int xp) {
        experience += xp;
        System.out.println(name + " gains " + xp + " XP. Total XP: " + experience);
        checkLevelUp();
    }

    private void checkLevelUp() {
        int xpThreshold = level * 100; // XP required for next level increases with level
        if (experience >= xpThreshold) {
            level++;
            experience -= xpThreshold; // Carry over remaining XP
            health += 20; // Increase health on level up
            attackPower += 5; // Increase attack power on level up
            System.out.println(name + " leveled up to Level " + level + "! Health and attack power increased!");
        }
    }

    public int getLevel() {
        return level;
    }

    public void useItem(Item item) {
        if (item == null) return;

        switch (item.getType()) {
            case "Health":
                heal(item.getValue());
                break;
            case "Attack":
                attackPower += item.getValue();
                System.out.println(name + "'s attack power increased by " + item.getValue() + ". New attack power: " + attackPower);
                break;
            case "Special":
                useSpecialItem(item);
                break;
            default:
                System.out.println("Invalid item type.");
        }
    }

    private void useSpecialItem(Item item) {
        Random rand = new Random();
        int effect = rand.nextInt(4); // Random effect: 0 - 3

        switch (effect) {
            case 0: // Heal the player
                int healAmount = rand.nextInt(30) + 10; // Random healing between 10 and 40
                heal(healAmount);
                System.out.println(item.getName() + " heals " + name + " for " + healAmount + " health.");
                break;
            case 1: // Boost attack power
                int attackBoost = rand.nextInt(5) + 5; // Random boost between 5 and 10
                attackPower += attackBoost;
                System.out.println(item.getName() + " increases " + name + "'s attack power by " + attackBoost + ". New attack power: " + attackPower);
                break;
            case 2: // Deal damage to an alive opponent
                Character opponent = getRandomOpponent();
                if (opponent != null) {
                    int damage = rand.nextInt(20) + 10; // Random damage between 10 and 30
                    opponent.receiveDamage(damage);
                    System.out.println(item.getName() + " deals " + damage + " damage to " + opponent.getName() + ".");
                }
                break;
            default:
                System.out.println("Unknown special effect.");
        }
    }

    // Drop a random item when the character is defeated
    private void dropItem(Character opponent) {
        Random rand = new Random();
        List<Item> availableItems = new ArrayList<>();

        // Add some items that could drop
        availableItems.add(new Item("Health Potion", "Health", 20));
        availableItems.add(new Item("Attack Boost", "Attack", 10));
        availableItems.add(new Item("Mystical Amulet", "Special", 0));

        // Randomly choose an item from the available items
        Item droppedItem = availableItems.get(rand.nextInt(availableItems.size()));
        System.out.println(opponent.getName() + " drops a " + droppedItem.getName() + "!");

        // Simulate adding it to the player's inventory
        Character player = this; // Assuming a Player class that extends Character
        player.getInventory().addItem(droppedItem);
    }

    // Updated method to randomly select an alive opponent
    private Character getRandomOpponent() {
        // Add all characters to a list
        List<Character> opponents = new ArrayList<>();
        if (this instanceof Warrior) {
            opponents.add(new Mage("Gandalf"));
            opponents.add(new Robber("Loki"));
        } else if (this instanceof Mage) {
            opponents.add(new Warrior("Thor"));
            opponents.add(new Robber("Loki"));
        } else if (this instanceof Robber) {
            opponents.add(new Warrior("Thor"));
            opponents.add(new Mage("Gandalf"));
        }

        // Filter the list to only include alive opponents
        List<Character> aliveOpponents = new ArrayList<>();
        for (Character opponent : opponents) {
            if (opponent.isALive()) {
                aliveOpponents.add(opponent);
            }
        }

        // If there are alive opponents, choose one randomly
        if (aliveOpponents.isEmpty()) {
            System.out.println("No opponents are alive to choose from.");
            return null;
        } else {
            Random rand = new Random();
            return aliveOpponents.get(rand.nextInt(aliveOpponents.size()));
        }
    }

    public void decrementCooldown() {
        if (skillCooldown > 0) {
            skillCooldown--;
            System.out.println(name + "'s skill cooldown: " + skillCooldown + " turns remaining.");
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public abstract void useSkill(Character opponent);
}
