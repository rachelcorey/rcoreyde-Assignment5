package main.java;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is used to build a MagicEnemy.
 * This satisfies the Builder Design Pattern.
 */
public class MagicEnemyBuilder implements EnemyBuilder {

    Enemy enemy;

    public MagicEnemyBuilder() {
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
        enemy.setName(EnemyNameGenerator.generateRandomName(false));
    }

    @Override
    public void buildSkill() {
        Random random = new Random();
        int skill = random.nextInt(3) + 1;
        if (skill == 1) {
            enemy.setSkill(new SaltSpray(enemy.getAtkPower()));
        } else {
            enemy.setSkill(new EnemyHack(enemy.getAtkPower()));
        }
    }

    // Magic enemies can have a maximum of 5 healing potions
    @Override
    public void buildInventory() {
        int maxItems = 5;
        Random random = new Random();
        int numItems = random.nextInt(maxItems) + 1;
        for (int i = 0; i < numItems; i++) {
            enemy.getInventory().add(new RepairKit(enemy.getLevel()));
        }
    }

    // Magic enemies can drop 2 items
    @Override
    public void buildLoot() {
        Random random = new Random();
        int numItems = random.nextInt(3);
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
    }
}
