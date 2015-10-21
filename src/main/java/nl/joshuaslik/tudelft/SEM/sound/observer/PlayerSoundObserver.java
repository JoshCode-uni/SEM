/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.sound.observer;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.Player;
import nl.joshuaslik.tudelft.SEM.sound.EffectPlayer;
import nl.joshuaslik.tudelft.SEM.sound.EnumAudioTypes;
import nl.joshuaslik.tudelft.SEM.utility.IObserver;

/**
 * An observer which observes the events of a player.
 * @author Faris
 */
public class PlayerSoundObserver implements IObserver<Player, Player.EventType> {

    private final EffectPlayer effectPlayer;
    
    /**
     * Create a player observer.
     * @param effectPlayer the effect player which can play the sound.
     */
    public PlayerSoundObserver(EffectPlayer effectPlayer) {
        this.effectPlayer = effectPlayer;
    }
    
    /**
     * The update method. Used to notify the observer of an event.
     * @param player the player which triggered the event.
     * @param event the type of event.
     */
    @Override
    public void update(Player player, Player.EventType event) {
        switch (event) {
            case DIED:
                effectPlayer.play(EnumAudioTypes.HUMILIATING_DEFEAT);
                break;
            case SHOOT:
                // no sound yet
                break;
            case WALK:
                // no sound yet
                break;
            default:
                throw new AssertionError();
        }
    }

    /**
     * Checks if the given class is the class which this observer can observe (the player calss).
     * @param observed a class.
     * @return if the given class is the player class.
     */
    @Override
    public boolean sameClass(Class observed) {
        return observed.equals(Player.class);
    }
}
