import javax.swing.JFrame;
import javax.swing.Timer;

public class Game {

    private Pacman pacman;
    private Ghost[] ghosts;
    private Maze maze;
    private ScoreManager scoreManager;
    private GamePanel gamePanel;
    private JFrame frame;

    public Game() {
        this.maze = new Maze();
        this.pacman = new Pacman(1, 1);
        this.ghosts = new Ghost[2];
        this.scoreManager = new ScoreManager();

        this.gamePanel = new GamePanel(maze, pacman);

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

    private void startGameLoop() {
        Timer timer = new Timer(200, e -> {
            pacman.move(maze);
            gamePanel.repaint();
        });
        timer.start();
    }
}