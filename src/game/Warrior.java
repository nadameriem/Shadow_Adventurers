package Game;

import java.util.Random;

public class Warrior extends Character {
    public Warrior(String name) {
        super(name, 100,15); // Health = 100, Attack Power = 15
    }

    @Override
    public void useSkill(Character opponent) {
        if (skillCooldown > 0) {
            System.out.println(name + "'s skill is on cooldown! Turns remaining: " + skillCooldown);
            return;
        }
        Random rand = new Random();
        int skill = rand.nextInt(2); // 50% chance to apply either extra damage or a defense boost

        if (skill == 0) {
            System.out.println(getName() + " uses shield Bash!");
            int skillDamage = 25;
            opponent.receiveDamage(skillDamage);
        }else {
            this.isDefending = true;
            System.out.println(name + " uses Shield Block to increase defense for the next turn!");
        }
        // Reduce the user's health by 5 for using the skill
        this.receiveDamage(5);
        System.out.println(getName() + " takes 5 damage for using their skill.");
        gainExperience(50);
        skillCooldown = 3;
    }
}
