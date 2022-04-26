package main.java;

import java.util.ArrayList;

public abstract class Player {

    String name;
    int level;
    int currentHP;
    int totalHP;
    int speed;
    int atkPower;
    Resource resource;
    int expCurrent;
    int expRequiredToLevel;
    StatusEffect currentStatusEffect;
    Skill physicalAttack;
    Skill[] specialSkill;
    ArrayList<Item> inventory;
    Item currentItemBuff;

    public Player(String name, PlayerType playerType) {
        this.name = name;
        this.currentHP = 20;
        this.totalHP = 20;
        this.speed = 10;
        this.atkPower = 5;
        this.level = 1;
        this.expCurrent = 0;
        this.expRequiredToLevel = 100;
        this.currentStatusEffect = null;
        this.currentItemBuff = null;
        this.inventory = new ArrayList<>();
        this.physicalAttack = new BasicPhysical(atkPower/2);
        this.implementPlayerType(playerType);
    }

    public void implementPlayerType(PlayerType playerType) {
        if (playerType.equals(PlayerType.HOVERBOT)) {
            this.speed = 20;
        } else if (playerType.equals(PlayerType.CAPACITORBOT)) {
            int newResourceAmt = (int) (this.resource.getTotalAmount() + (this.resource.getTotalAmount() * 0.50));
            this.resource.setCurrentAmount(newResourceAmt);
            this.resource.setTotalAmount(newResourceAmt);
        } else if (playerType.equals(PlayerType.TUNGSTENBOT)) {
            this.totalHP = 40;
            this.currentHP = 40;
        } else if (playerType.equals(PlayerType.RADIOACTIVEBOT)) {
            this.atkPower = 10;
            this.physicalAttack.setDamageAmt(5);
        }
    };

    public AttackResult physicalAttack() {
        return physicalAttack.useSkill(this.getName());
    }

    public AttackResult specialAttack(int atkNum) {
        return specialSkill[atkNum].useSkill(this.getName());
    }

    public void useItem(Item item) {
        item.use(this);
    }

    public void addHealth(int health) {
        this.currentHP += health;
    }

    public void takeDamage(int damage) {
        this.currentHP -= damage;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void increaseAtkPower(int atkPower) {
        this.atkPower += atkPower;
    }

    public void increaseResource(int resource) {
        this.resource.increaseCurrentAmount(resource);
    }

    public void setCurrentItemBuff(Item currentItemBuff) {
        this.currentItemBuff = currentItemBuff;
    }

    public Item getCurrentItemBuff() {
        return currentItemBuff;
    }

    public void setCurrentStatusEffect(StatusEffect currentStatusEffect) {
        this.currentStatusEffect = currentStatusEffect;
    }

    public StatusEffect getCurrentStatusEffect() {
        return currentStatusEffect;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public int getAtkPower() {
        return atkPower;
    }

    public int getSpeed() {
        return speed;
    }

    public void levelUp() {
        this.level++;
        this.expRequiredToLevel += 150;
        this.currentHP += 10;
        this.totalHP += 10;
        this.speed += 5;
        this.atkPower += 5;
        this.resource.increaseTotalAmount(10);
        this.resource.restoreFullAmount();
        this.physicalAttack.increaseDamageAmt(5);
        this.specialSkill[0].increaseDamageAmt(5);
        this.specialSkill[1].increaseDamageAmt(5);
    }

    public void useResource(int resource) {
        this.resource.decreaseCurrentAmount(resource);
    }

    public String getName() {
        return name;
    }

    public int getExpCurrent() {
        return expCurrent;
    }

    public int getExpRequiredToLevel() {
        return expRequiredToLevel;
    }

    public boolean addExp(int exp) {
        this.expCurrent += exp;
        if (this.expCurrent >= this.expRequiredToLevel) {
            int rollover = this.expCurrent - this.expRequiredToLevel;
            this.expCurrent = rollover;
            this.levelUp();
            return true;
        }
        return false;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getLevel() {
        return level;
    }

    public void addResource(int resource) {
        this.resource.increaseCurrentAmount(resource);
    }

    public void increaseSpeed(int amt) {
        this.speed += amt;
    }

    public void emptyInventory() {
        this.inventory.clear();
    }
}
