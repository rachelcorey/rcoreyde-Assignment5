package main.java;

import java.util.ArrayList;

public abstract class Player {

    String name;
    int level;
    int HP;
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
        this.HP = 20;
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
            this.HP = 40;
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
        this.HP += health;
    }

    public void takeDamage(int damage) {
        this.HP -= damage;
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

    public int getHP() {
        return HP;
    }

    public int getAtkPower() {
        return atkPower;
    }

    public int getSpeed() {
        return speed;
    }

    public void levelUp() {

    }

    public String getName() {
        return name;
    }
}
