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
 *
 * @author Faris
 */
public class PlayerSoundObserver implements IObserver<Player, Player.EventType> {

    private final EffectPlayer effectPlayer;
    
    public PlayerSoundObserver(EffectPlayer effectPlayer) {
        this.effectPlayer = effectPlayer;
    }
    
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

    @Override
    public boolean sameClass(Class observed) {
        return observed.equals(Player.class);
    }
}
