import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ghost extends MovableEntity {

    private boolean isWeak;
    private Random random;

    public Ghost(int startX, int startY) {
        super(startX, startY);
        this.isWeak = false;
        this.random = new Random();
    }

    public boolean isWeak() {
        return isWeak;
    }

    @Override
    public void move(Maze maze) {
        List<Direction> possibleDirections = getPossibleDirections(maze);

        if (possibleDirections.isEmpty()) {
            return;
        }

        int randomIndex = random.nextInt(possibleDirections.size());
        Direction chosenDirection = possibleDirections.get(randomIndex);

        setDirection(chosenDirection);
        applyMove(chosenDirection);
    }

    private List<Direction> getPossibleDirections(Maze maze) {
        List<Direction> options = new ArrayList<>();
        int row = getX();
        int col = getY();

        if (!maze.isWall(row - 1, col)) {
            options.add(Direction.UP);
        }
        if (!maze.isWall(row + 1, col)) {
            options.add(Direction.DOWN);
        }
        if (!maze.isWall(row, col - 1)) {
            options.add(Direction.LEFT);
        }
        if (!maze.isWall(row, col + 1)) {
            options.add(Direction.RIGHT);
        }

        return options;
    }

    private void applyMove(Direction direction) {
        int row = getX();
        int col = getY();

        switch (direction) {
            case UP:
                setPosition(row - 1, col);
                break;
            case DOWN:
                setPosition(row + 1, col);
                break;
            case LEFT:
                setPosition(row, col - 1);
                break;
            case RIGHT:
                setPosition(row, col + 1);
                break;
            case NONE:
                break;
        }
    }
}