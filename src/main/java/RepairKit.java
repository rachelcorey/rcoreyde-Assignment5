package main.java;

public class RepairKit extends Item {

    public RepairKit (int level) {
        super(level);
        this.name = "Repair Kit";
        this.description = "A kit that can be used to repair damaged parts of a bot. Repairs both HP and Resource Amount.";
        this.amtOfEffect = 7 * level;
    }
}
