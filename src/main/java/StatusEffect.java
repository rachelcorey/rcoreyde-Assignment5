package main.java;

/**
 * StatusEffect is an abstract class that represents a status effect that can be applied to a unit.
 * It has several child classes: HackStatus, Rust, and ChargedUp
 * This satisfies Requirement #9
 */
public abstract class StatusEffect {
    String name;
    String description;
    int power;
    int turnsElapsed;
    int numOfTurns;
    boolean doesDamage;

    public StatusEffect(int power, int numOfTurns) {
        this.power = power;
        this.numOfTurns = numOfTurns + 2;
        this.turnsElapsed = 0;
        this.doesDamage = false;
    }

    public void applyEffect(Player player) {
        player.setCurrentStatusEffect(this);
        player.takeDamage(this.power);
        this.turnsElapsed++;
    }

    public void applyEffect(Enemy enemy) {
        enemy.setCurrentStatusEffect(this);
        enemy.takeDamage(this.power);
        this.turnsElapsed++;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public String getDescription() {
        return description;
    }

    public int getTurnsElapsed() {
        return turnsElapsed;
    }

    public int getNumOfTurns() {
        return numOfTurns;
    }
}
