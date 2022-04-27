package test.java;

import main.java.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WhiteBoxTests {

    Player player;
    GameManager gameManager;

    @Before
    public void setUp() {
        String playerName = "TestPlayer";
        String playerClass = "ROBOGOLEM";
        PlayerType playerType = PlayerType.TUNGSTENBOT;
        player = PlayerFactory.createPlayer(playerName, playerClass, playerType);
        gameManager = new GameManager(playerName, playerClass, playerType, false);
    }

    @Test
    public void testPlayerName() {
        assertEquals("TestPlayer", player.getName());
    }

}
