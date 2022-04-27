package main.java;

import java.util.Random;

public class EnemyNameGenerator {

    public static String generateRandomName(boolean isBoss) {
        String name = getRandomPrefix() + getRandomSuffix();
        if (!isBoss) {
            return name;
        }
        return name + getRandomBossTitle();
    }

    private static String getRandomPrefix() {
        Random rand = new Random();
        String[] prefixes = {"Automatic", "Crackling", "Bionic", "Flaming", "Frozen", "Giant", "Gigantic", "Grim",
                "Huge", "Hulking", "Icy", "Infernal", "Insane", "Lethal", "Luminous", "Mysterious", "Mystical",
                "Nuclear", "Ominous", "Piercing", "Electric", "Ionic", "Maniacal", "Mechanical", "Mechanized"};
        return prefixes[rand.nextInt(prefixes.length)];
    }

    private static String getRandomSuffix() {
        Random rand = new Random();
        String[] suffixes = {"Boy", "Girl", "Bot", "Rover", "Assembler", "Pygmalion", "Automaton", "Predator",
                "Warrior", "Blooper", "Beeper", "System"};
        return suffixes[rand.nextInt(suffixes.length)];
    }

    private static String getRandomBossTitle() {
        Random rand = new Random();
        String[] bossTitles = {" The Crusher", ", MegaBot", " The Circuit-Breaker", " The Destroyer", ", Giga-Chad", ", Maniacal Mainframe",
                ", Supreme Techno-Giant", ", Fearsome Dragon-Bot", " The Cyber-Ruin", ", Unrivalled Neuro-Network",
                " The De-Compiler"};
        return bossTitles[rand.nextInt(bossTitles.length)];
    }
}
