package main.java;

import java.util.Random;

public class HeavyBlow extends Skill {

    public HeavyBlow(int powerModifier) {
        super(powerModifier);
        this.name = "Heavy Blow";
        this.description = "A heavy blow that deals high single-target damage to the enemy.";
        this.damageAmt = (int) (12 + (Math.floor((double) powerModifier / 3)));
        this.resourceCost = 5;
        this.hitNumber = 1;
        this.percentToMiss = 0.20f;
        this.numberOfTurnsToCast = 1;
        this.statusEffect = null;
    }

    @Override
    public AttackResult useSkill(String nameOfUser) {
        Random rand1 = new Random();
        Random rand2 = new Random();
        if (rand1.nextInt(100) < 100 * percentToMiss) {
            return new AttackResult(nameOfUser + "'s " + name + " missed!", 0, resourceCost, null);
        }
        if (rand2.nextInt(100) < 100 * percentToCrit) {
            return new AttackResult(nameOfUser + "'s " + name + " did a critical strike and dealt double damage for " + damageAmt * 2 + "!",
                    damageAmt * 2, resourceCost, null);
        }
        Player player = GameManager.getInstance().getPlayer();
        this.damageAmt = (int) Math.ceil(player.getLevel() * 0.50);
        return new AttackResult(nameOfUser + "'s " + name + " did " + damageAmt + " damage!", damageAmt, resourceCost, statusEffect);
    }

}
