package main.java;

/**
 * This class is responsible for creating players.
 * It satisfies the Factory Method Design Pattern.
 */
public class PlayerFactory {

    // This satisfies Requirements #3 and #4
    public static Player createPlayer(String name, String playerClass, PlayerType playerType) {
        Player player = null;
        switch (playerClass) {
            case "ROBOGOLEM":
                player = new RoboGolem(name, playerType);
                break;
            case "ROBOHACKER":
                player = new RoboHacker(name, playerType);
                break;
            case "NANOBOTS":
                player = new NanoBots(name, playerType);
                break;
            default:
                break;
        }

        return player;
    }
}
