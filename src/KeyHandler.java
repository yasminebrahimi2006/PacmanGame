import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private Pacman pacman;

    public KeyHandler(Pacman pacman) {
        this.pacman = pacman;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            pacman.setDirection(Direction.UP);
        } else if (code == KeyEvent.VK_DOWN) {
            pacman.setDirection(Direction.DOWN);
        } else if (code == KeyEvent.VK_LEFT) {
            pacman.setDirection(Direction.LEFT);
        } else if (code == KeyEvent.VK_RIGHT) {
            pacman.setDirection(Direction.RIGHT);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}