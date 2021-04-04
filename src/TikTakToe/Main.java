package TikTakToe;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();

        game.promptUsersForPlayerName();

        game.assignPieces();

        boolean isFinished = false;
        int rounds = 0;
        int played = 1;

        while (!isFinished) {
            rounds++;
            game.placePieceOnBoard();

            isFinished = game.hasWon(rounds);

            if (rounds == 6 && !isFinished) {
                game.getBoard().clear();
                rounds = 0;
                played++;
                System.out.println("Resetting game");
            }
        }

        String winner;

        if (rounds == 5) { // X wins
            winner = game.getPlayer1().isX() ? game.getPlayer1().getName() : game.getPlayer2().getName();
        } else { // O wins
            winner = !game.getPlayer1().isX() ? game.getPlayer1().getName() : game.getPlayer2().getName();
        }

        System.out.println("The winner is: " + winner + " in " + played + " rounds");
    }
}

