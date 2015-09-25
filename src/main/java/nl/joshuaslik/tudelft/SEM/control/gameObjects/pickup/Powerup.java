/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.IGameObjects;

/**
 *
 * @author faris
 * @param <T> type of the powerup
 */
public class Powerup<T extends IModifier> extends AbstractPickup {

    private T mod;
    private final PowerupTypes pickupType;

    public Powerup(IGameObjects gameObjects, PowerupTypes pickupType) {
        super(gameObjects, pickupType.getImageStream(), pickupType.getImageHeight(), pickupType.getImageWidth());
        this.pickupType = pickupType;
    }

    public void setModifier(T mod) {
        this.mod = mod;
    }

    public T decorateModifier(T currentMod) {
        return (T) pickupType.getDecor().decorate(mod);
    }

    @Override
    public void handlePlayerCollision() {
        getGameObjects().handlePickupCollision(pickupType.getMod(), pickupType.isPlayerPickup(), 
                pickupType.isBubblePickup());
    }
}
