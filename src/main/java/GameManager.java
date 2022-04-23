package main.java;

import java.util.ArrayList;
import java.util.Scanner;

public class GameManager {

    Player player;
    boolean testMode;
    boolean isHumanPlayerGame;
    boolean isGameActive;
    int totalDungeons;
    int floorsPerDungeon;
    Enemy currentEnemy;
    Dungeon currentDungeon;
    ArrayList<Dungeon> worldMap;

    public GameManager(String playerName, String playerClass, PlayerType playerType, boolean testMode, boolean isHumanPlayerGame) {
        player = PlayerFactory.createPlayer(playerName, playerClass, playerType);
        this.testMode = testMode;
        this.isHumanPlayerGame = isHumanPlayerGame;
        if (testMode) {
            totalDungeons = 1;
            floorsPerDungeon = 5;
        } else {
            totalDungeons = 5;
            floorsPerDungeon = 10;
        }
        this.worldMap = generateWorldMap();
        currentDungeon = worldMap.get(0);
        isGameActive = true;
        conductHumanPlayerGame();
    }

    private ArrayList<Dungeon> generateWorldMap() {
        ArrayList<Dungeon> worldMap = new ArrayList<>();
        int numOfFloors = 0;
        if (testMode) {
            numOfFloors = 5;
        } else {
            numOfFloors = 10;
        }
        for (int i = 0; i < totalDungeons; i++) {
            worldMap.add(new Dungeon(i, numOfFloors));
        }
        return worldMap;
    }

    public void conductHumanPlayerGame() {
        while (isGameActive) {
            if ((currentDungeon.getCurrentFloor().getNumber() == floorsPerDungeon) &&
                    (currentDungeon.getNumber() == totalDungeons)) {
                isGameActive = false;
                System.out.println("You have completed the game!");
            }

            currentEnemy = currentDungeon.getCurrentFloor().getEnemy();
            System.out.println("You are in dungeon " + currentDungeon.getNumber() + " on Floor " +
                    "number " + currentDungeon.getCurrentFloor().getNumber() + "!");
            boolean isBattleOver = false;

            System.out.println("You are now facing " + currentEnemy.getName() + "!");
            while (!isBattleOver) {
                AttackResult curResult = null;
                printCurrentStatsOfBattle();
                if (player.getCurrentStatusEffect() != null) {
                    System.out.println("You are under the effects of " + player.getCurrentStatusEffect().getName() + "!");
                    System.out.println("You take " + player.getCurrentStatusEffect().getPower() + " damage!");
                    player.takeDamage(player.getCurrentStatusEffect().getPower());
                }
                if (currentEnemy.getCurrentStatusEffect() != null) {
                    System.out.println("Enemy is under the effects of " + currentEnemy.getCurrentStatusEffect().getName() + "!");
                    System.out.println("Enemy takes " + currentEnemy.getCurrentStatusEffect().getPower() + " damage!");
                    currentEnemy.takeDamage(currentEnemy.getCurrentStatusEffect().getPower());
                }

                if (player.getCurrentItemBuff() != null) {
                    if (player.getCurrentItemBuff().turnsUsedSoFar == player.getCurrentItemBuff().getDuration()) {
                        System.out.println("Your " + player.getCurrentItemBuff().getName() + " has worn off....");
                        player.setCurrentItemBuff(null);
                    } else {
                        System.out.println("You are under the effects of " + player.getCurrentItemBuff().getName() + "!");
                        player.getCurrentItemBuff().use(player);
                    }
                }

                if (player.getSpeed() >= currentEnemy.getSpeed()) {
                    System.out.println("You go first!");
                    curResult = displayTurnMenu();
                    handleResult(curResult, true);
                    System.out.println("");
                    printCurrentStatsOfBattle();
                    System.out.println("");
                    System.out.println("It's the enemy's turn now!");
                    curResult = currentEnemy.takeTurn();
                    handleResult(curResult, false);
                } else {
                    System.out.println("The enemy goes first!");
                    curResult = currentEnemy.takeTurn();
                    handleResult(curResult, false);
                    System.out.println("");
                    printCurrentStatsOfBattle();
                    System.out.println("");
                    curResult = displayTurnMenu();
                    handleResult(curResult, true);
                }
                if (currentEnemy.getCurrentHP() <= 0) {
                    isBattleOver = true;
                    System.out.println("You have defeated " + currentEnemy.getName() + "!");
                    System.out.println("");
                    if (currentEnemy.getLoot().size() > 0) {
                        System.out.println("The enemy dropped the following items:");
                        for (Item item : currentEnemy.getLoot()) {
                            System.out.println(item.getName());
                            player.getInventory().add(item);
                        }
                        System.out.println("The loot was added to your inventory!");
                    } else {
                        System.out.println("The enemy didn't drop any loot.... :( ");
                        System.out.println("");
                    }
                }
                if (player.getHP() <= 0) {
                    isBattleOver = true;
                    System.out.println("You have died!");
                    System.out.println("Going back to the beginning of the dungeon.....");
                    // TODO: write this
                }
            }

            if (currentDungeon.getCurrentFloor().getNumber() == floorsPerDungeon) {
                System.out.println("You have completed the dungeon!");
                System.out.println("You carefully journey to the next dungeon....");
                worldMap.remove(0);
                currentDungeon = worldMap.get(0);
            } else {
                System.out.println("You carefully ascend to the next floor...........");
                System.out.println("...........");
                System.out.println("");
                currentDungeon.ascendToNextFloor();
            }
        }
    }

