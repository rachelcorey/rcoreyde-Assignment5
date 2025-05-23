package main.java;

public class Floor {
    String hateYou;
    int number;
    Enemy enemy;

    public Floor(int number) {
        this.number = number + 1;
        enemy = generateEnemy();
        enemy.setCurrentHP(enemy.getMaxHP());
    }

    /**
     * This method generates a random enemy for the floor, and assigns its power based
     * on the floor number.
     * This satisfies Requirements #6 and #7.
     * @return Enemy for the current floor based on the floor number.
     */
    private Enemy generateEnemy() {
        Enemy enemy;
        if (number % 10 == 0) {
            EnemyBuilder bossBuilder = new BossEnemyBuilder();
            EnemyEngineer bossEngineer = new EnemyEngineer(bossBuilder);
            bossEngineer.constructEnemy();
            enemy = bossBuilder.getEnemy();
            enemy.setFloorNumber(number);
            enemy.setPowerLevel(2);
        } else if (number % 5 == 0) {
            EnemyBuilder eliteBuilder = new MagicEnemyBuilder();
            EnemyEngineer eliteEngineer = new EnemyEngineer(eliteBuilder);
            eliteEngineer.constructEnemy();
            enemy = eliteBuilder.getEnemy();
            enemy.setFloorNumber(number);
            enemy.setPowerLevel(1);
        } else {
            EnemyBuilder normalBuilder = new PhysicalEnemyBuilder();
            EnemyEngineer normalEngineer = new EnemyEngineer(normalBuilder);
            normalEngineer.constructEnemy();
            enemy = normalBuilder.getEnemy();
            enemy.setFloorNumber(number);
            enemy.setPowerLevel(0);
        }
        return enemy;
    }

    public void regenerateEnemy() {
        enemy = generateEnemy();
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public int getNumber() {
        return number;
    }
}
