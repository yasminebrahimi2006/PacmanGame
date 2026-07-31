import javax.swing.JFrame;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private Pacman pacman;
    private Ghost[] ghosts;
    private Maze maze;
    private ScoreManager scoreManager;
    private GamePanel gamePanel;
    private JFrame frame;
    private List<Pellet> pellets;

    public Game() {
        this.maze = new Maze();
        this.pacman = new Pacman(1, 1);
        this.ghosts = new Ghost[2];
        this.scoreManager = new ScoreManager();
        this.pellets = createPelletsFromMaze();

        this.gamePanel = new GamePanel(maze, pacman, pellets, scoreManager);

        this.frame = new JFrame("Pac-Man");
        this.frame.add(gamePanel);
        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);

        KeyHandler keyHandler = new KeyHandler(pacman);
        this.frame.addKeyListener(keyHandler);
        this.frame.setFocusable(true);

        this.frame.setVisible(true);

        startGameLoop();
    }

    private List<Pellet> createPelletsFromMaze() {
        List<Pellet> result = new ArrayList<>();
        for (int row = 0; row < maze.getRows(); row++) {
            for (int col = 0; col < maze.getCols(); col++) {
                if (maze.hasPelletMark(row, col)) {
                    result.add(new Pellet(row, col));
                }
            }
        }
        return result;
    }

    private void startGameLoop() {
        Timer timer = new Timer(200, e -> {
            Direction beforeMove = pacman.getDirection();
            int oldRow = pacman.getX();
            int oldCol = pacman.getY();

            pacman.move(maze);

            boolean actuallyMoved = (pacman.getX() != oldRow || pacman.getY() != oldCol);
            if (actuallyMoved) {
                scoreManager.subtractPoints(1);
                checkPelletCollision();
            }

            gamePanel.repaint();
        });
        timer.start();
    }

    private void checkPelletCollision() {
        for (Pellet pellet : pellets) {
            if (!pellet.isCollected() && pellet.getX() == pacman.getX() && pellet.getY() == pacman.getY()) {
                pellet.collect();
                maze.clearPelletMark(pellet.getX(), pellet.getY());
                scoreManager.addPoints(10);
            }
        }
    }
}