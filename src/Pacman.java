public class Pacman extends MovableEntity {

    private int score;

    public Pacman(int startX, int startY) {
        super(startX, startY);
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int amount) {
        this.score += amount;
    }

    @Override
    public void move(Maze maze) {
        int currentRow = getX();
        int currentCol = getY();
        int newRow = currentRow;
        int newCol = currentCol;

        switch (getDirection()) {
            case UP:
                newRow = currentRow - 1;
                break;
            case DOWN:
                newRow = currentRow + 1;
                break;
            case LEFT:
                newCol = currentCol - 1;
                break;
            case RIGHT:
                newCol = currentCol + 1;
                break;
            case NONE:
                break;
        }

        if (!maze.isWall(newRow, newCol)) {
            setPosition(newRow, newCol);
        }
    }
}