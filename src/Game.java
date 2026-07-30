public class Game {

    private Pacman pacman;
    private Ghost[] ghosts;
    private Maze maze;
    private ScoreManager scoreManager;

    public Game() {
        this.maze = new Maze();
        this.pacman = new Pacman(1, 1);
        this.ghosts = new Ghost[2];
        this.scoreManager = new ScoreManager();
    }
}
