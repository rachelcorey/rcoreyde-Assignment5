package main.java;

public class EnemyHack extends Hack {

    public EnemyHack(int damageModifier) {
        super(damageModifier);
        this.resourceCost = 0;
        this.percentToMiss = 0.20f;
        this.numberOfTurnsToCast = 2;
        this.statusEffect = new HackStatus(damageModifier + damageAmt, 2);
    }
}
