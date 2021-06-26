package TikTakToe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void getName_newPlayerHans_NameIsHans() {
        // arrange
        String name = "Hans";
        Player player = new Player(name, false);

        // act
        String actualName = player.getName();

        // assert
        assertEquals(name, actualName);
    }

    @Test
    void setName_newPlayerWithoutName_NameIsSet() {
        // arrange
        Player player = new Player();
        String name = "Hans";

        // act
        player.setName(name);

        // assert
        assertEquals(name, player.getName());
    }

    @Test
    void isX_newPlayerIsX_true() {
        // arrange
        Player player = new Player("Hans", true);

        // act and assert
        assertTrue(player.isPlayerX());
    }

    @Test
    void isX_newPlayerIsX_false() {
        // arrange
        Player player = new Player("Hans", false);

        // act and assert
        assertFalse(player.isPlayerX());
    }

    @Test
    void setX_setXToTrue_true() {
        // arrange
        Player player = new Player("Hans", false);

        // act
        player.setIsPlayerX(true);

        // assert
        assertTrue(player.isPlayerX());
    }

    @Test
    void setX_setXToFalse_false() {
        // arrange
        Player player = new Player("Hans", false);

        // act
        player.setIsPlayerX(false);

        // assert
        assertFalse(player.isPlayerX());
    }
}
