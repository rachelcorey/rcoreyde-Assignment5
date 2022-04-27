package main.java;

public class IndividualNanoBot {
    int currentHP;
    int maxHP;

    public IndividualNanoBot(int maxHP) {
        this.maxHP = maxHP;
        this.currentHP = maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public void takeDamage(int damage) {
        this.currentHP -= damage;
    }
}
