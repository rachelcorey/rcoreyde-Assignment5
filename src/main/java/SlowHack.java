package main.java;

public class SlowHack extends Hack {

    int castingTurnsElapsed;

    public SlowHack(int damageModifier) {
        super(damageModifier);
        this.name = "Slow Hack";
        this.description = "A hack that takes longer to cast but does more damage and lasts for the rest of the battle.";
        this.resourceCost = 15;
        this.percentToMiss = 0.10f;
        this.castingTurnsElapsed = 0;
        this.numberOfTurnsToCast = 3;
        this.statusEffect = new HackStatus(damageModifier + damageAmt, 100);
    }

    @Override
    public AttackResult useSkill(String nameOfUser) {
        if (castingTurnsElapsed == numberOfTurnsToCast) {
            castingTurnsElapsed = 0;
            GameManager.getInstance().getPlayer().setCasting(false);
            NanoBots player = (NanoBots) GameManager.getInstance().getPlayer();
            player.addOneBotEach();
            return new AttackResult( nameOfUser + "'s Bots each built one bot each! " + nameOfUser + " now has " + (player.getBaseNumOfBots() + 1) + " bots!",
                    0, resourceCost, this.statusEffect);

        } else {
            int cost = 0;
            if (castingTurnsElapsed == 0) {
                cost = resourceCost;
            }
            castingTurnsElapsed++;
            GameManager.getInstance().getPlayer().setCasting(true);
            return new AttackResult(nameOfUser + "'s Slow Hack is casting.....", 0, cost, null);
        }
    }

}
