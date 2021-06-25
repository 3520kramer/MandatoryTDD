package TikTakToe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private Player player1;
    private Player player2;
    private Scanner scanner;
    private List<Piece> board;
    Random random;

    public Game() {
        this.player1 = new Player();
        this.player2 = new Player();
        this.scanner = new Scanner(System.in);
        this.board = new ArrayList();
        this.random = new Random();
    }

    public void promptUsersForPlayerName() {
        System.out.println("What is the name of player 1?");
        player1.setName(scanner.nextLine());

        System.out.println("What is the name of player 2?");
        player2.setName(scanner.nextLine());
    }

    public void assignPieces() throws InterruptedException {
        System.out.println("Who will start the game?");
        Thread.sleep(500);
        System.out.println("...");
        Thread.sleep(500);
        System.out.println("...");
        Thread.sleep(500);
        System.out.println("...");

        player1.setX(random.nextBoolean());
        player2.setX(!(player1.isX()));

        Player startingPlayer = player1.isX() ? player1 : player2;

        System.out.println(startingPlayer.getName() + " will start the game");
        Thread.sleep(1000);
    }

    public boolean hasWon(int round) {
        int sumX = 0;
        int sumY = 0;
        List<Piece> pieces = new ArrayList<>();

        for (Piece pieceOnBoard : board) {
            if (round == 5) {
                sumX += pieceOnBoard.isX() ? pieceOnBoard.getX() : 0;
                sumY += pieceOnBoard.isX() ? pieceOnBoard.getY() : 0;

                if (pieceOnBoard.isX()) {
                    pieces.add(pieceOnBoard);
                }
            } else if (round == 6) {
                sumX += !pieceOnBoard.isX() ? pieceOnBoard.getX() : 0;
                sumY += !pieceOnBoard.isX() ? pieceOnBoard.getY() : 0;

                if (!pieceOnBoard.isX()) {
                    pieces.add(pieceOnBoard);
                }
            } else {
                return false;
            }
        }
        return winningConditions(sumX, sumY, pieces);
    }

    public boolean checkCross(List<Piece> pieces) {
        int countX = 0;
        int countY = 0;

        // checking on cross
        for (Piece piece : pieces) {
            // x or y is 2,2,2
            if (piece.getX() == 2) {
                countX += 1;
            }
            if (piece.getY() == 2) {
                countY += 1;
            }
        }

        if (countX == 3 || countY == 3) {
            System.out.println("Cross");
            return true;
        } else return false;
    }

    public boolean checkDiagonals(List<Piece> pieces) {
        int pairs = 0;
        int matches = 0;

        // checking diagonals - bottom up
        for (Piece piece : pieces) {
            // needs to have 3 pairs
            if (piece.isCoordinatesEqual(1, 1) ||
                    piece.isCoordinatesEqual(2, 2) ||
                    piece.isCoordinatesEqual(3, 3)) {
                pairs += 1;
            }
        }

        if (pairs == 3) {
            System.out.println("Diagonals - Bottom up");
            return true;
        }

        // checking diagonals - up to bottom
        for (Piece piece : pieces) {
            if (piece.isCoordinatesEqual(3, 1) ||
                    piece.isCoordinatesEqual(1, 3) ||
                    piece.isCoordinatesEqual(2, 2)) {
                matches += 1;
            }
        }

        if (matches == 3) {
            System.out.println("Diagonals - Up to bottom");
            return true;
        }

        return false;
    }

    public boolean winningConditions(int sumX, int sumY, List<Piece> pieces) {

        if (sumX == 6 && sumY == 6) {
            if (checkCross(pieces)) {
                return true;

            } else if (checkDiagonals(pieces)) {
                return true;

            } else return false;

            // checking for horizontal outer line win
        } else if (sumX == 6 && (sumY == 3 || sumY == 9)) {
            System.out.println("Outerline");
            return true;

            // checking for vertical outer line win
        } else if (sumY == 6 && (sumX == 3 || sumX == 9)) {
            System.out.println("Outerline");
            return true;

        } else {
            return false;
        }
    }

    public int getRandomCoordinate() {
        return random.nextInt(3) + 1;
    }

    public void placePieceOnBoard() {

        boolean isX;
        Piece newPiece = null;
        int x = 0;
        int y = 0;

        if (board.isEmpty()) {
            x = getRandomCoordinate();
            y = getRandomCoordinate();
            newPiece = new Piece(true, x, y);
        } else {
            Piece piece = board.get(board.size() - 1);
            isX = !(piece.isX());

            boolean isCoordinateTaken = true;

            while (isCoordinateTaken) {
                x = getRandomCoordinate();
                y = getRandomCoordinate();
                newPiece = new Piece(isX, x, y);

                for (Piece pieceOnBoard : board) {
                    if (newPiece.isCoordinatesEqual(pieceOnBoard)) {
                        // System.out.println("On Board : x: " + pieceOnBoard.getX() + " y: " + pieceOnBoard.getY());
                        // System.out.println("New Piece: x: " + x + " y: " + y);
                        isCoordinateTaken = true;
                        break;
                    } else {
                        isCoordinateTaken = false;
                    }
                }
            }
        }
        board.add(newPiece);
        System.out.println((newPiece.isX() ? "X: " : "O: ") + x + ", " + y);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public List<Piece> getBoard() {
        return board;
    }
}
