/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.playerMods;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.AbstractPlayerDecorator;

/**
 * Speed up modifier of the player.
 * @author faris
 */
public class PlayerSpeedUp extends AbstractPlayerDecorator {

    /**
     * Increase the speed of the player.
     * @return increased speed of the player.
     */
    @Override
    public double getMoveSpeedMultiplier() {
        return 1.3 * getChild().getMoveSpeedMultiplier();
    }

    /**
     * Default speed of the projectile.
     * @return speed of the projectile.
     */
    @Override
    public double getProjectileSpeedMultiplier() {
        return 1 * getChild().getProjectileSpeedMultiplier();
    }

    /**
     * Default delay of the projectile.
     * @return delay of the projectile.
     */
    @Override
    public int getProjectileSpikeDelay() {
        return getChild().getProjectileSpikeDelay();
    }
}
