package main.java;

public abstract class Item {

    String name;
    String description;
    int level;
    int amtOfEffect;

    public Item (int level) {
        this.level = level;
    }
}
