package main.java;

import java.util.ArrayList;

public class GameManager {

    Player player;
    boolean testMode;
    boolean isGameActive;
    int totalDungeons;
    int floorsPerDungeon;
    Enemy currentEnemy;
    Dungeon currentDungeon;
    ArrayList<Dungeon> worldMap;

    public GameManager(String playerName, String playerClass, PlayerType playerType, boolean testMode) {
        player = PlayerFactory.createPlayer(playerName, playerClass, playerType);
        this.testMode = testMode;
        if (testMode) {
            totalDungeons = 1;
            floorsPerDungeon = 5;
        } else {
            totalDungeons = 5;
            floorsPerDungeon = 10;
        }
        this.worldMap = generateWorldMap();
        conductHumanPlayerGame();
    }

    private ArrayList<Dungeon> generateWorldMap() {
        return null;
    }

    public void conductHumanPlayerGame() {
        isGameActive = true;
        while (isGameActive) {

            if ((currentDungeon.getCurrentFloor().getNumber() == floorsPerDungeon) &&
                    (currentDungeon.getNumber() == totalDungeons)) {
                isGameActive = false;
            }
        }
    }

    public String showTurnMenu() {
        return null;
    }

}
