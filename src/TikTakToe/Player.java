package TikTakToe;

public class Player {

    private String name;
    private boolean isPlayerX;

    public Player() {

    }

    public Player(String name, boolean isPlayerX) {
        this.name = name;
        this.isPlayerX = isPlayerX;
    }

    public boolean isPlayerX() {
        return isPlayerX;
    }

    public void setIsPlayerX(boolean isPlayerX) {
        this.isPlayerX = isPlayerX;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

