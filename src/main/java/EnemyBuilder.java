package main.java;

/**
 * EnemyBuilder is a class that builds enemies.
 * This satisfies the Builder Design Pattern.
 */
public interface EnemyBuilder {

    public Enemy getEnemy();

    public void buildName();

    public void buildSkill();

    public void buildInventory();

    public void buildLoot();
}
