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
    private boolean gameOver;
    private boolean won;

    public Game() {
        this.maze = new Maze();
        this.pacman = new Pacman(1, 1);
        this.ghosts = new Ghost[] {
            new Ghost(1, 8),
            new Ghost(8, 1)
        };
        this.scoreManager = new ScoreManager();
        this.pellets = createPelletsFromMaze();
        this.gameOver = false;
        this.won = false;

        this.gamePanel = new GamePanel(maze, pacman, ghosts, pellets, scoreManager, this);

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

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isWon() {
        return won;
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
            if (gameOver) {
                return;
            }

            int oldRow = pacman.getX();
            int oldCol = pacman.getY();

            pacman.move(maze);

            boolean actuallyMoved = (pacman.getX() != oldRow || pacman.getY() != oldCol);
            if (actuallyMoved) {
                scoreManager.subtractPoints(1);
                checkPelletCollision();
                checkWinCondition();
            }

            moveGhosts();
            checkGhostCollision();

            gamePanel.repaint();
        });
        timer.start();
    }

    private void moveGhosts() {
        for (Ghost ghost : ghosts) {
            ghost.move(maze);
        }
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

    private void checkWinCondition() {
        for (Pellet pellet : pellets) {
            if (!pellet.isCollected()) {
                return;
            }
        }
        scoreManager.addPoints(500);
        won = true;
        gameOver = true;
    }

    private void checkGhostCollision() {
        for (Ghost ghost : ghosts) {
            if (ghost.getX() == pacman.getX() && ghost.getY() == pacman.getY()) {
                gameOver = true;
            }
        }
    }
}