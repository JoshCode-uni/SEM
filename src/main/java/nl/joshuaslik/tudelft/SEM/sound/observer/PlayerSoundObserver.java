/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.sound.observer;

import java.util.Random;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.Player;
import nl.joshuaslik.tudelft.SEM.sound.EffectPlayer;
import nl.joshuaslik.tudelft.SEM.sound.EnumAudioTypes;
import nl.joshuaslik.tudelft.SEM.utility.IObserver;

/**
 * An observer which observes the events of a player.
 *
 * @author Faris
 */
public class PlayerSoundObserver implements IObserver<Player, Player.EventType> {

    private final EffectPlayer effectPlayer;
    private final Random rand = new Random();
    private boolean walkLoopPlaying = false;

    /**
     * Create a player observer.
     *
     * @param effectPlayer the effect player which can play the sound.
     */
    public PlayerSoundObserver(EffectPlayer effectPlayer) {
        this.effectPlayer = effectPlayer;
    }

    /**
     * The update method. Used to notify the observer of an event.
     *
     * @param player the player which triggered the event.
     * @param event the type of event.
     */
    @Override
    public void update(Player player, Player.EventType event) {
        double balance = player.getRelativeXPos() * 1.8 - 0.9;
        switch (event) {
            case DIED:
                effectPlayer.play(EnumAudioTypes.UT_HUMILIATING_DEFEAT);
                break;
            case SHOOT:
                handleShoot(balance);
                break;
            case WALK:
                handleWalk();
                break;
            case NOT_WALKING:
                handleNotWalking();
                break;
            default:
                throw new AssertionError();
        }
    }

    private void handleShoot(double balance) {
        if (rand.nextBoolean()) {
            effectPlayer.play(EnumAudioTypes.SHOOT_1, balance);
        } else {
            effectPlayer.play(EnumAudioTypes.SHOOT_2, balance);
        }
    }

    private void handleWalk() {
        if(!walkLoopPlaying) {
            walkLoopPlaying = true;
            effectPlayer.playLoop(EnumAudioTypes.WALK_LOOP, 0);
        }
    }
    
    private void handleNotWalking() {
        if(walkLoopPlaying) {
            walkLoopPlaying = false;
            effectPlayer.stopLoop(EnumAudioTypes.WALK_LOOP);
        }
    }

    /**
     * Checks if the given class is the class which this observer can observe (the player calss).
     *
     * @param observed a class.
     * @return if the given class is the player class.
     */
    @Override
    public boolean sameClass(Class observed) {
        return observed.equals(Player.class);
    }
}
