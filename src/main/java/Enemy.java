package main.java;

import java.util.ArrayList;

public class Enemy implements EnemyPlan {

    String name;
    int level;
    int HP;
    int speed;
    int atkPower;
    int expAwarded;
    PowerLevel powerLevel;
    int floorNum;
    Skill skill;
    Skill physicalSkill;
    StatusEffect currentStatusEffect;
    ArrayList<Item> inventory;
    ArrayList<Item> loot;

    @Override
    public void setPowerLevel(int powerLevel) {
        this.powerLevel = PowerLevel.values()[powerLevel];
        this.currentStatusEffect = null;
        // set level, HP, speed, expAwarded, physicalSkill based on powerLevel and floorNum
        switch (powerLevel) {
            case 0:
                this.level = 1 + floorNum;
                this.HP = 12 * floorNum;
                this.speed = (int) (10 * (Math.floor(floorNum / 2)));
                this.atkPower = (int) (3 * (Math.floor(floorNum / 2)));
                this.expAwarded = 50 * floorNum;
                break;
            case 1:
                this.level = 2 + floorNum;
                this.HP = 20 * floorNum;
                this.speed = (int) (12 * (Math.floor(floorNum / 2)));
                this.atkPower = (int) (5 * (Math.floor(floorNum / 2)));
                this.expAwarded = 100 * floorNum;
                break;
            case 2:
                this.level = 3 + floorNum;
                this.HP = 30 * floorNum;
                this.speed = (int) (18 * (Math.floor(floorNum / 2)));
                this.atkPower = (int) (8 * (Math.floor(floorNum / 2)));
                this.expAwarded = 200 * floorNum;
                break;
        }
        this.physicalSkill = new BasicPhysical(atkPower/2);
    }

    @Override
    public void setFloorNumber(int floorNumber) {
        this.floorNum = floorNumber;
    }

    @Override
    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    @Override
    public void setLoot(ArrayList<Item> loot) {
        this.loot = loot;
    }

    public void takeTurn() {

    }

    public AttackResult attack() {
        return null;
    }

    public void useItem(Item item) {

    }

    public void setName(String name) {
        this.name = name;
    }

    public PowerLevel getPowerLevel() {
        return powerLevel;
    }

    public int getAtkPower() {
        return atkPower;
    }

    public int getLevel() {
        return level;
    }

    public ArrayList<Item> getLoot() {
        return loot;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

}
