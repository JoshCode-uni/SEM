/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.sound;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Play the overall music. First start the intro, after that keep looping the loop part.
 *
 * @author Faris
 */
public class MusicLoop implements Runnable {

    private final InputStream intro;
    private final InputStream loop;
    private final int LONG_FRAME_TIME = 1100;

    /**
     * Initialize input streams.
     */
    public MusicLoop() {
        intro = getClass().getResourceAsStream("/data/sound/DYAMY - United Intro.wav");
        loop = getClass().getResourceAsStream("/data/sound/DYAMY - United Loop.wav");
    }

    /**
     * Start the music intro and after the intro keep looping the loop music.
     */
    @Override
    public void run() {
        try {
            Clip clip = AudioSystem.getClip(), loopClip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(intro));
            loopClip.open(AudioSystem.getAudioInputStream(loop));
            
            LineListener stopEvent = (LineEvent event) -> {
                if(clip.getFrameLength() < clip.getLongFramePosition() + LONG_FRAME_TIME) {
                    loopClip.loop(Clip.LOOP_CONTINUOUSLY);
                }
            };
            clip.addLineListener(stopEvent);
            clip.start();
        }
        catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(MusicLoop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
