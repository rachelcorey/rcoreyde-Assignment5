package main.java;

public class UpgradeModule extends Item {

    public UpgradeModule (int level) {
        super(level);
        this.name = "Upgrade Module";
        this.description = "A rare module that allows you to upgrade your bot by " + level + " levels.";
        this.amtOfEffect = level;
    }

    @Override
    public void use(Enemy enemy) {
        // enemy cannot use this item
    }

    @Override
    public void use(Player player) {
        for (int i = 0; i < amtOfEffect; i++) {
           player.levelUp();
        }
        System.out.println("You have upgraded your bot by " + amtOfEffect + " levels!!!");
    }
}
