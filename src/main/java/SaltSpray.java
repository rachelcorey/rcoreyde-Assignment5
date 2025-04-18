package main.java;

public class SaltSpray extends Skill {

    public SaltSpray(int damageModifier) {
        super(damageModifier);
        this.name = "Salt Spray";
        this.description = "A spray of salt that deals damage over time to enemies for the rest of the battle, unless cleansed.";
        this.damageAmt = 2;
        this.resourceCost = 0;
        this.hitNumber = 1;
        this.percentToMiss = 0.30f;
        this.numberOfTurnsToCast = 1;
        this.statusEffect = new Rust(damageModifier + damageAmt, 100);
    }

    @Override
    public AttackResult useSkill(String nameOfUser) {
        return new AttackResult(nameOfUser + "'s Salt Spray did " + damageAmt + " damage and applied the Rust effect for the rest of the battle!",
                damageAmt, resourceCost, this.statusEffect);
    }
}
