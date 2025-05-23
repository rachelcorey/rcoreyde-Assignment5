package main.java;

import java.util.ArrayList;

public abstract class Player {

    String name;
    PlayerType type;
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
    boolean isCasting;

    // This satisfies Requirement #5
    public Player(String name, PlayerType playerType) {
        this.name = name;
        this.type = playerType;
        this.currentHP = 20;
        this.totalHP = 20;
        this.speed = 10;
        this.atkPower = 5;
        this.level = 1;
        this.isCasting = false;
        this.expCurrent = 0;
        this.expRequiredToLevel = 100;
        this.currentStatusEffect = null;
        this.currentItemBuff = null;
        this.inventory = new ArrayList<>();
        this.physicalAttack = new BasicPhysical(atkPower / 2);
    }

    // This satisfies Requirement #4
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
    }

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

    public void decreaseAtkPower(int atkPower) {
        this.atkPower -= atkPower;
    }

    public void decreaseSpeed(int speed) {
        this.speed -= speed;
    }

    public void decreaseResource(int resource) {
        this.resource.decreaseCurrentAmount(resource);
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
        this.expRequiredToLevel = 10 * this.level;
        this.currentHP = this.level * 3;
        this.totalHP = this.level * 5;
        this.speed = this.level * 3;
        this.atkPower = this.level * 2;
        this.resource.restoreFullAmount();
        this.physicalAttack.setDamageAmt(this.level + 1);
        this.specialSkill[0].setDamageAmt(this.level + 1);
        this.specialSkill[1].setDamageAmt(this.level + 1);
    }

    public Resource getResource() {
        return resource;
    }

    public void useResource(int resource) {
        if (this.resource.getCurrentAmount() >= resource) {
            this.resource.decreaseCurrentAmount(resource);
        }
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
            this.expCurrent = this.expCurrent - this.expRequiredToLevel;
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

    public boolean isCasting() {
        return isCasting;
    }

    public void setCasting(boolean casting) {
        isCasting = casting;
    }

    public int getTotalHP() {
        return totalHP;
    }

    public abstract void resetSpells();

    public PlayerType getType() {
        return type;
    }
}
