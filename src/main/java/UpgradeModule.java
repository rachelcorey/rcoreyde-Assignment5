package main.java;

public class UpgradeModule extends Item {

    public UpgradeModule (int level) {
        super(level);
        this.name = "Upgrade Module";
        this.description = "A rare module that allows you to upgrade your bot by " + level + " levels.";
        this.amtOfEffect = level;
    }
}
