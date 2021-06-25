package TikTakToe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    @Test
    void isCoordinatesEqual_creatingTwoPieces_true() {
        // arrange
        Piece piece1 = new Piece(false, 1,1);
        Piece piece2 = new Piece(false, 1,1);

        // act and assert
        assertTrue(piece1.isCoordinatesEqual(piece2));
    }

    @Test
    void isCoordinatesEqual_creatingTwoPieces_false() {
        // arrange
        Piece piece1 = new Piece(false, 1,1);
        Piece piece2 = new Piece(false, 1,2);

        // act and assert
        assertFalse(piece1.isCoordinatesEqual(piece2));
    }

}