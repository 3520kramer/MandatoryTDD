package TikTakToe;

public class Player {

    private String name;
    private boolean isX;

    public Player() {

    }

    public Player(String name, boolean isX) {
        this.name = name;
        this.isX = isX;
    }

    public boolean isX() {
        return isX;
    }

    public void setX(boolean x) {
        isX = x;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

