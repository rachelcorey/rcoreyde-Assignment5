package main.java;

import java.util.ArrayList;

public interface EnemyPlan {

    public void setPowerLevel(int powerLevel);

    public void setFloorNumber(int floorNumber);

    public void setSkill(Skill skill);

    public void setLoot(ArrayList<Item> loot);
}
