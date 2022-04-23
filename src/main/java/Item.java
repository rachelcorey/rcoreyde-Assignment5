package main.java;

public abstract class Item {

    String name;
    String description;
    int level;
    int amtOfEffect;
    int duration;
    int turnsUsedSoFar;

    public Item (int level) {
        this.level = level;
        this.turnsUsedSoFar = 0;
    }

    public String getName() {
        return name;
    }

    public int getTurnsUsedSoFar() {
        return turnsUsedSoFar;
    }

    public abstract void use(Enemy enemy);

    public abstract void use(Player player);

    public String getDescription() {
        return description;
    }

    public int getLevel() {
        return level;
    }

    public void setTurnsUsedSoFar(int turnsUsedSoFar) {
        this.turnsUsedSoFar = turnsUsedSoFar;
    }

    public int getDuration() {
        return duration;
    }

    public void incrementTurnsUsedSoFar() {
        this.turnsUsedSoFar++;
    }

    public int getAmtOfEffect() {
        return amtOfEffect;
    }

}
