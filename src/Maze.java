public class Maze {

    private int[][] grid;
    private static final int WALL = 1;
    private static final int PATH = 0;
    private static final int PELLET_MARK = 2;

    public Maze() {
        grid = new int[][] {
            {1,1,1,1,1,1,1,1,1,1},
            {1,2,2,2,1,2,2,2,2,1},
            {1,2,1,2,1,2,1,1,2,1},
            {1,2,1,2,2,2,2,1,2,1},
            {1,2,1,1,1,1,2,1,2,1},
            {1,2,2,2,2,1,2,2,2,1},
            {1,1,1,2,1,1,1,1,2,1},
            {1,2,2,2,2,2,2,2,2,1},
            {1,2,1,1,1,1,1,1,2,1},
            {1,1,1,1,1,1,1,1,1,1}
        };
    }

    public int getRows() {
        return grid.length;
    }

    public int getCols() {
        return grid[0].length;
    }

    public boolean isWall(int row, int col) {
        if (row < 0 || row >= getRows() || col < 0 || col >= getCols()) {
            return true;
        }
        return grid[row][col] == WALL;
    }

    public boolean hasPelletMark(int row, int col) {
        return grid[row][col] == PELLET_MARK;
    }

    public void clearPelletMark(int row, int col) {
        grid[row][col] = PATH;
    }
}
