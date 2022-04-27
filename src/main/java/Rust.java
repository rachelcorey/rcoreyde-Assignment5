package main.java;

public class Rust extends StatusEffect {

    public Rust(int power, int numOfTurns) {
        super(power, numOfTurns);
        this.name = "Rust";
        this.description = "A status effect that the enemy applies to your bot to do damage for " + this.numOfTurns + " turns.";
    }

}
