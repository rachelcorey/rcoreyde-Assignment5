package main.java;

public class EnemyHack extends Hack {

    public EnemyHack(int damageModifier) {
        super(damageModifier);
        this.resourceCost = 0;
        this.percentToMiss = 0.20f;
        this.numberOfTurnsToCast = 2;
        this.statusEffect = new HackStatus(damageModifier + damageAmt, 2);
    }

    @Override
    public AttackResult useSkill(String nameOfUser) {
        return new AttackResult(nameOfUser + "'s Hack did " + damageAmt + " damage and applied the Hack effect!",
                damageAmt, resourceCost, this.statusEffect);
    }
}
