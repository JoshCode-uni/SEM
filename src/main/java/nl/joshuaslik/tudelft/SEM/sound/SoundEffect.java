/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.sound;

import javafx.scene.media.AudioClip;

/**
 * Used to play a sound effect
 * Example:
 * Thread t2 = new Thread(new SoundEffect(getClass().
 *         getResource("/data/sound/effects/MultiKill.wav").toExternalForm()));
 *     t2.start();
 * @author Faris
 */
public class SoundEffect implements Runnable {

    private final AudioClip audio;
    
    public SoundEffect(String source) {
        audio = new AudioClip(source);
    }
    
    @Override
    public void run() {
//        audio.setBalance(0.8);
        audio.play();
    }
}
