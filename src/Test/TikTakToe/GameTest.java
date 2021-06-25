package TikTakToe;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Game game;

    @BeforeEach
    void beforeEach() {

        // arrange
        game = new Game();
    }

    @Test
    void assignPieces_playersGetPieces_playersPiecesAreNotEqual() throws InterruptedException {

        // act
        game.assignPieces();

        // assert
        assertNotEquals(game.getPlayer1().isX(), game.getPlayer2().isX());
    }


    @Test
    void placePieceOnBoard_newGameFirstMove_moreThanOnePiecesOnBoard() {

        // act
        game.placePieceOnBoard();

        // assert
        assertTrue(game.getBoard().size() > 0);
    }


    @Test
    void placePieceOnBoard_playingGame_noDuplicates() {

        // Run game 10 times to test for duplicates
        for (int i = 0; i < 10; i++) {

            // arrange
            game.getBoard().clear();

            for (int j = 0; j < 6; j++) {
                // act
                game.placePieceOnBoard();
                var newPiece = game.getBoard().get(j);

                for (int k = 0; k <= j - 1; k++) {
                    var alreadyPlacedPiece = game.getBoard().get(k);
                    // assert
                    assertFalse(newPiece.isCoordinatesEqual(alreadyPlacedPiece));
                }
            }
        }
    }


    @Test
    void hasWon_xHasWon_gameStopsAtRoundFive() {

        // arrange
        Piece piece1 = new Piece(true, 1, 1);
        Piece piece2 = new Piece(true, 1, 2);
        Piece piece3 = new Piece(true, 1, 3);
        Piece piece4 = new Piece(false, 3, 1);
        Piece piece5 = new Piece(false, 3, 2);

        game.getBoard().add(piece1);
        game.getBoard().add(piece4);
        game.getBoard().add(piece2);
        game.getBoard().add(piece5);
        game.getBoard().add(piece3);

        // act
        boolean hasWon = game.hasWon(5);

        // assert
        assertTrue(hasWon);

    }

    @Test
    void hasWon_oHasWon_gameStopsAtRoundSix() {

        // arrange
        Piece piece1 = new Piece(false, 1, 1);
        Piece piece2 = new Piece(false, 1, 2);
        Piece piece3 = new Piece(false, 1, 3);
        Piece piece4 = new Piece(true, 3, 1);
        Piece piece5 = new Piece(true, 3, 2);
        Piece piece6 = new Piece(true, 2, 2);

        game.getBoard().add(piece4);
        game.getBoard().add(piece1);
        game.getBoard().add(piece5);
        game.getBoard().add(piece2);
        game.getBoard().add(piece6);
        game.getBoard().add(piece3);

        // act and assert
        assertTrue(game.hasWon(6));
    }

    @Test
    void hasWon_draw_nobodyWinsAtRoundSix() {

        // arrange
        Piece piece1 = new Piece(true, 1, 1);
        Piece piece2 = new Piece(true, 3, 1);
        Piece piece3 = new Piece(true, 3, 2);
        Piece piece4 = new Piece(false, 2, 1);
        Piece piece5 = new Piece(false, 2, 2);
        Piece piece6 = new Piece(false, 1, 2);

        game.getBoard().add(piece1);
        game.getBoard().add(piece4);
        game.getBoard().add(piece2);
        game.getBoard().add(piece5);
        game.getBoard().add(piece3);
        game.getBoard().add(piece6);

        // act and assert
        assertFalse(game.hasWon(6));
    }

    @Test
    void winningConditions_winWithHorizontalOuterLine_true() {

        // arrange
        Piece piece1 = new Piece(false, 3, 3);
        Piece piece2 = new Piece(false, 1, 3);
        Piece piece3 = new Piece(false, 2, 3);

        game.getBoard().add(piece1);
        game.getBoard().add(piece2);
        game.getBoard().add(piece3);

        // act and assert
        assertTrue(game.winningConditions(6, 9, game.getBoard()));
    }

    @Test
    void winningConditions_winWithVerticalOuterLine_true() {

        // arrange
        Piece piece1 = new Piece(true, 1, 1);
        Piece piece2 = new Piece(true, 1, 2);
        Piece piece3 = new Piece(true, 1, 3);

        game.getBoard().add(piece1);
        game.getBoard().add(piece2);
        game.getBoard().add(piece3);

        // act and assert
        assertTrue(game.winningConditions(3, 6, game.getBoard()));
    }

    @Test
    void winningConditions_noWinningCombo_gameNotWon() {

        // arrange
        Piece piece1 = new Piece(true, 1, 1);
        Piece piece2 = new Piece(true, 1, 2);
        Piece piece3 = new Piece(true, 2, 3);

        game.getBoard().add(piece1);
        game.getBoard().add(piece2);
        game.getBoard().add(piece3);

        // act and assert
        assertFalse(game.winningConditions(4, 6, game.getBoard()));
    }

    @Test
    void checkCross_winWithVerticalCross_true() {

        // arrange
        Piece piece1 = new Piece(true, 1, 2);
        Piece piece2 = new Piece(true, 2, 2);
        Piece piece3 = new Piece(true, 3, 2);

        game.getBoard().add(piece1);
        game.getBoard().add(piece2);
        game.getBoard().add(piece3);

        // act and assert
        assertTrue(game.winningConditions(6, 6, game.getBoard()));
    }

    @Test
    void checkCross_winWithHorizontalCross_true() {

        // arrange
        Piece piece1 = new Piece(true, 2, 1);
        Piece piece2 = new Piece(true, 2, 2);
        Piece piece3 = new Piece(true, 2, 3);

        game.getBoard().add(piece1);
        game.getBoard().add(piece2);
        game.getBoard().add(piece3);

        // act and assert
        assertTrue(game.winningConditions(6, 6, game.getBoard()));
    }

    @Test
    void checkCross_noMatchingCombo_gameNotWon() {

        // arrange
        Piece piece1 = new Piece(true, 1, 1);
        Piece piece2 = new Piece(true, 2, 2);
        Piece piece3 = new Piece(true, 2, 3);

        game.getBoard().add(piece1);
        game.getBoard().add(piece2);
        game.getBoard().add(piece3);

        // act and assert
        assertFalse(game.checkCross(game.getBoard()));
    }

    @Test
    void checkDiagonals_checkDiagonalsBottomUp_true() {

        // arrange
        Piece piece1 = new Piece(true, 1, 1);
        Piece piece2 = new Piece(true, 2, 2);
        Piece piece3 = new Piece(true, 3, 3);

        game.getBoard().add(piece1);
        game.getBoard().add(piece2);
        game.getBoard().add(piece3);

        // act and assert
        assertTrue(game.winningConditions(6, 6, game.getBoard()));

    }

    @Test
    void checkDiagonals_checkDiagonalsUpToBottom_true() {

        // arrange
        Piece piece1 = new Piece(true, 1, 3);
        Piece piece2 = new Piece(true, 2, 2);
        Piece piece3 = new Piece(true, 3, 1);

        game.getBoard().add(piece1);
        game.getBoard().add(piece2);
        game.getBoard().add(piece3);

        // act and assert
        assertTrue(game.winningConditions(6, 6, game.getBoard()));
    }

    @Test
    void checkDiagonals_noMatchingCombo_gameNotWon() {

        // arrange
        Piece piece1 = new Piece(true, 2, 3);
        Piece piece2 = new Piece(true, 2, 2);
        Piece piece3 = new Piece(true, 3, 1);

        game.getBoard().add(piece1);
        game.getBoard().add(piece2);
        game.getBoard().add(piece3);

        // act and assert
        assertFalse(game.checkDiagonals(game.getBoard()));
    }


}
