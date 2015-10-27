/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.sound.observer;

import java.util.Random;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.Bubble;
import nl.joshuaslik.tudelft.SEM.sound.EffectPlayer;
import nl.joshuaslik.tudelft.SEM.sound.EnumAudioTypes;
import nl.joshuaslik.tudelft.SEM.utility.IObserver;

/**
 * An observer which observes the events of a bubble.
 *
 * @author Faris
 */
public class BubbleSoundObserver implements IObserver<Bubble, Bubble.EventType> {

    private final EffectPlayer effectPlayer;
    private long lastSplitTime = Long.MAX_VALUE;
    private int killingSpree = 0;
    private boolean firstKill = true;
    private final Random rand = new Random();
    private final boolean utSounds;

    /**
     * Create a bubble observer.
     *
     * @param effectPlayer the effect player which can play the sound.
     * @param utSounds
     */
    public BubbleSoundObserver(EffectPlayer effectPlayer, boolean utSounds) {
        this.effectPlayer = effectPlayer;
        this.utSounds = utSounds;
    }

    /**
     * The update method. Used to notify the observer of an event.
     *
     * @param bubble the bubble which triggered the event.
     * @param event the type of event.
     */
    @Override
    public void update(Bubble bubble, Bubble.EventType event) {
        double balance = bubble.getRelativeXPos() * 1.8 - 0.9;
        switch (event) {
            case SPLIT:
                handleKill(balance);
                break;
            case COLLIDE:
                handleCollide(balance);
                break;
            case KILLED_CEILING:
                effectPlayer.play(EnumAudioTypes.SHOOT_1, balance);
                break;
            case KILLED_PROJECTILE:
                handleKill(balance);
                break;
            default:
                throw new AssertionError();
        }
    }

    /**
     * Handle the 'bubble is killed' event.
     *
     * @param balance the balance of the sound.
     */
    public void handleKill(double balance) {
        if (firstKill) {
            playFirstBlood(balance);
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
     * Play the first blood sound.
     *
     * @param balance the balance of the sound.
     */
    private void playFirstBlood(double balance) {
        if (utSounds) {
            effectPlayer.play(EnumAudioTypes.UT_FIRST_BLOOD, balance);
        } else {
            effectPlayer.play(EnumAudioTypes.GLADOS_FIRSTBLOOD, balance);
        }
    }

    /**
     * Decide which kill sound to play.
     *
     * @param balance the balance of the sound.
     */
    private void decideKillSound(double balance) {
        if (utSounds) {
            decideUtKillSound(balance);
        } else {
            decideGladdosKillSound(balance);
        }
    }

    /**
     * Decide which unreal tournament sound to play.
     *
     * @param balance the balance of the sound.
     */
    private void decideUtKillSound(double balance) {
        switch (killingSpree) {
            case 2:
                effectPlayer.play(EnumAudioTypes.UT_DOUBLE_KILL, balance);
                break;
            case 3:
                effectPlayer.play(EnumAudioTypes.UT_MULTI_KILL, balance);
                break;
            case 5:
                effectPlayer.play(EnumAudioTypes.UT_MEGA_KILL, balance);
                break;
            case 8:
                effectPlayer.play(EnumAudioTypes.UT_MONSTER_KILL, balance);
                break;
            default:
                effectPlayer.play(EnumAudioTypes.POP_1, balance);
        }
        if (killingSpree > 8 && killingSpree % 2 == 0) {
            effectPlayer.play(EnumAudioTypes.UT_KILLING_SPREE, balance);
        }
    }

    /**
     * Decide which Gladdos sound to play.
     *
     * @param balance the balance of the sound.
     */
    private void decideGladdosKillSound(double balance) {
        switch (killingSpree) {
            case 2:
                effectPlayer.play(EnumAudioTypes.GLADOS_DOUBLEKILL, balance);
                break;
            case 3:
                effectPlayer.play(EnumAudioTypes.GLADOS_TRIPLEKILL, balance);
                break;
            case 4:
                effectPlayer.play(EnumAudioTypes.GLADOS_QUADRAKILL, balance);
                break;
            case 5:
                playRandomGladdosMultiKillSound(balance);
                break;
            case 7:
                playRandomGladdosMegaKillSound(balance);
                break;
            case 9:
                playRandomGladdosMonsterKillSound(balance);
                break;
            default:
                effectPlayer.play(EnumAudioTypes.POP_2, balance);
        }
        if (killingSpree > 8 && killingSpree % 2 == 0) {
            playRandomGladdosKillingSpreeSound(balance);
        }
    }

    /**
     * Play the one of the multi kill sounds with the gladdos voice.
     *
     * @param balance the balance of the sound.
     */
    private void playRandomGladdosMultiKillSound(double balance) {
        switch (rand.nextInt(3)) {
            case 0:
                effectPlayer.play(EnumAudioTypes.GLADOS_MULTIKILL_1, balance);
                break;
            case 1:
                effectPlayer.play(EnumAudioTypes.GLADOS_MULTIKILL_2, balance);
                break;
            case 2:
                effectPlayer.play(EnumAudioTypes.GLADOS_MULTIKILL_3, balance);
        }
    }

    /**
     * Play one of the mega kill sounds with the gladdos voice.
     *
     * @param balance the balance of the sound.
     */
    private void playRandomGladdosMegaKillSound(double balance) {
        switch (rand.nextInt(2)) {
            case 0:
                effectPlayer.play(EnumAudioTypes.GLADOS_MEGAKILL_1, balance);
                break;
            case 1:
                effectPlayer.play(EnumAudioTypes.GLADOS_MEGAKILL_2, balance);
                break;
        }
    }

    /**
     * Play one of the monster kill sounds with the gladdos voice.
     *
     * @param balance the balance of the sound.
     */
    private void playRandomGladdosMonsterKillSound(double balance) {
        switch (rand.nextInt(2)) {
            case 0:
                effectPlayer.play(EnumAudioTypes.GLADOS_MONSTERKILL_1, balance);
                break;
            case 1:
                effectPlayer.play(EnumAudioTypes.GLADOS_MONSTERKILL_2, balance);
                break;
        }
    }

    /**
     * Play one of the killing spree sounds with the gladdos voice.
     *
     * @param balance the balance of the sound.
     */
    private void playRandomGladdosKillingSpreeSound(double balance) {
        switch (rand.nextInt(2)) {
            case 0:
                effectPlayer.play(EnumAudioTypes.GLADOS_DOMINATING, balance);
                break;
            case 1:
                effectPlayer.play(EnumAudioTypes.GLADOS_GODLIKE, balance);
                break;
            case 2:
                effectPlayer.play(EnumAudioTypes.GLADOS_RAMPAGE, balance);
                break;
            case 3:
                effectPlayer.play(EnumAudioTypes.GLADOS_UNSTROPPABLE, balance);
                break;
            case 4:
                effectPlayer.play(EnumAudioTypes.GLADOS_ULTRAKILL, balance);
                break;
        }
    }

    /**
     * Decide which collide sound to play.
     *
     * @param balance the balance of the sound.
     */
    private void handleCollide(double balance) {
        if (rand.nextBoolean()) {
            effectPlayer.play(EnumAudioTypes.COLLIDE_1, balance);
        } else {
            effectPlayer.play(EnumAudioTypes.COLLIDE_2, balance);
        }
    }

    /**
     * Checks if the given class is the class which this observer can observe (the bubble calss).
     *
     * @param observed a class.
     * @return if the given class is the bubble class.
     */
    @Override
    public boolean sameClass(Class observed) {
        return observed.equals(Bubble.class);
    }
}