    private void printCurrentStatsOfBattle() {
        System.out.println("Enemy's HP: " + currentEnemy.getCurrentHP() + " | Enemy's Attack: " + currentEnemy.getAtkPower() + " | Enemy's Speed: " + currentEnemy.getSpeed());
        System.out.println("Your HP: " + player.getHP() + " | Your " + player.resource.getName() + " " +
                "amount: " + player.resource.getCurrentAmount() + " | Your Attack: " + player.getAtkPower() + " | Your Speed: " + player.getSpeed());
    }

    private void handleResult(AttackResult result, boolean isPlayerTurn) {
        System.out.println(result.resultMsg);
        if (result.getDamageDealt() > 0) {
            if (isPlayerTurn) {
                currentEnemy.takeDamage(result.getDamageDealt());
            } else {
                player.takeDamage(result.getDamageDealt());
            }
        }
        if (result.getStatusEffect() != null) {
            if (isPlayerTurn) {
                player.setCurrentStatusEffect(result.getStatusEffect());
            } else {
                currentEnemy.setCurrentStatusEffect(result.getStatusEffect());
            }
        }
    }

    public AttackResult displayTurnMenu() {
        Scanner scanner = new Scanner(System.in);
        AttackResult result = null;

        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.println("It's your turn!");
            System.out.println("What would you like to do?");
            System.out.println("1. Attack");
            if (player.getInventory().size() > 0) {
                System.out.println("2. Use Item");
            }
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.println("What attack would you like to use?");
                System.out.println("1. Basic Attack");
                System.out.println("2. Special Attack");
                choice = scanner.nextInt();
                if (choice == 1) {
                    result = player.physicalAttack();
                    isValidInput = true;
                } else {
                    System.out.println("Which special attack would you like to use?");
                    System.out.println("1. " + player.specialSkill[0].getName());
                    System.out.println("2. " + player.specialSkill[1].getName());
                    choice = scanner.nextInt();
                    if (choice == 1) {
                        result = player.specialAttack(0);
                        isValidInput = true;
                    } else {
                        result = player.specialAttack(1);
                        isValidInput = true;
                    }
                }
            } else if (choice == 2 && player.getInventory().size() > 0) {
                System.out.println("Which item would you like to use?");
                int count = 1;
                for (Item item : player.getInventory()) {
                    System.out.println(count + ". " + item.getName());
                    count++;
                }
                choice = scanner.nextInt();
                player.useItem(player.getInventory().get(choice - 1));
                result = new AttackResult("", 0, null);
                isValidInput = true;
            } else {
                System.out.println("Invalid choice!");
            }
        }

        return result;
    }

    public void conductComputerPlayerGame() {

    }

    public String showTurnMenu() {
        return null;
    }

}
