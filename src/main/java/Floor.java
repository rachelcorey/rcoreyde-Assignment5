package main.java;

public class Floor {
    int number;
    Enemy enemy;

    public Floor(int number) {
        this.number = number;
        enemy = generateEnemy();
    }

    private Enemy generateEnemy() {
        Enemy enemy;
        if (number % 10 == 0) {
            EnemyBuilder bossBuilder = new BossEnemyBuilder();
            EnemyEngineer bossEngineer = new EnemyEngineer(bossBuilder);
            bossEngineer.constructEnemy();
            enemy = bossBuilder.getEnemy();
        } else if (number % 5 == 0) {
            EnemyBuilder eliteBuilder = new MagicEnemyBuilder();
            EnemyEngineer eliteEngineer = new EnemyEngineer(eliteBuilder);
            eliteEngineer.constructEnemy();
            enemy = eliteBuilder.getEnemy();
        } else {
            EnemyBuilder normalBuilder = new PhysicalEnemyBuilder();
            EnemyEngineer normalEngineer = new EnemyEngineer(normalBuilder);
            normalEngineer.constructEnemy();
            enemy = normalBuilder.getEnemy();
        }
        return enemy;
    }

    public int getNumber() {
        return number;
    }
}
