/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.IGameObjects;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.AbstractPickup;

/**
 * @param <T> type of the powerup
 * @author faris
 */
public class Powerup<T> extends AbstractPickup {

    private final T mod;
    private final EnumPowerupTypes pickupType;

    public Powerup(IGameObjects gameObjects, EnumPowerupTypes pickupType, double xCoordinate, double yCoordinate) {
        super(gameObjects, pickupType.getImageStream(), pickupType.getImageHeight(), pickupType.getImageWidth(), xCoordinate, yCoordinate);
        this.pickupType = pickupType;
        mod = (T) pickupType.getDecor();
    }

    @Override
    public void handlePlayerCollision() {
        destroy();
        getGameObjects().handleModifierCollision(mod, pickupType.isPlayerPickup(), pickupType.isBubblePickup());
    }
}
