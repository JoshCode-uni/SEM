/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.sound;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
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

    private final String introPath = "/data/sound/DYAMY - United Intro.wav";
    private final String loopPath = "/data/sound/DYAMY - United Loop.wav";
    private final int LONG_FRAME_TIME = 1100;
    private static MusicLoop musicLoop = null;
    private boolean isPlaying = false;
    private Clip introClip, loopClip;
    private static final Object LOCK = new Object();

    /**
     * Private constructor to avoid initialization outside of getInstance method.
     */
    private MusicLoop() {
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
                introClip.open(AudioSystem.getAudioInputStream(getClass().
                        getResourceAsStream(introPath)));
                loopClip.open(AudioSystem.getAudioInputStream(getClass().
                        getResourceAsStream(loopPath)));
                adjustAudio();

                introClip.addLineListener(stopEvent);
                introClip.start();
            }
            catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
                Logger.getLogger(MusicLoop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /**
         * Set the audio a bit lower.
         */
        private void adjustAudio() {
            FloatControl control = (FloatControl) introClip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(-25f);
            control = (FloatControl) loopClip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(-25f);
        }
    }

    /**
     * Start the music loop if it wasn't started yet.
     */
    public void start() {
        synchronized (LOCK) {
            if (isPlaying) {
                return;
            }
            isPlaying = true;
            Thread musicThread = new Thread(new Music());
            musicThread.start();
        }
    }

    /**
     * Stop the music.
     */
    private void stop() {
        if (introClip != null) {
            introClip.close();
            introClip = null;
        }
        if (loopClip != null) {
            loopClip.close();
            loopClip = null;
        }
        isPlaying = false;
    }

    /**
     * Toggle the music.
     */
    public void toggleMusic() {
        synchronized (LOCK) {
            if (isPlaying) {
                stop();
            } else {
                start();
            }
        }
    }

    public boolean isPlaying() {
        synchronized (LOCK) {
            return isPlaying;
        }
    }

    /**
     * Get the instance of MusicLoop.
     *
     * @return the MusicLoop.
     */
    public static MusicLoop getInstance() {
        synchronized (LOCK) {
            if (musicLoop == null) {
                musicLoop = new MusicLoop();
            }
            return musicLoop;
        }
    }
}
