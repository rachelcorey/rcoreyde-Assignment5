package main.java;

import java.util.Random;

public class BasicPhysicalNanobots extends BasicPhysical {

    int totalDamage;

    public BasicPhysicalNanobots(int powerModifier) {
        super(powerModifier);
    }

    @Override
    public AttackResult useSkill(String nameOfUser) {
        NanoBots player = (NanoBots) GameManager.getInstance().getPlayer();
        totalDamage = player.getNumOfBots() * damageAmt;
        Random rand = new Random();
        if (rand.nextInt(100) < 100 * percentToMiss) {
            return new AttackResult("Each of " + nameOfUser + "'s Nanobots' " + name + " missed!", 0, resourceCost, null);
        }
        return new AttackResult("Each of " + nameOfUser + "'s Nanobots' " + name + " did " + damageAmt + " damage! Total damage was " + totalDamage + "! ",
                totalDamage, resourceCost, statusEffect);
    }
}
