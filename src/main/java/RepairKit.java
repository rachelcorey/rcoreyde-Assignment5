package main.java;

public class RepairKit extends Item {

    public RepairKit(int level) {
        super(level);
        this.name = "Repair Kit";
        this.description = "A kit that can be used to repair damaged parts of a bot. Repairs both HP and Resource Amount.";
        this.amtOfEffect = 7 * (level + 1);
    }

    @Override
    public void use(Enemy enemy) {
        enemy.addHealth(amtOfEffect);
        System.out.println("Enemy was healed by " + amtOfEffect + " HP!");
    }

    @Override
    public void use(Player player) {
        player.addResource(amtOfEffect);
        if (player instanceof NanoBots) {
            NanoBots nanoPlayer = (NanoBots) player;
            nanoPlayer.addMultipleBots(amtOfEffect);
            System.out.println(player.getName() + " gained " + amtOfEffect + " additional bots and " + amtOfEffect + " resource!");
        } else {
            player.addHealth(amtOfEffect);
            System.out.println(player.getName() + " was healed " + amtOfEffect + " HP and gained " + amtOfEffect + " Resource!");
        }
    }
}
