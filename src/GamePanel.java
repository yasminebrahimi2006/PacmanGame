import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

public class GamePanel extends JPanel {

    private static final int TILE_SIZE = 40;
    private static final int SCORE_BAR_HEIGHT = 30;

    private Maze maze;
    private Pacman pacman;
    private Ghost[] ghosts;
    private List<Pellet> pellets;
    private ScoreManager scoreManager;

    public GamePanel(Maze maze, Pacman pacman, Ghost[] ghosts, List<Pellet> pellets, ScoreManager scoreManager) {
        this.maze = maze;
        this.pacman = pacman;
        this.ghosts = ghosts;
        this.pellets = pellets;
        this.scoreManager = scoreManager;

        int width = maze.getCols() * TILE_SIZE;
        int height = maze.getRows() * TILE_SIZE + SCORE_BAR_HEIGHT;
        this.setPreferredSize(new java.awt.Dimension(width, height));
        this.setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMaze(g);
        drawPellets(g);
        drawGhosts(g);
        drawPacman(g);
        drawScore(g);
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

    private void drawGhosts(Graphics g) {
        g.setColor(Color.RED);
        for (Ghost ghost : ghosts) {
            int x = ghost.getY() * TILE_SIZE;
            int y = ghost.getX() * TILE_SIZE + SCORE_BAR_HEIGHT;
            g.fillOval(x + 4, y + 4, TILE_SIZE - 8, TILE_SIZE - 8);
        }
    }

    private void drawPacman(Graphics g) {
        int x = pacman.getY() * TILE_SIZE;
        int y = pacman.getX() * TILE_SIZE + SCORE_BAR_HEIGHT;
        g.setColor(Color.YELLOW);
        g.fillOval(x + 4, y + 4, TILE_SIZE - 8, TILE_SIZE - 8);
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Score: " + scoreManager.getCurrentScore(), 10, 20);
    }
}