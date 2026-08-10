import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundManager {

    private Clip clip;

    public void playSound(String filePath) {
        try {
            File soundFile = new File(filePath);

            System.out.println("Sound path: " + soundFile.getAbsolutePath());
            System.out.println("File exists: " + soundFile.exists());

            AudioInputStream audioStream =
                    AudioSystem.getAudioInputStream(soundFile);

            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

        } catch (Exception e) {
            System.out.println("Sound error: " + filePath);
            e.printStackTrace();
        }
    }
}
