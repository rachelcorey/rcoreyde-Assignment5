package main.java;

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
}
