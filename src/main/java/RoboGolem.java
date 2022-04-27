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
        this.specialSkill[0].damageAmt += this.atkPower;
        specialSkill[1] = new MultiSlash(this.atkPower);
        this.specialSkill[1].damageAmt += this.atkPower;
        this.physicalAttack.damageAmt += this.atkPower;
        this.implementPlayerType(playerType);
    }

    @Override
    public void resetSpells() {
        // not applicable to this class
    }

}
