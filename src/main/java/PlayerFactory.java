package main.java;

public class PlayerFactory {

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
                break;
        }

        return player;
    }
}
