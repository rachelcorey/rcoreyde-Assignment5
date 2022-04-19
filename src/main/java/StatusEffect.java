package main.java;

public abstract class StatusEffect {
    String name;
    int power;
    int numOfTurns;

    public StatusEffect(int power, int numOfTurns) {
        this.power = power;
        this.numOfTurns = numOfTurns;
    }
}
