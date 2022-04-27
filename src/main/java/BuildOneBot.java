package main.java;

public class BuildOneBot extends Skill {

    public BuildOneBot(int powerModifier) {
        super(powerModifier);
        this.name = "Build One NanoBot";
        this.description = "Each of your NanoBots builds one additional NanoBot each.";
        this.resourceCost = 3;
        this.percentToMiss = 0;
        this.numberOfTurnsToCast = 1;
    }

    @Override
    public AttackResult useSkill(String nameOfUser) {
        NanoBots player = (NanoBots) GameManager.getInstance().getPlayer();
        player.addOneBotEach();
        player.setCasting(false);
        return new AttackResult(nameOfUser + "'s Bots each built one bot each! " + nameOfUser + " now has " + (player.getNumOfBots() + 1) + " bots!",
                0, resourceCost, this.statusEffect);
    }
}
