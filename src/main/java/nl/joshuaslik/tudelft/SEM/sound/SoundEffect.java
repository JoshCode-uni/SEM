/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.sound;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.AudioClip;

/**
 * The Sound Effect class can be used to play sound effects like, for example, voices.
 *
 * @author Faris
 */
public class SoundEffect {

    private final AudioClip audio;
    private boolean echo = true;

    /**
     * Create a sound effect.
     *
     * @param audioPath the path of the audio file.
     */
    protected SoundEffect(String audioPath) {
        this.audio = new AudioClip(audioPath);
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
     * Stop a sound effect loop.
     */
    protected synchronized void stopLoop() {
        audio.stop();
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
                audio.play(0.3, this.balance, 1, 1, 1);
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
            audio.play(this.volume, this.balance, 1, 1, 1);
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
        audio.setCycleCount(Integer.MAX_VALUE);
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
