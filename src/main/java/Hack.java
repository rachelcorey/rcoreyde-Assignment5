package main.java;

public abstract class Hack extends Skill {

    public Hack(int damageModifier) {
        super(damageModifier);
        this.name = "Hack";
        this.damageAmt = 2 + damageModifier;
        this.description = "A hack that renders the enemy useless for several turns "
                + "and damages them for " + damageAmt + " damage.";
        this.aoeDmg = 0;
        this.hitNumber = 1;
    }
}
