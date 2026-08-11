import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ScoreManager {
    private int currentScore;
    private int highScore;
    private static final String FILE_NAME = "highscore.txt";

    public ScoreManager() {
        this.currentScore = 0;
        this.highScore = loadHighScore();
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public void addPoints(int amount) {
        currentScore += amount;
        if (currentScore > highScore) {
            highScore = currentScore;
        }
    }

    public void subtractPoints(int amount) {
        currentScore -= amount;
        if (currentScore < 0) {
            currentScore = 0;
        }
    }

    public void resetCurrentScore() {  // for the PlayAgain button
        currentScore = 0;
    }

    private int loadHighScore() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return 0;
        }

        try {
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextInt()) {
                int savedScore = scanner.nextInt();
                scanner.close();
                return savedScore;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            return 0;
        }

        return 0;
    }

    public void saveHighScore() {
        try {
            PrintWriter writer = new PrintWriter(FILE_NAME);
            writer.println(highScore);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("خطا در ذخیره امتیاز.");
        }
    }
}