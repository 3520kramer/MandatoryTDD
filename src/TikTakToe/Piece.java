package TikTakToe;

public class Piece {
    private boolean isX;
    private int x;
    private int y;

    public Piece(boolean isX, int x, int y) {
        this.isX = isX;
        this.x = x;
        this.y = y;
    }

    public boolean isCoordinatesEqual(Piece otherPiece){
        return this.x == otherPiece.x && this.y == otherPiece.y;
    }
    public boolean isCoordinatesEqual(int otherX, int otherY){
        return this.x == otherX && this.y == otherY;
    }

    public boolean isX() {
        return isX;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
