public class Pacman extends MovableEntity {

    private int score;

    public Pacman(int startX, int startY) {
        super(startX, startY);
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void move(Maze maze) {
        // منطق حرکت رو مرحله بعد پیاده می‌کنیم
    }
}
