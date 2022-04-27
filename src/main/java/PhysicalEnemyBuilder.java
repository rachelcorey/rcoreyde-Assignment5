package main.java;

import java.util.ArrayList;
import java.util.Random;

/**
 * PhysicalEnemyBuilder is a class that builds PhysicalEnemy objects.
 * This satisfies the Builder Design Pattern.
 */
public class PhysicalEnemyBuilder implements EnemyBuilder {

    Enemy enemy;

    public PhysicalEnemyBuilder() {
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
        enemy.setSkill(new HeavyBlow(enemy.getLevel()));
    }

    @Override
    public void buildInventory() {
        int maxItems = 2;
        Random random = new Random();
        int numItems = random.nextInt(maxItems) + 1;
        for (int i = 0; i < numItems; i++) {
            enemy.getInventory().add(new RepairKit(enemy.getAtkPower()));
        }
    }

    @Override
    public void buildLoot() {
        Random random = new Random();
        int numItems = random.nextInt(2);
        if (numItems == 1) {
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
