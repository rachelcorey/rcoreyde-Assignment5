package main.java;

public abstract class StatusEffect {
    String name;
    String description;
    int power;
    int turnsElapsed;
    int numOfTurns;

    public StatusEffect(int power, int numOfTurns) {
        this.power = power;
        this.numOfTurns = numOfTurns;
        this.turnsElapsed = 0;
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
