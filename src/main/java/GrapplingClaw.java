package main.java;

public class GrapplingClaw extends Item {

    public GrapplingClaw (int level) {
        super(level);
        this.name = "Grappling Claw";
        this.description = "A rare grappling claw that can be temporarily installed on your bot to escape battle and " +
                "advance " + level + " floors. Cannot be used on Boss fights.";
        this.amtOfEffect = level;
    }

    @Override
    public void use(Enemy enemy) {
        // enemy cannot use this item
    }

    @Override
    public void use(Player player) {
        // TODO: implement
        System.out.println("not implemented yet.");
    }
}
