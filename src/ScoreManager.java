public class ScoreManager {
    private int currentScore;

    public ScoreManager() {
        this.currentScore = 0;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void addPoints(int amount) {
        currentScore += amount;
    }

    public void subtractPoints(int amount) {
        currentScore -= amount;
        if (currentScore < 0) {
            currentScore = 0;
        }
    }
}