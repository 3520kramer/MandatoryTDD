package TikTakToe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void getName() {
        String name = "Hans";
        Player player = new Player(name, false);
        assertEquals(name, player.getName());
    }

    @Test
    void isX() {
        Player player = new Player("Hans", true);
        assertTrue(player.isX());

        Player player2 = new Player("Hans", false);
        assertFalse(player2.isX());
    }

    @Test
    void setX() {
        Player player = new Player("Hans", false);

        player.setX(true);
        assertTrue(player.isX());

        player.setX(false);
        assertFalse(player.isX());
    }

    @Test
    void setName() {
        Player player = new Player();

        String name = "Hans";
        player.setName(name);

        assertEquals(name, player.getName());
    }
}
