package main.java;

import java.util.ArrayList;

public class NanoBots extends Player {

    ArrayList<IndividualNanoBot> bots;
    int baseNumOfBots;
    int maxHPperBot;

    // This satisfies Requirement #3
    public NanoBots(String name, PlayerType playerType) {
        super(name, playerType);
        this.baseNumOfBots = 3;
        this.maxHPperBot = 5;
        bots = new ArrayList<>(baseNumOfBots);
        for (int i = 0; i < baseNumOfBots; i++) {
            bots.add(new IndividualNanoBot(maxHPperBot));
        }
        this.currentHP = baseNumOfBots * maxHPperBot;
        this.totalHP = baseNumOfBots * maxHPperBot;
        this.atkPower = 3;
        // This satisfies Requirement #5
        this.resource = new MagneticCohesion();
        this.specialSkill = new Skill[2];
        specialSkill[0] = new BuildOneBot(this.atkPower);
        specialSkill[1] = new BuildFourBots(this.atkPower);
        this.physicalAttack = new BasicPhysicalNanobots(atkPower / 2);
        this.physicalAttack.damageAmt = this.atkPower;
        this.implementPlayerType(playerType);
    }

    @Override
    public void resetSpells() {
        if (this.getCurrentHP() > 0) {
            int botOverflow = bots.size() - baseNumOfBots;
            if (bots.size() != baseNumOfBots) {
                for (int i = botOverflow; i > 0; i--) {
                    IndividualNanoBot bot = bots.get(i);
                    bots.remove(bot);
                }
            }
            System.out.println("Bots were reset to " + baseNumOfBots + ". Any injured bots were recycled!");
        } else {
            resetHP();
        }
    }

    public void resetHP() {
        this.currentHP = baseNumOfBots * maxHPperBot;
        this.totalHP = baseNumOfBots * maxHPperBot;
        this.resource = new MagneticCohesion();
        for (int i = 0; i < baseNumOfBots; i++) {
            bots.add(new IndividualNanoBot(maxHPperBot));
        }
        System.out.println("Bots have been rebuilt. HP was reset to " + currentHP + ".");
    }

    public void addMultipleBots(int numBots) {
        for (int i = 0; i < numBots; i++) {
            bots.add(new IndividualNanoBot(maxHPperBot));
        }
    }

    @Override
    public void levelUp() {
        this.level++;
        this.expRequiredToLevel += 150;
        this.baseNumOfBots += 1;
        this.maxHPperBot += 1;
        System.out.println("Base number of bots increased by 1!");
        bots.clear();
        for (int i = 0; i < baseNumOfBots; i++) {
            bots.add(new IndividualNanoBot(maxHPperBot));
        }
        this.currentHP = baseNumOfBots * maxHPperBot;
        this.totalHP = baseNumOfBots * maxHPperBot;
        this.speed += 2;
        this.atkPower += 1;
        this.resource.increaseTotalAmount(4);
        this.resource.restoreFullAmount();
        this.physicalAttack.increaseDamageAmt(2);
    }

    @Override
    public void takeDamage(int damage) {
        bots.get(0).takeDamage(damage);
        if (bots.get(0).getCurrentHP() <= 0) {
            bots.remove(0);
            System.out.println("One of your bots was destroyed!");
            System.out.println("You now have " + bots.size() + " bots.");
            System.out.println("");
        }
    }

    @Override
    public int getCurrentHP() {
        int total = 0;
        for (IndividualNanoBot bot : bots) {
            total += bot.getCurrentHP();
        }
        return total;
    }

    public void addOneBotEach() {
        int numBots = bots.size();
        for (int i = 0; i < numBots - 1; i++) {
            bots.add(new IndividualNanoBot(maxHPperBot));
        }
    }


    public void addFourBotsEach() {
        int numBotsToBuild = bots.size() * 4;
        for (int i = 0; i < numBotsToBuild; i++) {
            bots.add(new IndividualNanoBot(maxHPperBot));
        }
    }

    public int getBaseNumOfBots() {
        return baseNumOfBots;
    }

    public int getNumOfBots() {
        return bots.size();
    }
}
