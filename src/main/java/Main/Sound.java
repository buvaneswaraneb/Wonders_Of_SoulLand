package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.net.URL;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[10];

    public Sound(){
        soundURL[0] = getClass().getResource("/Sound/powerUP.wav");
        soundURL[1] = getClass().getResource("/Sound/background.wav");
        soundURL[2] = getClass().getResource("/Sound/wood.wav");
    }

    public void setFile(int idx){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[idx]);
            this.clip = AudioSystem.getClip();
            this.clip.open(ais);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void play(){
        clip.start();
    }

    public  void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }
}

