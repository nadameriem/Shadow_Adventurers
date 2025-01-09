package Game;

import java.util.Random;

public class Robber extends Character {
    public Robber(String name) {
        super(name, 90,18); // Health = 90, Attack Power = 18
    }

    @Override
    public void useSkill(Character opponent) {
        if (skillCooldown > 0) {
            System.out.println(name + "'s skill is on cooldown! Turns remaining: " + skillCooldown);
            return;
        }
        Random rand = new Random();
        int skill = rand.nextInt(2);

        if (skill == 0) {
            opponent.applyStun();
            System.out.println(name + " uses a Stun Strike on " + opponent.getName() + "!");
        } else {
            System.out.println(getName() + " uses Backstab!");
            int skillDamage = 35;
            System.out.println(opponent.getName() + " is caught off guard by the backstab!");
            opponent.receiveDamage(skillDamage);
        }

        this.receiveDamage(15);
        System.out.println(getName() + " takes 15 damage for using their skill.");
        gainExperience(50);
        skillCooldown = 3;

    }
}
