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
 * Used to play a sound effect Example: Thread t2 = new Thread(new SoundEffect(getClass().
 * getResource("/data/sound/effects/MultiKill.wav").toExternalForm())); t2.start();
 *
 * @author Faris
 */
public class SoundEffect {

    private final AudioClip audio;
    private boolean echo = true;

    public SoundEffect(String audioPath) {
        this.audio = new AudioClip(audioPath);
    }

    protected void play(double balance) {
        playAudio(1, balance);

        if (echo) {
            createEcho(balance);
        }
    }

    private void createEcho(double balance) {
        EchoPlayer echoPlayer = new EchoPlayer();
        echoPlayer.balance = balance;
        new Thread(echoPlayer).start();
    }

    private class EchoPlayer implements Runnable {

        private double balance;

        @Override
        public void run() {
            try {
                Thread.sleep(300);
                playAudio(0.25, balance);
            }
            catch (InterruptedException ex) {
                Logger.getLogger(SoundEffect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private synchronized void playAudio(double volume, double balance) {
        audio.play(2 * volume, balance, 1, 1, 1);
    }

    protected void setEcho(boolean echo) {
        this.echo = echo;
    }
}
