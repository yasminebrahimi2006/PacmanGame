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
    private SoundManager soundManager;
    private Timer gameLoopTimer;
    private Timer ghostTimer;

    public Game() {
        this.maze = new Maze();
        this.pacman = new Pacman(18, 1);

        this.ghosts = new Ghost[] {
            new Ghost(1, 18),
            new Ghost(17, 1),
            new Ghost(14,18),
            new Ghost(9,10)
 
        };

        this.scoreManager = new ScoreManager();
        this.pellets = createPelletsFromMaze();
        this.gameOver = false;
        this.won = false;
        this.soundManager = new SoundManager();

        this.gamePanel = new GamePanel(
            maze,
            pacman,
            ghosts,
            pellets,
            scoreManager,
            this
        );

        this.frame = new JFrame("Pac-Man");
        this.frame.add(gamePanel);
        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);  // پنجره وسط صفحه باشه

        KeyHandler keyHandler = new KeyHandler(pacman);
        this.frame.addKeyListener(keyHandler);
        this.frame.setFocusable(true);

        this.frame.setVisible(true);

        soundManager.playSound("sounds/start.wav");




        startGameLoop();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isWon() {
        return won;
    }




    public void restart() {          ////////// for the PlayAgain button ////////////
        maze.reset();
        for (Pellet pellet : pellets) {
            pellet.resetCollected();
        }

        pacman.setPosition(18, 1);
        pacman.setDirection(Direction.NONE);
        ghosts[0].setPosition(9, 9);
        ghosts[1].setPosition(9, 10);
        ghosts[2].setPosition(10, 9);
        ghosts[3].setPosition(10, 10);

        scoreManager.resetCurrentScore();

        gameOver = false;
        won = false;

        soundManager.playSound("sounds/start.wav");
        
        gamePanel.repaint();
        
        javax.swing.SwingUtilities.invokeLater(() -> {
        frame.requestFocusInWindow();
         });

         startGameLoop();
    } //**********************************************************************///

 
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
    if (gameLoopTimer != null) {
        gameLoopTimer.stop();  
    }
     if (ghostTimer != null) {
            ghostTimer.stop();
        }
    
    gameLoopTimer = new Timer(200, e -> {
        if (gameOver) {
            return;
        }

        int oldRow = pacman.getX();
        int oldCol = pacman.getY();

        pacman.move(maze);

        boolean actuallyMoved =
            (pacman.getX() != oldRow ||
             pacman.getY() != oldCol);

        if (actuallyMoved) {
            scoreManager.subtractPoints(1);
            checkPelletCollision();
            checkWinCondition();
        }

        if (!gameOver) {
            checkGhostCollision();
        }

        gamePanel.repaint();
    });
        
        ghostTimer = new Timer(180, e -> {
            if (gameOver) {
                return;
            }

            
            moveGhosts();
            
        
            if (!gameOver) {
                checkGhostCollision();
            }
            
            gamePanel.repaint();
    });  
              gameLoopTimer.start();
        ghostTimer.start();
    }

    private void moveGhosts() {
        for (Ghost ghost : ghosts) {
            ghost.move(maze);
        }
    }

    private void checkPelletCollision() {
        for (Pellet pellet : pellets) {
            if (!pellet.isCollected()
                    && pellet.getX() == pacman.getX()
                    && pellet.getY() == pacman.getY()) {

                pellet.collect();

                maze.clearPelletMark(
                    pellet.getX(),
                    pellet.getY()
                );

                scoreManager.addPoints(10);
                soundManager.playSound("sounds/eat_pellet.wav");
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

        soundManager.playSound("sounds/win.wav");

        scoreManager.saveHighScore();
    }

    private void checkGhostCollision() {
        for (Ghost ghost : ghosts) {
            if (ghost.getX() == pacman.getX()
                    && ghost.getY() == pacman.getY()) {

                gameOver = true;
                won = false;

                soundManager.playSound("sounds/lose.wav");

                scoreManager.saveHighScore();
            }
        }
    }
}