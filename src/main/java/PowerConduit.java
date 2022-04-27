package main.java;

public class PowerConduit extends Item {

    public PowerConduit(int level) {
        super(level);
        this.name = "Power Conduit";
        this.description = "A conduit crackling with power that grants your bot extra Resource and Attack Power"
                + " for " + this.duration + " turns.";
        this.amtOfEffect = 10 * (level + 1);
    }

    @Override
    public void use(Enemy enemy) {
        // enemy cannot use this item
    }

    @Override
    public void use(Player player) {
        System.out.println("Your bot has gained " + this.amtOfEffect + " Attack Power, " + this.amtOfEffect + " Speed, and " + this.amtOfEffect + " Resource from"
                + " the Power Conduit this turn!");
        player.increaseResource(amtOfEffect);
        player.increaseAtkPower(amtOfEffect);
        player.increaseSpeed(amtOfEffect);
        player.setCurrentItemBuff(this);
        if (this.getTurnsUsedSoFar() > 0) {
            this.incrementTurnsUsedSoFar();
        }
    }

}
