public class Pellet {
    private int x;
    private int y;
    private boolean collected;

    public Pellet(int x, int y) {
        this.x = x;
        this.y = y;
        this.collected = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isCollected() {
        return collected;
    }

    public void collect() {
        this.collected = true;
    }

    public void resetCollected() {  //for the PlayAgain button
        this.collected = false;
    }
}