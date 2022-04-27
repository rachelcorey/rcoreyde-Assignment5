package main.java;

import java.util.Random;

public class MultiSlash extends Skill {

    public MultiSlash(int powerModifier) {
        super(powerModifier);
        this.name = "Multi Slash";
        this.description = "Slash the enemy up to 5 times in a row for a smaller amount of damage.";
        this.damageAmt = 2;
        this.resourceCost = 5;
        this.hitNumber = hitNumber();
        this.percentToMiss = 0.10f;
        this.numberOfTurnsToCast = 1;
        this.statusEffect = null;
    }

    @Override
    public AttackResult useSkill(String nameOfUser) {
        Player player = GameManager.getInstance().getPlayer();
        this.damageAmt = (int) Math.floor(player.getLevel() * 0.40);
        this.hitNumber = hitNumber();
        this.damageAmt *= hitNumber;
        return new AttackResult(name + " hit " + hitNumber + " times and did " + damageAmt + " damage!", damageAmt, resourceCost, null);
    }

    private int hitNumber() {
        Random rand = new Random();
        return rand.nextInt(5) + 1;
    }
}
