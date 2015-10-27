/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.playerMods;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.AbstractPlayerDecorator;

/**
 * Projectile spike delay increase modifier.
 *
 * @author faris
 */
public class ProjectileSpikeDelayUp extends AbstractPlayerDecorator {

    /**
     * Increase the projectile spike delay.
     *
     * @return increased projectile spike delay.
     */
    @Override
    public int getProjectileSpikeDelay() {
        return 1 + getChild().getProjectileSpikeDelay();
    }

    /**
     * Default player speed multiplier.
     *
     * @return player speed multiplier.
     */
    @Override
    public double getMoveSpeedMultiplier() {
        return 1 * getChild().getMoveSpeedMultiplier();
    }

    /**
     * Default projectile speed multiplier.
     *
     * @return the projectile speed multiplier.
     */
    @Override
    public double getProjectileSpeedMultiplier() {
        return 1 * getChild().getProjectileSpeedMultiplier();
    }
}
