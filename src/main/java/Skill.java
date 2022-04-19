package main.java;

import java.util.Random;

public abstract class Skill {
    String name;
    String description;
    int damageAmt;
    int resourceCost;
    int aoeDmg;
    int hitNumber;
    float percentToMiss;
    int numberOfTurnsToCast;
    StatusEffect statusEffect;

    public Skill(int powerModifier) {
        this.damageAmt = 1;
        this.damageAmt = (damageAmt + (damageAmt * powerModifier));
    }

    public AttackResult useSkill() {
        Random rand = new Random();
        if (rand.nextInt(100) < 100 * percentToMiss) {
            return new AttackResult(name + " missed!", 0, null);
        }
        return new AttackResult(name + " did " + damageAmt + " damage!", damageAmt, statusEffect);
    }

    public void setDamageAmt(int damageAmt) {
        this.damageAmt = damageAmt;
    }
}
