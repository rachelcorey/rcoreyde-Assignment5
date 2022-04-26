package main.java;

public class BuildFourBots extends BuildOneBot {

    int castingTurnsElapsed;

    public BuildFourBots(int powerModifier) {
        super(powerModifier);
        this.name = "Build Four NanoBots";
        this.description = "Each of your NanoBots builds four additional NanoBots each. Needs 3 turns to cast.";
        this.resourceCost = 6;
        this.percentToMiss = 0;
        this.numberOfTurnsToCast = 3;
    }


    @Override
    public AttackResult useSkill(String nameOfUser) {
        System.out.println("number of turns to cast: " + numberOfTurnsToCast);
        System.out.println("number of turns elapsed: " + castingTurnsElapsed);
        if (castingTurnsElapsed + 1 == numberOfTurnsToCast) {
            castingTurnsElapsed = 0;
            GameManager.getInstance().getPlayer().setCasting(false);
            NanoBots player = (NanoBots) GameManager.getInstance().getPlayer();
            player.addFourBotsEach();
            return new AttackResult( nameOfUser + "'s Bots each built four bots each! " + nameOfUser + " now has " + player.getNumOfBots() + " bots!",
                    0, 0, this.statusEffect);

        } else {
            int cost = 0;
            if (castingTurnsElapsed == 0) {
                cost = resourceCost;
            }
            castingTurnsElapsed++;
            GameManager.getInstance().getPlayer().setCasting(true);
            return new AttackResult(nameOfUser + "'s Bots are each constructing four bots each.....", 0, cost, null);
        }
    }
}
