public abstract class MovableEntity {
    private int x;
    private int y;
    private Direction direction;

    public MovableEntity(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.direction = Direction.NONE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    protected void setPosition(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public abstract void move(Maze maze);
}
