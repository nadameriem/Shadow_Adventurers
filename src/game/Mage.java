package Game;

import java.util.Random;

public class Mage extends Character {
    public Mage(String name) {
        super(name, 80,20); // Health = 80, Attack Power = 20
    }

    @Override
    public void useSkill(Character opponent) {
        if (skillCooldown > 0) {
            System.out.println(name + "'s skill is on cooldown! Turns remaining: " + skillCooldown);
            return;
        }
        Random rand = new Random();
        int skill = rand.nextInt(2); // 50% chance to apply either poison or a special heal

        if (skill == 0) {

            opponent.applyPoison();
            System.out.println(name + " uses a Poison Spell on " + opponent.getName() + "!");
        } else {
            System.out.println(getName() + " casts Fireball!");
            int skillDamage = 30;
            System.out.println(opponent.getName() + " is scorched by the fireball!");
            opponent.receiveDamage(skillDamage);

        }
        // Reduce the user's health by 5 for using the skill
        this.receiveDamage(10);
        System.out.println(getName() + " takes 10 damage for using their skill.");
        gainExperience(50);
        skillCooldown = 3;
    }
}