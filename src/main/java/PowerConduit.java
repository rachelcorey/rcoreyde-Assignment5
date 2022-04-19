package main.java;

public class PowerConduit extends Item {

    public PowerConduit (int level) {
        super(level);
        this.name = "Power Conduit";
        this.description = "A conduit crackling with power that grants your bot extra Resource.";
        this.amtOfEffect = 10 * level;
    }
}
