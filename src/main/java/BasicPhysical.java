package main.java;

import java.util.Random;

public class BasicPhysical extends Skill {

    public BasicPhysical(int powerModifier) {
        super(powerModifier);
        this.name = "Basic Physical Attack";
        this.description = "A basic physical attack that deals damage based on your strength.";
        this.resourceCost = 0;
        this.damageAmt = 2 + powerModifier;
        this.hitNumber = 1;
        this.percentToMiss = 0.10f;
        this.numberOfTurnsToCast = 1;
        this.statusEffect = null;
    }


    // This satisfies Requirement #5
    public AttackResult useSkill(String nameOfUser) {
        Random rand1 = new Random();
        Random rand2 = new Random();
        if (rand1.nextInt(100) < 100 * percentToMiss) {
            return new AttackResult(nameOfUser + "'s " + name + " missed!", 0, 0, null);
        }
        if (rand2.nextInt(100) < 100 * percentToCrit) {
            return new AttackResult(nameOfUser + "'s " + name + " did a critical strike and dealt double damage for " + damageAmt * 2 + "!",
                    damageAmt * 2, 0, null);
        }
        Player player = GameManager.getInstance().getPlayer();
        this.damageAmt = (int) Math.ceil(player.getLevel() * 0.50);
        return new AttackResult(nameOfUser + "'s " + name + " did " + damageAmt + " damage!", damageAmt, 0, statusEffect);
    }
}
