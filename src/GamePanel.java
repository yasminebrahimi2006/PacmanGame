import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class GamePanel extends JPanel {

    private static final int TILE_SIZE = 40;
    private Maze maze;

    public GamePanel(Maze maze) {
        this.maze = maze;
        int width = maze.getCols() * TILE_SIZE;
        int height = maze.getRows() * TILE_SIZE;
        this.setPreferredSize(new java.awt.Dimension(width, height));
        this.setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMaze(g);
    }

    private void drawMaze(Graphics g) {
        for (int row = 0; row < maze.getRows(); row++) {
            for (int col = 0; col < maze.getCols(); col++) {
                int x = col * TILE_SIZE;
                int y = row * TILE_SIZE;

                if (maze.isWall(row, col)) {
                    g.setColor(Color.BLUE);
                    g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
                } else if (maze.hasPelletMark(row, col)) {
                    g.setColor(Color.YELLOW);
                    int dotSize = 6;
                    g.fillOval(x + TILE_SIZE/2 - dotSize/2, y + TILE_SIZE/2 - dotSize/2, dotSize, dotSize);
                }
            }
        }
    }
}