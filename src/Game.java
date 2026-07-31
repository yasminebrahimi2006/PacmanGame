import javax.swing.JFrame;

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

        this.gamePanel = new GamePanel(maze);

        this.frame = new JFrame("Pac-Man");
        this.frame.add(gamePanel);
        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }
}