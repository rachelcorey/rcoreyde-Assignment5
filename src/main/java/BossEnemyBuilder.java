package main.java;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is used to build the boss enemy.
 * It satisfies the Builder Design Pattern.
 */
public class BossEnemyBuilder implements EnemyBuilder {

    Enemy enemy;

    public BossEnemyBuilder() {
        enemy = new Enemy();
        enemy.inventory = new ArrayList<Item>();
        enemy.loot = new ArrayList<Item>();
    }

    @Override
    public Enemy getEnemy() {
        return enemy;
    }

    @Override
    public void buildName() {
        enemy.setName(EnemyNameGenerator.generateRandomName(true));
    }

    @Override
    public void buildSkill() {
        Random random = new Random();
        int skill = random.nextInt(4) + 1;
        if (skill == 1) {
            enemy.setSkill(new SaltSpray(enemy.getAtkPower()));
        } else if (skill == 2) {
            enemy.setSkill(new EnemyHack(enemy.getAtkPower()));
        } else {
            enemy.setSkill(new HeavyBlow(enemy.getAtkPower()));
        }
    }

    // Boss enemies can have a maximum of 5 healing potions
    @Override
    public void buildInventory() {
        int maxItems = 5;
        Random random = new Random();
        int numItems = random.nextInt(maxItems) + 1;
        for (int i = 0; i < numItems; i++) {
            enemy.getInventory().add(new RepairKit(enemy.getLevel()));
        }
    }

    // Boss enemies can drop up to 4 items and 1 guaranteed rare item
    @Override
    public void buildLoot() {
        Random random = new Random();
        int numItems = random.nextInt(5);
        if (numItems > 1) {
            for (int i = 0; i < numItems; i++) {
                Random random2 = new Random();
                int itemType = random2.nextInt(2);
                if (itemType == 0) {
                    enemy.getLoot().add(new RepairKit(enemy.getLevel()));
                } else {
                    enemy.getLoot().add(new PowerConduit(enemy.getLevel()));
                }
            }
        }
        Random random3 = new Random();
        int itemType = random3.nextInt(2);
        int itemLevel = random3.nextInt(5) + 1;
        if (itemType == 0) {
            enemy.getLoot().add(new UpgradeModule(itemLevel));
        } else {
            enemy.getLoot().add(new GrapplingClaw(itemLevel));
        }
    }
}
