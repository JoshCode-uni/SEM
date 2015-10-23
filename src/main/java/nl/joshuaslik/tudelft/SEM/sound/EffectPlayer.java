/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.sound;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.IOberservableGameObjectContainer;
import nl.joshuaslik.tudelft.SEM.sound.observer.BubbleSoundObserver;
import nl.joshuaslik.tudelft.SEM.sound.observer.PickupSoundObserver;
import nl.joshuaslik.tudelft.SEM.sound.observer.PlayerSoundObserver;

/**
 * A sound effect player. Can be used to add listeners to game objects and will play a sound effect
 * when one of those listeners gets an event from one of the game objects.
 * @author Faris
 */
public class EffectPlayer {

    private static EffectPlayer effectPlayer = null;
    private boolean playSound = true;

    /**
     * Private constructor to only allow the getInstance method to create an instance of this class.
     * This class is a singleton.
     */
    private EffectPlayer() {
    }

    /**
     * Add observers/listeners to gameobjects.
     * @param ob the object to add observers to.
     */
    public void addListenersTo(IOberservableGameObjectContainer ob) {
        ob.addObserver(new BubbleSoundObserver(this));
        ob.addObserver(new PlayerSoundObserver(this));
        ob.addObserver(new PickupSoundObserver(this));
    }

    /**
     * Play a sound.
     * @param audioType enum object containing a sound effect.
     */
    public void play(EnumAudioTypes audioType) {
        play(audioType, 0);
    }

    /**
     * Play a sound.
     * @param audioType enum object containing a sound effect.
     * @param balance the balance of the sound (between -1 and 1).
     */
    public void play(EnumAudioTypes audioType, double balance) {
        if (playSound) {
            audioType.getSound().play(balance);
        }
    }
    
    /**
     * Play a sound loop.
     * @param audioType enum object containing a sound effect.
     * @param balance the balance of the sound (between -1 and 1).
     */
    public void playLoop(EnumAudioTypes audioType, double balance) {
        if (playSound) {
            audioType.getSound().playLoop(balance);
        }
    }
    
    /**
     * Stop a sound loop.
     * @param audioType enum object containing a sound effect.
     */
    public void stopLoop(EnumAudioTypes audioType) {
        if (playSound) {
            audioType.getSound().stopLoop();
        }
    }

    /**
     * Toggle the sound effects.
     */
    public void toggleSound() {
        playSound = !playSound;
    }

    /**
     * Get the instance of this effect player. This class is a singleton.
     * @return the effect player.
     */
    public static EffectPlayer getInstace() {
        if (effectPlayer == null) {
            effectPlayer = new EffectPlayer();
        }
        return effectPlayer;
    }
    
    public void destroy() {
        stopLoop(EnumAudioTypes.WALK_LOOP);
    }
}
