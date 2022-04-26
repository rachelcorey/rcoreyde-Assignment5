package main.java;

public class RepairKit extends Item {

    public RepairKit (int level) {
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
        player.addHealth(amtOfEffect);
        player.addResource(amtOfEffect);
        System.out.println("Player was healed by " + amtOfEffect + " HP and gained " + amtOfEffect + " Resource!");
    }
}
