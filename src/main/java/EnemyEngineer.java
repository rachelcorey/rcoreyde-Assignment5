package main.java;

public class EnemyEngineer {

    EnemyBuilder enemyBuilder;

    public EnemyEngineer(EnemyBuilder enemyBuilder) {
        this.enemyBuilder = enemyBuilder;
    }

    public void constructEnemy() {
        enemyBuilder.buildName();
        enemyBuilder.buildSkill();
        enemyBuilder.buildLoot();
        enemyBuilder.buildInventory();
    }

    public Enemy getEnemy() {
        return enemyBuilder.getEnemy();
    }
}
