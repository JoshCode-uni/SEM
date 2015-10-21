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
    
    public EffectPlayer(IOberservableGameObjectContainer ob) {
        ob.addObserver(new BubbleSoundObserver(this));
        ob.addObserver(new PlayerSoundObserver(this));
    }
    
    public void play(EnumAudioTypes audioType) {
        play(audioType, 0);
    }
    
    public void play(EnumAudioTypes audioType, double balance) {
        audioType.getSound().play(balance);
    }
    
    public void destroy() {
        
    }
}
