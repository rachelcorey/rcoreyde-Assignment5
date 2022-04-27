package main.java;

import java.util.Random;

public abstract class Skill {
    String name;
    String description;
    int damageAmt;
    int resourceCost;
    int hitNumber;
    float percentToMiss;
    float percentToCrit;
    int numberOfTurnsToCast;
    StatusEffect statusEffect;

    public Skill(int powerModifier) {
        this.damageAmt = 1;
        this.percentToCrit = 0.10f;
        this.damageAmt = (damageAmt + (damageAmt * powerModifier));
    }

    // This satisfies Requirement #5
    public AttackResult useSkill(String nameOfUser) {
        Random rand1 = new Random();
        Random rand2 = new Random();
        if (rand1.nextInt(100) < 100 * percentToMiss) {
            return new AttackResult(nameOfUser + "'s " + name + " missed!", 0, resourceCost, null);
        }
        if (rand2.nextInt(100) < 100 * percentToCrit) {
            return new AttackResult(nameOfUser + "'s " + name + " did a critical strike and dealt double damage for " + damageAmt * 2 + "!",
                    damageAmt * 2, resourceCost, null);
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

    public int getCost() {
        return resourceCost;
    }
}
