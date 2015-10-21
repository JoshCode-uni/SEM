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
public class MusicLoop {

    private final InputStream intro;
    private final InputStream loop;
    private final int LONG_FRAME_TIME = 1100;
    private static MusicLoop musicLoop = null;
    private boolean isPlaying = false;
    private Clip introClip, loopClip;

    /**
     * Initialize input streams.
     */
    private MusicLoop() {
        intro = getClass().getResourceAsStream("/data/sound/DYAMY - United Intro.wav");
        loop = getClass().getResourceAsStream("/data/sound/DYAMY - United Loop.wav");
    }

    /**
     * Hide the actual music thread from the outside.
     */
    private class Music implements Runnable {

        private LineListener stopEvent;

        /**
         * Initialize the stop event.
         */
        private Music() {
            stopEvent = (LineEvent event) -> {
                if (introClip.getFrameLength() < introClip.getLongFramePosition()
                        + LONG_FRAME_TIME) {
                    loopClip.loop(Clip.LOOP_CONTINUOUSLY);
                }
                if (event.getType().equals(LineEvent.Type.CLOSE)) {
                    ((Clip) event.getSource()).removeLineListener(stopEvent);
                }
            };
        }

        /**
         * Start the music intro and after the intro keep looping the loop music.
         */
        @Override
        public void run() {
            try {
                introClip = AudioSystem.getClip();
                loopClip = AudioSystem.getClip();
                introClip.open(AudioSystem.getAudioInputStream(intro));
                loopClip.open(AudioSystem.getAudioInputStream(loop));

                introClip.addLineListener(stopEvent);
                introClip.start();
            }
            catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
                Logger.getLogger(MusicLoop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Start the music loop if it wasn't started yet.
     */
    public void start() {
        if (isPlaying) {
            return;
        }
        isPlaying = true;
        Thread musicThread = new Thread(new Music());
        musicThread.start();
    }

    public void stop() {
        if (introClip != null) {
            introClip.close();
            introClip = null;
        }
        if (loopClip != null) {
            loopClip.close();
            loopClip = null;
        }
    }

    /**
     * Get the instance of MusicLoop.
     *
     * @return the MusicLoop.
     */
    public static MusicLoop getInstance() {
        if (musicLoop == null) {
            musicLoop = new MusicLoop();
        }
        return musicLoop;
    }
}
