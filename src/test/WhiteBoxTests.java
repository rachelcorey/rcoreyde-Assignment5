import main.java.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WhiteBoxTests {

    Player player;

    @Before
    public void setUp() {
        player = new Player(420);
    }

    @Test
    public void testPlayerConstructor() {
        assertEquals(420, player.getHP());
    }
}
