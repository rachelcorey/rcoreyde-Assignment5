package main.java;

public class FastHack extends Hack {

    public FastHack(int damageModifier) {
        super(damageModifier);
        this.name = "Fast Hack";
        this.description = "A fast hack with a higher chance to miss that lasts 2 turns.";
        this.resourceCost = 10;
        this.percentToMiss = 0.40f;
        this.numberOfTurnsToCast = 1;
        this.statusEffect = new HackStatus(damageModifier + damageAmt, 2);
    }

    @Override
    public AttackResult useSkill (String nameOfUser) {
        return new AttackResult( nameOfUser + "'s Hack did " + damageAmt + " damage and applied the Hack effect!",
                damageAmt, resourceCost, this.statusEffect);
    }
}
