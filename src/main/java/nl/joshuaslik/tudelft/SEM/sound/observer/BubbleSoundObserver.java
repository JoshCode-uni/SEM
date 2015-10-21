/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.sound.observer;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.Bubble;
import nl.joshuaslik.tudelft.SEM.sound.EffectPlayer;
import nl.joshuaslik.tudelft.SEM.sound.EnumAudioTypes;
import nl.joshuaslik.tudelft.SEM.utility.IObserver;

/**
 * An observer which observes the events of a bubble.
 * @author Faris
 */
public class BubbleSoundObserver implements IObserver<Bubble, Bubble.EventType> {

    private final EffectPlayer effectPlayer;
    private long lastSplitTime = Long.MAX_VALUE;
    private int killingSpree = 0;
    private boolean firstKill = true;

    /**
     * Create a bubble observer.
     * @param effectPlayer the effect player which can play the sound.
     */
    public BubbleSoundObserver(EffectPlayer effectPlayer) {
        this.effectPlayer = effectPlayer;
    }

    /**
     * The update method. Used to notify the observer of an event.
     * @param bubble the bubble which triggered the event.
     * @param event the type of event.
     */
    @Override
    public void update(Bubble bubble, Bubble.EventType event) {
        switch (event) {
            case SPLIT:
                handleKill(bubble);
                break;
            case COLLIDE:
                // no sound yet
                break;
            case KILLED_CEILING:
                // no sound yet
                break;
            case KILLED_PROJECTILE:
                handleKill(bubble);
                break;
            default:
                throw new AssertionError();
        }
    }

    /**
     * Handle the 'bubble is killed' event.
     * @param bubble the bubble which was killed.
     */
    public void handleKill(Bubble bubble) {
        double balance = bubble.getRelativeXPos() * 1.8 - 0.9;
        if (firstKill) {
            effectPlayer.play(EnumAudioTypes.FIRST_BLOOD, balance);
            firstKill = false;
        }
        long time = System.currentTimeMillis();
        if (time - lastSplitTime < 2000) {
            killingSpree++;
        } else {
            killingSpree = 1;
        }
        decideKillSound(balance);
        lastSplitTime = time;
    }

    /**
     * Decide which kill sound to play.
     * @param balance the balance of the sound.
     */
    private void decideKillSound(double balance) {
        switch (killingSpree) {
            case 2:
                effectPlayer.play(EnumAudioTypes.DOUBLE_KILL, balance);
                break;
            case 3:
                effectPlayer.play(EnumAudioTypes.MULTI_KILL, balance);
                break;
            case 5:
                effectPlayer.play(EnumAudioTypes.MEGA_KILL, balance);
                break;
            case 8:
                effectPlayer.play(EnumAudioTypes.MONSTER_KILL, balance);
                break;
        }
        if (killingSpree > 8 && killingSpree % 2 == 0) {
            effectPlayer.play(EnumAudioTypes.KILLING_SPREE, balance);
        }
    }

    /**
     * Checks if the given class is the class which this observer can observe (the bubble calss).
     * @param observed a class.
     * @return if the given class is the bubble class.
     */
    @Override
    public boolean sameClass(Class observed) {
        return observed.equals(Bubble.class);
    }
}
