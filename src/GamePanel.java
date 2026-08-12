import javax.swing.JButton;  // for button
import javax.swing.ImageIcon; // for images
import java.awt.Image;  // for images
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

public class GamePanel extends JPanel {

    private static final int TILE_SIZE = 32;
    private static final int SCORE_BAR_HEIGHT = 30;

    private Maze maze;
    private Pacman pacman;
    private Ghost[] ghosts;
    private List<Pellet> pellets;
    private ScoreManager scoreManager;
    private Game game;  //چون به متغیر gameOver kنیاز داریم، باید یک مرجع به کلاس Game داشته باشیم
    private Image pacmanImage;
    private Image[] ghostImages;
    private JButton playAgainButton; 


    public GamePanel(Maze maze, Pacman pacman, Ghost[] ghosts, List<Pellet> pellets, ScoreManager scoreManager, Game game) {
        this.maze = maze;
        this.pacman = pacman;
        this.ghosts = ghosts;
        this.pellets = pellets;
        this.scoreManager = scoreManager;
        this.game = game;



    //////// uploading the images
pacmanImage = new ImageIcon("images/pacman.png").getImage();

ghostImages = new Image[4];
ghostImages[0] = new ImageIcon("images/ghost1.png").getImage();
ghostImages[1] = new ImageIcon("images/ghost2.png").getImage();
ghostImages[2] = new ImageIcon("images/ghost3.png").getImage();
ghostImages[3] = new ImageIcon("images/ghost4.png").getImage();

        int width = maze.getCols() * TILE_SIZE;
        int height = maze.getRows() * TILE_SIZE + SCORE_BAR_HEIGHT;
        this.setPreferredSize(new java.awt.Dimension(width, height));
        this.setBackground(Color.BLACK);
    
   
    ////////////////////button
    this.setLayout(null); // Absolute positioning 

    playAgainButton = new JButton("Play Again");
    int buttonWidth = 140;
        int buttonHeight = 40;
        int buttonX = width / 2 - buttonWidth / 2;
        int buttonY = height / 2 + 40;
        playAgainButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
         playAgainButton.setVisible(false);
        playAgainButton.addActionListener(e -> {
            game.restart();
            requestFocusInWindow();
        });
        this.add(playAgainButton); 
    }
    
    ///////////////////////
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMaze(g);
        drawPellets(g);
        drawGhosts(g);
        drawPacman(g);
        drawScore(g);

        if (game.isGameOver()) {
            drawGameOverMessage(g);
            playAgainButton.setVisible(true);
        } else {
            playAgainButton.setVisible(false);
        }
    }

    private void drawMaze(Graphics g) {
        for (int row = 0; row < maze.getRows(); row++) {
            for (int col = 0; col < maze.getCols(); col++) {
                int x = col * TILE_SIZE;
                int y = row * TILE_SIZE + SCORE_BAR_HEIGHT;

                if (maze.isWall(row, col)) {
                    g.setColor(Color.BLUE);
                    g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
                }
            }
        }
    }

    private void drawPellets(Graphics g) {
        g.setColor(Color.YELLOW);
        int dotSize = 6;
        for (Pellet pellet : pellets) {
            if (!pellet.isCollected()) {
                int x = pellet.getY() * TILE_SIZE;
                int y = pellet.getX() * TILE_SIZE + SCORE_BAR_HEIGHT;
                g.fillOval(x + TILE_SIZE/2 - dotSize/2, y + TILE_SIZE/2 - dotSize/2, dotSize, dotSize);
            }
        }
    }

   private void drawGhosts(Graphics g) {  //this uses images//
    for (int i = 0; i < ghosts.length; i++) {
        Ghost ghost = ghosts[i];

        int x = ghost.getY() * TILE_SIZE;
        int y = ghost.getX() * TILE_SIZE + SCORE_BAR_HEIGHT;

        Image image = ghostImages[i % ghostImages.length];

        g.drawImage(
            image,
            x,
            y,
            TILE_SIZE,
            TILE_SIZE,
            this
        );
    }
}

  private void drawPacman(Graphics g) {
    int x = pacman.getY() * TILE_SIZE;
    int y = pacman.getX() * TILE_SIZE + SCORE_BAR_HEIGHT;
    g.drawImage(pacmanImage, x, y, TILE_SIZE, TILE_SIZE, this);
}



    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Score: " + scoreManager.getCurrentScore(), 10, 20);
        g.drawString("High Score: " + scoreManager.getHighScore(), 150, 20);
    }

    private void drawGameOverMessage(Graphics g) {
        String message;
        if (game.isWon()) {
            g.setColor(Color.GREEN);
            message = "You Win!";
        } else {
            g.setColor(Color.RED);
            message = "Game Over!";
        }
        g.setFont(new Font("Arial", Font.BOLD, 28));
        g.drawString(message, getWidth()/2 - 90, getHeight()/2);
    }


}