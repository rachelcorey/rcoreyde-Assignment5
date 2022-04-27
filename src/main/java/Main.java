package main.java;

import java.util.Scanner;

public class Main {

    private static GameManager gameManager;

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" ________                         ________                             ____                                                             ");
        System.out.println("/\\_____  \\                       /\\_____  \\                           /\\  _`\\                                                           ");
        System.out.println("\\/____//'/'     __     __   _____\\/____//'/'    ___   _ __   _____    \\ \\ \\/\\ \\  __  __    ___      __      __    ___     ___     ____  ");
        System.out.println("     //'/'    /'__`\\ /'__`\\/\\ '__`\\   //'/'    / __`\\/\\`'__\\/\\ '__`\\   \\ \\ \\ \\ \\/\\ \\/\\ \\ /' _ `\\  /'_ `\\  /'__`\\ / __`\\ /' _ `\\  /',__\\ ");
        System.out.println("    //'/'___ /\\  __//\\  __/\\ \\ \\L\\ \\ //'/'___ /\\ \\L\\ \\ \\ \\/ \\ \\ \\L\\ \\   \\ \\ \\_\\ \\ \\ \\_\\ \\/\\ \\/\\ \\/\\ \\L\\ \\/\\  __//\\ \\L\\ \\/\\ \\/\\ \\/\\__, `\\");
        System.out.println("    /\\_______\\ \\____\\ \\____\\\\ \\ ,__/ /\\_______\\ \\____/\\ \\_\\  \\ \\ ,__/    \\ \\____/\\ \\____/\\ \\_\\ \\_\\ \\____ \\ \\____\\ \\____/\\ \\_\\ \\_\\/\\____/");
        System.out.println("    \\/_______/\\/____/\\/____/ \\ \\ \\/  \\/_______/\\/___/  \\/_/   \\ \\ \\/      \\/___/  \\/___/  \\/_/\\/_/\\/___L\\ \\/____/\\/___/  \\/_/\\/_/\\/___/ ");
        System.out.println("                              \\ \\_\\                            \\ \\_\\                                /\\____/                             ");
        System.out.println("                               \\/_/                             \\/_/                                \\_/__/                              ");
        System.out.println("Welcome to ZeepZorp Dungeons!");
        System.out.println("Would you like to enter autoplay mode?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int choice = scanner.nextInt();
        if (choice == 1) {
            createGameManager("Klapaucius", "NANOBOTS", PlayerType.RADIOACTIVEBOT, false);
        } else {
            showDialogAndStartGame();
        }
    }

    private static void showDialogAndStartGame() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is your name?: ");
        String name = scanner.nextLine();
        System.out.println("Hello " + name + "! Zorped to meet you!");
        System.out.println("What kind of bot would you like to build today?");
        System.out.println("1. RoboGolem: A strong bot that can strike heavy physical blows to enemies.");
        System.out.println("2. RoboHacker: A clever bot that can hack other bots and render them helpless.");
        System.out.println("3. NanoBots: A group of bots that can add more to their ranks and attack as a group.");
        int classChoice = scanner.nextInt();
        System.out.println("That's a great choice!");
        System.out.println("What kind of modification would you like to add to your bot?");
        System.out.println("1. Hover Module: A module that increases speed by allowing the bot to hover above the ground.");
        System.out.println("2. Extra Capacitor Couplings: A module that increases the amount of Resource your bot has.");
        System.out.println("3. Tungsten Plating: A module that greatly increases the hit points of your bot.");
        System.out.println("4. Radioactive Core: A module that increases the damage your bot can do.");
        int moduleChoice = scanner.nextInt();
        System.out.println("That's a great choice!");
        System.out.println("We're ready to build your bot!");
        System.out.println("Just a moment......");
        Thread.sleep(1000);
        System.out.println("*loud clanking*");
        Thread.sleep(1000);
        System.out.println("*BEEP BLOOP BLEEEEEEEEEEEEEEEEEEE*");
        Thread.sleep(1000);
        System.out.println("Thanks for waiting....!");
        System.out.println("Your bot is ready to go!");
        System.out.println("We named them after you! <3");
        System.out.println("");
        Thread.sleep(1000);
        String classChoiceString = "";
        PlayerType moduleChoiceEnum = null;
        switch (classChoice) {
            case 1:
                classChoiceString = "ROBOGOLEM";
                break;
            case 2:
                classChoiceString = "ROBOHACKER";
                break;
            case 3:
                classChoiceString = "NANOBOTS";
                break;
            default:
                break;
        }
        switch (moduleChoice) {
            case 1:
                moduleChoiceEnum = PlayerType.HOVERBOT;
                break;
            case 2:
                moduleChoiceEnum = PlayerType.CAPACITORBOT;
                break;
            case 3:
                moduleChoiceEnum = PlayerType.TUNGSTENBOT;
                break;
            case 4:
                moduleChoiceEnum = PlayerType.RADIOACTIVEBOT;
                break;
            default:
                break;
        }
        createGameManager(name, classChoiceString, moduleChoiceEnum, true);
    }

    private static void createGameManager(String playerName, String playerClass, PlayerType playerType, boolean isHumanPlayerGame) {
        if (gameManager == null) {
            gameManager = new GameManager(playerName, playerClass, playerType, isHumanPlayerGame);
        }
    }
}
