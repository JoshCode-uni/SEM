/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.sound;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.IOberservableGameObjectContainer;
import nl.joshuaslik.tudelft.SEM.sound.observer.BubbleSoundObserver;
import nl.joshuaslik.tudelft.SEM.sound.observer.PlayerSoundObserver;

/**
 *
 * @author Faris
 */
public class EffectPlayer {

    private static EffectPlayer effectPlayer = null;
    private boolean playSound = true;

    private EffectPlayer() {
    }

    public void addListenersTo(IOberservableGameObjectContainer ob) {
        ob.addObserver(new BubbleSoundObserver(this));
        ob.addObserver(new PlayerSoundObserver(this));
    }

    public void play(EnumAudioTypes audioType) {
        play(audioType, 0);
    }

    public void play(EnumAudioTypes audioType, double balance) {
        if (playSound) {
            audioType.getSound().play(balance);
        }
    }

    /**
     * Toggle the sound effects.
     */
    public void toggleSound() {
        playSound = !playSound;
    }

    public static EffectPlayer getInstace() {
        if (effectPlayer == null) {
            effectPlayer = new EffectPlayer();
        }
        return effectPlayer;
    }
}
