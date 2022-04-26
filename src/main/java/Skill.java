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

    public AttackResult useSkill(String nameOfUser) {
        Random rand = new Random();
        if (rand.nextInt(100) < 100 * percentToMiss) {
            return new AttackResult(nameOfUser + "'s " + name + " missed!", 0, resourceCost, null);
        }
        return new AttackResult(nameOfUser + "'s " + name + " did " + damageAmt + " damage!", damageAmt, resourceCost, statusEffect);
    }

    public void setDamageAmt(int damageAmt) {
        this.damageAmt = damageAmt;
    }

    public String getName() {
        return name;
    }

    public void increaseDamageAmt(int amt) {
        this.damageAmt += amt;
    }

    public String getDescription() {
        return description;
    }
}
