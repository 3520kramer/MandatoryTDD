package TikTakToe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    @Test
    void isCoordinatesEqual() {
        Piece piece1 = new Piece(false, 1,1);
        Piece piece2 = new Piece(false, 1,1);

        assertTrue(piece1.isCoordinatesEqual(piece2));

        Piece piece3 = new Piece(false, 1,2);

        assertFalse(piece1.isCoordinatesEqual(piece3));
    }

}