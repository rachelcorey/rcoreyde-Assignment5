package main.java;

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

}
