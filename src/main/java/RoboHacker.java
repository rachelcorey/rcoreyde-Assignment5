package main.java;

public class RoboHacker extends Player {

    // This satisfies Requirement #3
    public RoboHacker(String name, PlayerType playerType) {
        super(name, playerType);
        this.currentHP = 20;
        this.totalHP = 20;
        // This satisfies Requirement #5
        this.resource = new QuantumIons();
        this.specialSkill = new Skill[2];
        specialSkill[0] = new FastHack(this.atkPower);
        specialSkill[1] = new SlowHack(this.atkPower + 10);
        this.physicalAttack.damageAmt += this.atkPower;
        this.implementPlayerType(playerType);
    }

    @Override
    public void resetSpells() {
        specialSkill[1] = new SlowHack(this.atkPower);
    }

}
