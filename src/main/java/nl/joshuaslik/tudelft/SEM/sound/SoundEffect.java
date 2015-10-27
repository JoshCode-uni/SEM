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
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * The Sound Effect class can be used to play sound effects like, for example, voices.
 *
 * @author Faris
 */
public class SoundEffect {

    private final String audioPath;
    private Clip soundClip;
    private boolean echo = true;
    private int loop = 0;

    /**
     * Create a sound effect.
     *
     * @param audioPath the path of the audio file.
     */
    protected SoundEffect(String audioPath) {
        this.audioPath = audioPath;
    }

    private void initializeClip() {
        try {
            soundClip = AudioSystem.getClip();
            soundClip.open(AudioSystem.getAudioInputStream(getClass().
                    getResourceAsStream(audioPath)));
        }
        catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(SoundEffect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Play a sound effect.
     *
     * @param balance the balance of the sound.
     */
    protected void play(double balance) {
        playAudio(1, balance);

        if (echo) {
            createEcho(balance);
        }
    }

    /**
     * Play a sound effect loop.
     *
     * @param balance the balance of the sound.
     */
    protected void playLoop(double balance) {
        playAudioLoop(1, balance);
    }

    /**
     * Play a sound effect loop.
     *
     * @param volume the volume.
     * @param balance the balance of the sound.
     */
    protected void playLoop(double volume, double balance) {
        playAudioLoop(volume, balance);
    }

    /**
     * Stop a sound effect loop.
     */
    protected synchronized void stopLoop() {
        if (soundClip != null) {
            soundClip.stop();
        }
    }

    /**
     * Create an echo.
     *
     * @param balance the balance of the sound.
     */
    private void createEcho(double balance) {
        EchoPlayer echoPlayer = new EchoPlayer();
        echoPlayer.balance = balance;
        new Thread(echoPlayer).start();
    }

    /**
     * Runnable class which will create an effect after 300 milliseconds.
     */
    private class EchoPlayer implements Runnable {

        private double balance;

        /**
         * The run method of the runnable interface. Wait 300 milliseconds and then play the echo.
         */
        @Override
        public void run() {
            try {
                Thread.sleep(300);
                initializeClip();
                try {
                    FloatControl control = 
                            (FloatControl) soundClip.getControl(FloatControl.Type.PAN);
                    control.setValue((float) this.balance);
                } catch (Exception e) { }
                try {
                    FloatControl control2 = 
                            (FloatControl) soundClip.getControl(FloatControl.Type.MASTER_GAIN);
                    control2.setValue(-6);
                } catch (Exception e) { }
                soundClip.start();
            }
            catch (InterruptedException ex) {
                Logger.getLogger(SoundEffect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * A regular sound playing runnable class.
     */
    private class RegularPlayer implements Runnable {

        private double volume;
        private double balance;

        /**
         * Play the sound.
         */
        @Override
        public void run() {
            initializeClip();
            try {
                FloatControl control = (FloatControl) soundClip.getControl(FloatControl.Type.PAN);
                control.setValue((float) this.balance);
            }
            catch (Exception e) {
            }
            try {
                FloatControl control2 = 
                        (FloatControl) soundClip.getControl(FloatControl.Type.MASTER_GAIN);
                control2.setValue((float) (this.volume - 1) * 4);
            }
            catch (Exception e) {
            }
            soundClip.loop(loop);
            soundClip.start();
        }
    }

    /**
     * Play the sound effect.
     *
     * @param volume the volume.
     * @param balance the balance.
     */
    private synchronized void playAudio(double volume, double balance) {
        RegularPlayer soundPlayer = new RegularPlayer();
        soundPlayer.volume = volume;
        soundPlayer.balance = balance;
        Thread t = new Thread(soundPlayer);
        t.start();
    }

    /**
     * Play the sound effect in a loop.
     *
     * @param volume the volume.
     * @param balance the balance.
     */
    private synchronized void playAudioLoop(double volume, double balance) {
        loop = Integer.MAX_VALUE;
        RegularPlayer soundPlayer = new RegularPlayer();
        soundPlayer.volume = volume;
        soundPlayer.balance = balance;
        Thread t = new Thread(soundPlayer);
        t.start();
    }

    /**
     * Set the 'should echo' value to true or false.
     *
     * @param echo if an echo should be played.
     */
    protected void setEcho(boolean echo) {
        this.echo = echo;
    }
}
