public class Ghost extends MovableEntity {

    private boolean isWeak; // برای Power Pellet در آینده (بونوس)

    public Ghost(int startX, int startY) {
        super(startX, startY);
        this.isWeak = false;
    }

    public boolean isWeak() {
        return isWeak;
    }

    @Override
    public void move(Maze maze) {
        // منطق حرکت خودکار رو بعدا پیاده می‌کنیم
    }
}