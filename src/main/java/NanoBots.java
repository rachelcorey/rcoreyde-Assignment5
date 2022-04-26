package main.java;

import java.util.ArrayList;

public class NanoBots extends Player {

    ArrayList<IndividualNanoBot> bots;
    int baseNumOfBots;
    int maxHPperBot;

    public NanoBots(String name, PlayerType playerType) {
        super(name, playerType);
        this.baseNumOfBots = 3;
        this.maxHPperBot = 5;
        bots = new ArrayList<>(baseNumOfBots);
        for (int i = 0; i < baseNumOfBots; i++) {
            bots.add(new IndividualNanoBot(maxHPperBot));
        }
        this.currentHP = baseNumOfBots * 5;
        this.totalHP = baseNumOfBots * 5;
        this.atkPower = 3;
        // Assign unique skills for player class
        // Pass the power modifier to the skill based on strength
        this.resource = new MagneticCohesion();
        this.specialSkill = new Skill[2];
        specialSkill[0] = new BuildOneBot(this.atkPower);
        specialSkill[1] = new SlowHack(this.atkPower + 10);
        this.physicalAttack = new BasicPhysicalNanobots(atkPower/2);
        this.physicalAttack.damageAmt = this.atkPower;
    }

    @Override
    public void resetSpells() {
        int currentNumBots = bots.size();
        if (bots.size() > baseNumOfBots) {
            for (int i = 0; i < bots.size(); i++) {
                IndividualNanoBot bot = bots.get(i);
                if (bots.size() >= baseNumOfBots) {
                    bots.remove(bot);
                }
            }
        }
//        if (bots.size() < numBots) {
//            for (int i = 0; i < numBots - bots.size(); i++) {
//                bots.add(new IndividualNanoBot(bots.size()));
//            }
//        }
        System.out.println("Bots were reset to " + baseNumOfBots + ". Any injured bots were recycled!");
    }

    public void addMultipleBots(int numBots) {
        for (int i = 0; i < numBots; i++) {
            bots.add(new IndividualNanoBot(maxHPperBot));
        }
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

    public void addOneBot() {
        int numBots = bots.size();
        for (int i = 0; i < numBots - 1; i++) {
            bots.add(new IndividualNanoBot(maxHPperBot));
        }
    }

    public int getBaseNumOfBots() {
        return bots.size();
    }
}
