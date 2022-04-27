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
        this.damageAmt += powerModifier;
    }

    public abstract AttackResult useSkill(String nameOfUser);

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
