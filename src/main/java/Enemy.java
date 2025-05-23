package main.java;

import java.util.ArrayList;
import java.util.Random;

public class Enemy implements EnemyPlan {

    String name;
    int level;
    int currentHP;
    int maxHP;
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

    /**
     * This method is used to set the enemy's stats based on their power level assigned
     * by the Floor class.
     * It satisfies Requirement #7
     * @param powerLevel the power level of the enemy
     */
    @Override
    public void setPowerLevel(int powerLevel) {
        this.powerLevel = PowerLevel.values()[powerLevel];
        this.currentStatusEffect = null;
        // set level, HP, speed, expAwarded, physicalSkill based on powerLevel and floorNum
        switch (powerLevel) {
            case 0:
                this.setLevel(floorNum);
                this.setMaxHP(10 * floorNum);
                this.setSpeed((int) (10 + (Math.ceil((double) floorNum / 0.5))));
                this.setAtkPower((int) (8 + (Math.ceil((double) floorNum / 0.5))));
                this.setExpAwarded(20 * (1 + floorNum));
                break;
            case 1:
                this.setLevel(2 * floorNum);
                this.setMaxHP(20 * floorNum);
                this.setSpeed((int) (15 * (Math.ceil((double) floorNum / 0.5))));
                this.setAtkPower((int) (15 * (Math.ceil((double) floorNum / 0.5))));
                this.setExpAwarded(50 * (1 + floorNum));
                break;
            case 2:
                this.setLevel(3 * floorNum);
                this.setMaxHP(25 * floorNum);
                this.setSpeed((int) (20 * (Math.ceil((double) floorNum / 0.5))));
                this.setAtkPower((int) (20 * (Math.ceil((double) floorNum / 0.5))));
                this.setExpAwarded(100 * (1 + floorNum));
                break;
            default:
                break;
        }
        this.setCurrentHP(this.getMaxHP());
        this.physicalSkill = new BasicPhysical(atkPower / 2);
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

    // This satisfies Requirement #2
    public AttackResult takeTurn() {
        Random rand = new Random();
        AttackResult result = null;
        int randomNum = rand.nextInt(2);
        if (randomNum == 1) {
            result = attack();
        } else {
            // This satisfies Requirement #8
            if (inventory.size() > 0 && this.getCurrentHP() < this.getMaxHP()) {
                System.out.println(name + " is using a " + inventory.get(0).getName() + "!: " + inventory.get(0).getDescription());
                useItem(inventory.get(0));
                result = new AttackResult("",0,0, null);
            } else {
                result = attack();
            }
        }

        return result;
    }

    public AttackResult attack() {
        Random rand = new Random();
        int randomNum = rand.nextInt(2);
        if (randomNum == 0) {
            return physicalSkill.useSkill(this.getName());
        } else {
            return skill.useSkill(this.getName());
        }
    }

    public void useItem(Item item) {
        inventory.remove(item);
        item.use(this);
    }

    public void addHealth(int health) {
        this.currentHP += health;
    }

    public void takeDamage(int damage) {
        this.currentHP -= damage;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PowerLevel getPowerLevel() {
        return powerLevel;
    }

    public String getName() {
        return name;
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

    public int getSpeed() {
        return speed;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void setCurrentStatusEffect(StatusEffect currentStatusEffect) {
        this.currentStatusEffect = currentStatusEffect;
    }

    public StatusEffect getCurrentStatusEffect() {
        return currentStatusEffect;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setAtkPower(int atkPower) {
        this.atkPower = atkPower;
    }

    public int getExpAwarded() {
        return expAwarded;
    }

    public void setExpAwarded(int expAwarded) {
        this.expAwarded = expAwarded;
    }

    public int getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(int floorNum) {
        this.floorNum = floorNum;
    }

    public Skill getSkill() {
        return skill;
    }

    public Skill getPhysicalSkill() {
        return physicalSkill;
    }

    public void setPhysicalSkill(Skill physicalSkill) {
        this.physicalSkill = physicalSkill;
    }

    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

}
