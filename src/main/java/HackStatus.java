package main.java;

public class HackStatus extends StatusEffect {

    public HackStatus(int power, int numOfTurns) {
        super(power, numOfTurns);
        this.name = "Hacked";
        this.description = "A status effect that stuns and damages the enemy for " + this.numOfTurns + " turns.";
    }

}
