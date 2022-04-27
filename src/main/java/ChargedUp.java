package main.java;

public class ChargedUp extends StatusEffect{

    public ChargedUp(int power, int numOfTurns) {
        super(power, numOfTurns);
        this.name = "Charged Up";
        this.description = "A status effect that powers up your bot for " + this.numOfTurns + " turns.";
    }
}
