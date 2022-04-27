package test.java;

import main.java.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WhiteBoxTests {

    @Before
    public void setUp() {
        //
    }

//    @Test
//    public void testRoboGolemGame() {
//        String playerName = "TestPlayer";
//        String playerClass = "ROBOGOLEM";
//        PlayerType playerType = PlayerType.TUNGSTENBOT;
//        GameManager gameManager = new GameManager(playerName, playerClass, playerType, false);
//        assertFalse(gameManager.isGameActive());
//    }

//    @Test
//    public void testRoboHackerGame() {
//        String playerName = "TestPlayer";
//        String playerClass = "ROBOHACKER";
//        PlayerType playerType = PlayerType.RADIOACTIVEBOT;
//        GameManager gameManager = new GameManager(playerName, playerClass, playerType, false);
//        assertFalse(gameManager.isGameActive());
//    }

    @Test
    public void testNanobotsGame() {
        String playerName = "TestPlayer";
        String playerClass = "NANOBOTS";
        PlayerType playerType = PlayerType.HOVERBOT;
        GameManager gameManager = new GameManager(playerName, playerClass, playerType, false);
        assertFalse(gameManager.isGameActive());
    }
}
