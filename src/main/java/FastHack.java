package main.java;

public class FastHack extends Hack {

    public FastHack(int damageModifier) {
        super(damageModifier);
        this.resourceCost = 10;
        this.percentToMiss = 0.40f;
        this.numberOfTurnsToCast = 1;
        this.statusEffect = new HackStatus(damageModifier + damageAmt, 2);
    }
}
