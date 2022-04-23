package main.java;

import java.util.Random;

public class MultiSlash extends Skill{

    public MultiSlash(int powerModifier) {
        super(powerModifier);
        this.name = "Multi Slash";
        this.description = "Slash the enemy up to 5 times in a row for a smaller amount of damage.";
        this.damageAmt = 3;
        this.resourceCost = 5;
        this.aoeDmg = 0;
        this.hitNumber = hitNumber();
        this.percentToMiss = 0.10f;
        this.numberOfTurnsToCast = 1;
        this.statusEffect = null;
    }

    @Override
    public AttackResult useSkill(String nameOfUser) {
        this.hitNumber = hitNumber();
        this.damageAmt *= this.hitNumber;
        return new AttackResult( name + " hit " + hitNumber + " times and did " + damageAmt + " damage!", damageAmt, null);
    }

    private int hitNumber() {
        Random rand = new Random();
        return rand.nextInt(5) + 1;
    }
}
