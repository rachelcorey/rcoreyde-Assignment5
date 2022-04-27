package main.java;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The GameManager class is responsible for managing the game.
 * This satisfies the Singleton Design Pattern.
 */
public class GameManager {

    private static GameManager INSTANCE;
    static Player player;
    static String playerClass;
    static boolean isHumanPlayerGame;
    boolean isGameActive;
    boolean playerJustLost;
    boolean isBattleOver;
    int totalDungeons;
    int floorsPerDungeon;
    Enemy currentEnemy;
    Dungeon currentDungeon;
    ArrayList<Dungeon> worldMap;

    public GameManager(String playerName, String playerClass, PlayerType playerType, boolean isHumanPlayerGame) {
        INSTANCE = this;
        player = PlayerFactory.createPlayer(playerName, playerClass, playerType);
        GameManager.playerClass = playerClass;
        GameManager.isHumanPlayerGame = isHumanPlayerGame;
        totalDungeons = 5;
        floorsPerDungeon = 10;
        this.worldMap = generateWorldMap();
        currentDungeon = worldMap.get(0);
        isGameActive = true;
        player.setCurrentHP(2);
        printPlayerStats();
        conductHumanPlayerGame();
    }

    public static GameManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GameManager(player.getName(), playerClass, player.getType(), isHumanPlayerGame);
        }
        return INSTANCE;
    }

    private ArrayList<Dungeon> generateWorldMap() {
        ArrayList<Dungeon> worldMap = new ArrayList<>();
        int numOfFloors = floorsPerDungeon;
        for (int i = 0; i < totalDungeons; i++) {
            worldMap.add(new Dungeon(i, numOfFloors));
        }
        return worldMap;
    }

    public void conductHumanPlayerGame() {
        // Game loop
        while (isGameActive) {
            currentEnemy = currentDungeon.getCurrentFloor().getEnemy();
            System.out.println("You are in dungeon " + currentDungeon.getNumber() + " on Floor "
                    + "number " + currentDungeon.getCurrentFloor().getNumber() + "!");
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

                // This satisfies Requirement #2
                if (player.getSpeed() >= currentEnemy.getSpeed()) {
                    System.out.println("You go first!");
                    handleTurn(true);
                    if (isBattleOver || checkIfBattleOver()) {
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
                if ((currentDungeon.getCurrentFloor().getNumber() == floorsPerDungeon)
                        && (currentDungeon.getNumber() == totalDungeons)) {
                    isGameActive = false;
                    System.out.println("You have completed the game!");
                    break;
                }
                // This satisfies Requirement #1
                if (player.getCurrentHP() <= (player.getTotalHP() * 0.15)) {
                    processPlayerFifteenPercentHP();
                } else {
                    printFloorNavigation();
                }
            } else {
                playerJustLost = false;
            }
        }
        System.out.println("Final stats:");
        printPlayerStats();
        System.out.println("");
        System.out.println("Thanks for playing!");
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
                System.out.println("The status effect " + currentEnemy.getCurrentStatusEffect().getName() + " wore off on "
                        + currentEnemy.getName() + "....");
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
        isBattleOver = false;
        if (currentEnemy.getCurrentHP() <= 0 || player.getCurrentHP() <= 0) {
            if (currentEnemy.getCurrentHP() <= 0) {
                isBattleOver = true;
                player.setCurrentStatusEffect(null);
                player.resetSpells();
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
            // This satisfies Requirement #2
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

    // This satisfies Requirement #1
    private void processPlayerFifteenPercentHP() {
        System.out.println("");
        System.out.println("Your HP is less than or equal to 15% of your total HP!");
        System.out.println("You will be healed to full HP and returned to the beginning of the dungeon!");
        System.out.println("");
        playerJustLost = true;
        player.setCurrentHP(player.getTotalHP());
        player.setCasting(false);
        player.resetSpells();
        currentDungeon.setCurrentFloor(0);
        currentDungeon.getCurrentFloor().regenerateEnemy();
    }

    // This satisfies Requirement #2
    private void processPlayerLoss() {
        playerJustLost = true;
        player.emptyInventory();
        player.setCurrentHP(player.getTotalHP());
        player.setCasting(false);
        player.resetSpells();
        currentDungeon.setCurrentFloor(0);
    }

    private void printCurrentStatsOfBattle() {
        System.out.println("Enemy's HP: " + currentEnemy.getCurrentHP() + " | Enemy's Attack: " + currentEnemy.getAtkPower() + " | Enemy's Speed: " + currentEnemy.getSpeed());
        printPlayerStats();
    }

    private void printPlayerStats() {
        System.out.println("~*~*~*~*~*~*~ " + player.getName() + ", the " + playerClass + " " + player.getType() + " ~*~*~*~*~*~*~");
        if (player instanceof NanoBots) {
            System.out.println("Current number of NanoBots: " + ((NanoBots) player).getNumOfBots());
        }
        System.out.println("Your HP: " + player.getCurrentHP() + " / " + player.getTotalHP() + " | Your " + player.resource.getName() + " "
                + "amount: " + player.resource.getCurrentAmount() + " | Your Attack: " + player.getAtkPower() + " | Your Speed: " + player.getSpeed());
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

    private int generateRandomChoice(int max) {
        Random rand = new Random();
        return rand.nextInt(max + 1);
    }

    private int generateChoice(int validOptions) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        int choice = 0;
        if (isHumanPlayerGame) {
            choice = scanner.nextInt();
        } else {
            choice = generateRandomChoice(validOptions);
        }
        return choice;
    }

    public AttackResult displayTurnMenu() {
        int choice = 0;
        AttackResult result = null;
        boolean isValidInput = false;

        if (!player.isCasting()) {
            while (!isValidInput) {
                int validOptions = 1;
                // This satisfies Requirement #2
                System.out.println("What would you like to do?");
                System.out.println("1. Attack");
                if (player.getInventory().size() > 0) {
                    System.out.println("2. Use Item");
                    validOptions = 2;
                }
                choice = generateChoice(validOptions);
                if (choice == 1) {
                    System.out.println("What attack would you like to use?");
                    System.out.println("1. Basic Attack");
                    System.out.println("2. Special Attack");
                    validOptions = 2;
                    choice = generateChoice(validOptions);
                    if (choice == 1) {
                        result = player.physicalAttack();
                        isValidInput = true;
                    } else {
                        System.out.println("Which special attack would you like to use?");
                        System.out.println("1. " + player.specialSkill[0].getName() + ": " + player.specialSkill[0].getDescription() + " | Cost: " + player.specialSkill[0].getCost());
                        System.out.println("2. " + player.specialSkill[1].getName() + ": " + player.specialSkill[1].getDescription() + " | Cost: " + player.specialSkill[1].getCost());
                        choice = generateChoice(validOptions);
                        if (player instanceof NanoBots && ((NanoBots) player).getNumOfBots() >= 50) {
                            System.out.println("You have too many bots to use this skill!");
                        } else {
                            if (choice == 1) {
                                if (player.specialSkill[0].getCost() <= player.resource.getCurrentAmount()) {
                                    result = player.specialAttack(0);
                                    isValidInput = true;
                                } else {
                                    System.out.println("You do not have enough resource to use this special attack!");
                                }
                            } else {
                                if (player.specialSkill[1].getCost() <= player.resource.getCurrentAmount()) {
                                    result = player.specialAttack(1);
                                    isValidInput = true;
                                } else {
                                    System.out.println("You do not have enough resource to use this special attack!");
                                }
                            }
                        }
                    }
                } else if (choice == 2 && player.getInventory().size() > 0) {
                    System.out.println("Which item would you like to use?");
                    int count = 1;
                    for (Item item : player.getInventory()) {
                        System.out.println(count + ". " + item.getName());
                        count++;
                    }
                    validOptions = count - 1;
                    choice = generateChoice(validOptions);
                    if (choice <= 0 || choice >= player.getInventory().size()) {
                        choice = 1;
                    }
                    player.useItem(player.getInventory().get(choice - 1));
                    player.getInventory().remove(player.getInventory().get(choice - 1));
                    result = new AttackResult("", 0,0,  null);
                    isValidInput = true;
                } else {
                    System.out.println("Invalid choice!");
                }
            }
        } else {
            System.out.println("You are currently casting a spell!");
            result = player.specialAttack(1);
        }
        return result;
    }

    public Player getPlayer() {
        return player;
    }

    public Dungeon getCurrentDungeon() {
        return currentDungeon;
    }

    public int getFloorsPerDungeon() {
        return floorsPerDungeon;
    }

    public void setBattleOver(boolean battleOver) {
        isBattleOver = battleOver;
    }

    public boolean isGameActive() {
        return isGameActive;
    }
}
