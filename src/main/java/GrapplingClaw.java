package main.java;

public class GrapplingClaw extends Item {

    public GrapplingClaw(int level) {
        super(level);
        this.name = "Grappling Claw";
        this.description = "A rare grappling claw that can be temporarily installed on your bot to escape battle and "
                 + "advance " + level + " floors. Cannot be used on Boss fights.";
        this.amtOfEffect = level;
    }

    @Override
    public void use(Enemy enemy) {
        // enemy cannot use this item
    }

    @Override
    public void use(Player player) {
        Dungeon currentDungeon = GameManager.getInstance().getCurrentDungeon();
        int floorToSkipTo = amtOfEffect + currentDungeon.getCurrentFloor().getNumber();
        if (floorToSkipTo > GameManager.getInstance().getFloorsPerDungeon()) {
            floorToSkipTo = GameManager.getInstance().getFloorsPerDungeon() - 1;
        }
        amtOfEffect = floorToSkipTo - currentDungeon.getCurrentFloor().getNumber();
        GameManager.getInstance().getCurrentDungeon().setCurrentFloor(floorToSkipTo - 1);
        GameManager.getInstance().setBattleOver(true);
        System.out.println("Skipped ahead " + amtOfEffect + " floors, to floor " + floorToSkipTo + "!");
    }
}
