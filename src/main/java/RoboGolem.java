package main.java;

public class RoboGolem extends Player {

    // This satisfies Requirement #4
    public RoboGolem(String name, PlayerType playerType) {
        super(name, playerType);
        this.speed -= 5;
        // This satisfies Requirement #5
        this.resource = new Steam();
        this.specialSkill = new Skill[2];
        specialSkill[0] = new HeavyBlow(this.atkPower);
        specialSkill[1] = new MultiSlash(this.atkPower - 5);
        this.physicalAttack.damageAmt += this.atkPower;
        this.implementPlayerType(playerType);
    }

    @Override
    public void resetSpells() {
        // not applicable to this class
    }

}
