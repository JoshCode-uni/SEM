/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.sound.observer;

import java.util.Random;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.AbstractPickup;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.PickupEventType;
import nl.joshuaslik.tudelft.SEM.sound.EffectPlayer;
import nl.joshuaslik.tudelft.SEM.sound.EnumAudioTypes;
import nl.joshuaslik.tudelft.SEM.utility.IObserver;

/**
 * A event listener which plays sounds every time there is a pickup event.
 * @author Faris
 */
public class PickupSoundObserver implements IObserver<AbstractPickup, PickupEventType> {

    private final EffectPlayer effectPlayer;
    private final Random rand = new Random();

    /**
     * Create a bubble observer.
     *
     * @param effectPlayer the effect player which can play the sound.
     */
    public PickupSoundObserver(EffectPlayer effectPlayer) {
        this.effectPlayer = effectPlayer;
    }

    /**
     * The update method. Used to notify the observer of an event.
     *
     * @param pickup the pickup which triggered the event.
     * @param event the type of event.
     */
    @Override
    public void update(AbstractPickup pickup, PickupEventType event) {
        double balance = pickup.getRelativeXPos() * 1.8 - 0.9;
        switch (event) {
            case COIN:
                effectPlayer.play(EnumAudioTypes.COIN, balance);
                break;
            case LIFE:
                handleLife(balance);
                break;
            default:
                throw new AssertionError();
        }
    }

    /**
     * Handle the picked up life event.
     *
     * @param balance the balance of the sound.
     */
    private void handleLife(double balance) {
        if (rand.nextDouble() < 0.33) {
            effectPlayer.play(EnumAudioTypes.LIFE_1, balance);
        } else if (rand.nextBoolean()) {
            effectPlayer.play(EnumAudioTypes.LIFE_2, balance);
        } else {
            effectPlayer.play(EnumAudioTypes.LIFE_3, balance);
        }
    }

    /**
     * Checks if the given class is the class which this observer can observe (a pickup).
     *
     * @param observed a class.
     * @return if the given class is the bubble class.
     */
    @Override
    public boolean sameClass(Class observed) {
        return AbstractPickup.class.isAssignableFrom(observed);
    }
}
