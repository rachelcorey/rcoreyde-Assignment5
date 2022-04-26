package main.java;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class GameManager {

    Player player;
    boolean testMode;
    boolean isHumanPlayerGame;
    boolean isGameActive;
    boolean playerJustLost;
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
        // Game loop
        while (isGameActive) {
            if ((currentDungeon.getCurrentFloor().getNumber() == floorsPerDungeon + 1) &&
                    (currentDungeon.getNumber() == totalDungeons + 1)) {
                isGameActive = false;
                System.out.println("You have completed the game!");
                break;
            }

            currentEnemy = currentDungeon.getCurrentFloor().getEnemy();
            System.out.println("You are in dungeon " + currentDungeon.getNumber() + " on Floor " +
                    "number " + currentDungeon.getCurrentFloor().getNumber() + "!");
            System.out.println("");

            System.out.println("You are now facing " + currentEnemy.getName() + "!");
            // Battle loop
            while (true) {
                printCurrentStatsOfBattle();
                printCurrentStatusEffects();
                printCurrentBuffEffects();

                if (checkIfBattleOver()) {
                    break;
                }

                if (player.getSpeed() >= currentEnemy.getSpeed()) {
                    System.out.println("You go first!");
                    handleTurn(true);
                    if (checkIfBattleOver()) {
                        break;
                    } else {
                        handleTurn(false);
                    }
                } else {
                    System.out.println("Enemy goes first!");
                    handleTurn(false);
                    if (checkIfBattleOver()) {
                        break;
                    } else {
                        handleTurn(true);
                    }
                }

            }
            if (!playerJustLost) {
                printFloorNavigation();
            } else {
                playerJustLost = false;
            }
        }
        System.out.println("Final stats:");
        printPlayerStats();
        System.out.println("");
        System.out.println("Thanks for playing!");
        exit(0);
    }

    private void printFloorNavigation() {
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

    private void printCurrentStatusEffects() {
        if (player.getCurrentStatusEffect() != null) {
            if (player.getCurrentStatusEffect().getTurnsElapsed() == player.getCurrentStatusEffect().getNumOfTurns()) {
                System.out.println("The status effect " + player.getCurrentStatusEffect().getName() + " wore off....");
                player.setCurrentStatusEffect(null);
            } else {
                System.out.println("You are under the effects of " + player.getCurrentStatusEffect().getName() + "!:");
                System.out.println(player.getCurrentStatusEffect().getDescription());
                System.out.println("You take " + player.getCurrentStatusEffect().getPower() + " damage!");
                player.getCurrentStatusEffect().applyEffect(player);
            }
        }
        if (currentEnemy.getCurrentStatusEffect() != null) {
            if (currentEnemy.getCurrentStatusEffect().getTurnsElapsed() == currentEnemy.getCurrentStatusEffect().getNumOfTurns()) {
                System.out.println("The status effect " + currentEnemy.getCurrentStatusEffect().getName() + " wore off on " +
                        currentEnemy.getName() + "....");
                currentEnemy.setCurrentStatusEffect(null);
            } else {
                System.out.println("Enemy is under the effects of " + currentEnemy.getCurrentStatusEffect().getName() + "!:");
                System.out.println(currentEnemy.getCurrentStatusEffect().getDescription());
                System.out.println("Enemy takes " + currentEnemy.getCurrentStatusEffect().getPower() + " damage!");
                currentEnemy.getCurrentStatusEffect().applyEffect(currentEnemy);
            }
        }
    }

    private void printCurrentBuffEffects() {
        if (player.getCurrentItemBuff() != null) {
            if (player.getCurrentItemBuff().turnsUsedSoFar == player.getCurrentItemBuff().getDuration() + 1) {
                System.out.println("Your " + player.getCurrentItemBuff().getName() + " has worn off....");
                player.setCurrentItemBuff(null);
            } else {
                System.out.println("You are under the effects of " + player.getCurrentItemBuff().getName() + "!");
                player.getCurrentItemBuff().use(player);
            }
        }
    }

    private void handleTurn(boolean isPlayerTurn) {
        AttackResult curResult = null;
        if (isPlayerTurn) {
            System.out.println("It's your turn now!");
            curResult = displayTurnMenu();
            handleResult(curResult, true);
        } else {
            System.out.println("It's the enemy's turn now!");
            curResult = currentEnemy.takeTurn();
            handleResult(curResult, false);
        }
    }

    private boolean checkIfBattleOver() {
        boolean isBattleOver = false;
        if (currentEnemy.getCurrentHP() <= 0 || player.getCurrentHP() <= 0) {
            if (currentEnemy.getCurrentHP() <= 0) {
                isBattleOver = true;
                player.setCurrentStatusEffect(null);
                System.out.println("You have defeated " + currentEnemy.getName() + "!");
                System.out.println("");
                System.out.println("You gained " + currentEnemy.getExpAwarded() + " experience!");
                boolean didLevel = player.addExp(currentEnemy.getExpAwarded());
                if (didLevel) {
                    System.out.println("You have leveled up!");
                }
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
            if (player.getCurrentHP() <= 0) {
                isBattleOver = true;
                System.out.println("You have died!");
                System.out.println("You have lost all your items!");
                System.out.println("Going back to the beginning of the dungeon.....");
                processPlayerLoss();
            }
        }
        return isBattleOver;
    }

    private void processPlayerLoss() {
        playerJustLost = true;
        player.emptyInventory();
        currentDungeon.setCurrentFloor(0);
    }

    private void printCurrentStatsOfBattle() {
        System.out.println("Enemy's HP: " + currentEnemy.getCurrentHP() + " | Enemy's Attack: " + currentEnemy.getAtkPower() + " | Enemy's Speed: " + currentEnemy.getSpeed());
        printPlayerStats();
    }

    private void printPlayerStats() {
        System.out.println("Your HP: " + player.getCurrentHP() + " | Your " + player.resource.getName() + " " +
                "amount: " + player.resource.getCurrentAmount() + " | Your Attack: " + player.getAtkPower() + " | Your Speed: " + player.getSpeed());
        System.out.println("Your Level: " + player.getLevel() + " | XP: " + player.getExpCurrent() + " / " + player.getExpRequiredToLevel());
        System.out.println("");
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
        if (result.getResourceSpent() > 0) {
            player.useResource(result.getResourceSpent());
        }
    }

    public AttackResult displayTurnMenu() {
        Scanner scanner = new Scanner(System.in);
        AttackResult result = null;

        boolean isValidInput = false;

        while (!isValidInput) {
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
                result = new AttackResult("", 0,0,  null);
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
