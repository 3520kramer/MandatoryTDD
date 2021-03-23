package TikTakToe;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test // hard to test because of input from scanner
    void promptUsersForPlayerNames() {
        Game game = new Game();
        //game.promptUsersForPlayerName();
        // https://stackoverflow.com/questions/46268486/sending-scanner-inputs-to-a-unit-test
    }

    @Test
    void assignPieces() throws InterruptedException {
        Game game = new Game();
        game.assignPieces();
        assertNotEquals(game.getPlayer1().isX(), game.getPlayer2().isX());
    }

    @Test
    void placePieceOnBoard() throws InterruptedException {
        Game game = new Game();

        // Testing one piece placed
        game.placePieceOnBoard();

        assertTrue(game.getBoard().size() > 0);

        assertTrue(game.getBoard().get(0).getX() >= 1 && game.getBoard().get(0).getX() <= 3);
        assertTrue(game.getBoard().get(0).getY() >= 1 && game.getBoard().get(0).getY() <= 3);

        // Test to make sure we don't allow duplicates
        for(int i = 0; i < 10; i++){
            game.getBoard().clear();

            for(int j = 0; j < 6; j++){
                game.placePieceOnBoard();
                var newPiece = game.getBoard().get(j);

                for(int k = 0; k <= j-1; k++){
                    var alreadyPlacedPiece = game.getBoard().get(k);
                    assertFalse(newPiece.isCoordinatesEqual(alreadyPlacedPiece));
                }
            }
        }
    }

    @Test
    void getRandomCoordinate() {
        Game game = new Game();
        int coordinate;

        HashMap<Integer, Integer> dictionary = new HashMap<>();
        dictionary.put(1, 0);
        dictionary.put(2, 0);
        dictionary.put(3, 0);

        for(int i = 0; i <= 100; i++){
            coordinate = game.getRandomCoordinate();
            dictionary.put(coordinate, dictionary.get(coordinate) + 1);
            assertTrue(coordinate >= 1 && coordinate <= 3);
        }

        for(Map.Entry<Integer, Integer> entry: dictionary.entrySet()) {
            assertTrue(entry.getValue() > 10);
        }
        System.out.println(dictionary);
    }

    @Test
    void hasWon() {
        Game game = new Game();

        // Piece X wins at round 5
        Piece piece1 = new Piece(true, 1,1);
        Piece piece2 = new Piece(true, 1,2);
        Piece piece3 = new Piece(true, 1,3);

        Piece piece4 = new Piece(false, 3,1);
        Piece piece5 = new Piece(false, 3,2);

        game.getBoard().add(piece1);
        game.getBoard().add(piece4);
        game.getBoard().add(piece2);
        game.getBoard().add(piece5);
        game.getBoard().add(piece3);

        assertTrue(game.hasWon(5));

        game.getBoard().clear();

        // Piece O wins at round 6
        piece1 = new Piece(false, 1,1);
        piece2 = new Piece(false, 1,2);
        piece3 = new Piece(false, 1,3);

        piece4 = new Piece(true, 3,1);
        piece5 = new Piece(true, 3,2);
        Piece piece6 = new Piece(true, 2,2);

        game.getBoard().add(piece4);
        game.getBoard().add(piece1);
        game.getBoard().add(piece5);
        game.getBoard().add(piece2);
        game.getBoard().add(piece6);
        game.getBoard().add(piece3);

        assertTrue(game.hasWon(6));

        game.getBoard().clear();

        // Draw
        piece1 = new Piece(true, 1,1);
        piece2 = new Piece(true, 3,1);
        piece3 = new Piece(true, 3,2);

        piece4 = new Piece(false, 2,1);
        piece5 = new Piece(false, 2,2);
        piece6 = new Piece(false, 1,2);

        game.getBoard().add(piece1);
        game.getBoard().add(piece4);
        game.getBoard().add(piece2);
        game.getBoard().add(piece5);
        game.getBoard().add(piece3);
        game.getBoard().add(piece6);

        assertFalse(game.hasWon(6));
    }

    @Test
    void winningConditions() {
        Game game = new Game();
        List<Piece> pieces = new ArrayList<>();

        Piece piece1;
        Piece piece2;
        Piece piece3;
        Piece piece4;
        Piece piece5;
        Piece piece6;

        // checking rest
        // else if (sumX == 6 && (sumY == 3 || sumY == 9))
        piece1 = new Piece(true, 1,1);
        piece2 = new Piece(true, 2,1);
        piece3 = new Piece(true, 1,2);

        piece4 = new Piece(false, 3,3);
        piece5 = new Piece(false, 1,3);
        piece6 = new Piece(false, 2,3);

        game.getBoard().add(piece1);
        game.getBoard().add(piece4);
        game.getBoard().add(piece2);
        game.getBoard().add(piece5);
        game.getBoard().add(piece3);
        game.getBoard().add(piece6);

        pieces.add(piece1);
        pieces.add(piece4);
        pieces.add(piece2);
        pieces.add(piece5);
        pieces.add(piece3);
        pieces.add(piece6);

        assertTrue(game.winningConditions(6, 9, pieces));

        game.getBoard().clear();
        pieces.clear();

        // else if (sumY == 6 && (sumX == 3 || sumX == 9))
        piece1 = new Piece(true, 1,1);
        piece2 = new Piece(true, 1,2);
        piece3 = new Piece(true, 1,3);

        piece4 = new Piece(false, 3,1);
        piece5 = new Piece(false, 3,2);

        game.getBoard().add(piece1);
        game.getBoard().add(piece4);
        game.getBoard().add(piece2);
        game.getBoard().add(piece5);
        game.getBoard().add(piece3);

        pieces.add(piece1);
        pieces.add(piece4);
        pieces.add(piece2);
        pieces.add(piece5);
        pieces.add(piece3);

        assertTrue(game.winningConditions(3, 6, pieces));

        game.getBoard().clear();
        pieces.clear();

    }

    @Test
    void checkCross() {
        Game game = new Game();
        List<Piece> pieces = new ArrayList<>();

        // testing our check on cross
        Piece piece1 = new Piece(true, 1,2);
        Piece piece2 = new Piece(true, 2,2);
        Piece piece3 = new Piece(true, 3,2);

        Piece piece4 = new Piece(false, 3,3);
        Piece piece5 = new Piece(false, 1,3);

        game.getBoard().add(piece1);
        game.getBoard().add(piece4);
        game.getBoard().add(piece2);
        game.getBoard().add(piece5);
        game.getBoard().add(piece3);

        pieces.add(piece1);
        pieces.add(piece4);
        pieces.add(piece2);
        pieces.add(piece5);
        pieces.add(piece3);

        assertTrue(game.winningConditions(6, 6, pieces));
    }

    @Test
    void checkDiagonals() {
        Game game = new Game();
        List<Piece> pieces = new ArrayList<>();

        // checking diagonals - bottom up
        Piece piece1 = new Piece(true, 1,1);
        Piece piece2 = new Piece(true, 2,2);
        Piece piece3 = new Piece(true, 3,3);

        Piece piece4 = new Piece(false, 3,2);
        Piece piece5 = new Piece(false, 1,3);

        game.getBoard().add(piece1);
        game.getBoard().add(piece4);
        game.getBoard().add(piece2);
        game.getBoard().add(piece5);
        game.getBoard().add(piece3);

        pieces.add(piece1);
        pieces.add(piece4);
        pieces.add(piece2);
        pieces.add(piece5);
        pieces.add(piece3);

        assertTrue(game.winningConditions(6, 6, pieces));

        game.getBoard().clear();
        pieces.clear();

        // checking diagonals - up to bottom
        piece1 = new Piece(true, 1,3);
        piece2 = new Piece(true, 2,2);
        piece3 = new Piece(true, 3,1);

        piece4 = new Piece(false, 3,2);
        piece5 = new Piece(false, 2,3);

        game.getBoard().add(piece1);
        game.getBoard().add(piece4);
        game.getBoard().add(piece2);
        game.getBoard().add(piece5);
        game.getBoard().add(piece3);

        pieces.add(piece1);
        pieces.add(piece4);
        pieces.add(piece2);
        pieces.add(piece5);
        pieces.add(piece3);

        assertTrue(game.winningConditions(6, 6, pieces));
    }
}