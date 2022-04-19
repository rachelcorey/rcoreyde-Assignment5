package main.java;

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
    Item[] inventory;

    public Player(String name, PlayerType playerType) {
        this.name = name;
        this.HP = 20;
        this.speed = 10;
        this.atkPower = 5;
        this.level = 1;
        this.expCurrent = 0;
        this.expRequiredToLevel = 100;
        this.currentStatusEffect = null;
        this.inventory = new Item[10];
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
        return physicalAttack.useSkill();
    }

    public abstract AttackResult specialAttack(int atkNum);

    public void useItem(Item item) {

    }

    public void levelUp() {

    }
}
