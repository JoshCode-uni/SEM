/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.playerMods;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.AbstractPlayerDecorator;

/**
 * Projectile speed up modifier.
 * @author faris
 */
public class ProjectileSpeedUp extends AbstractPlayerDecorator {

    /**
     * Multiply the speed of the projectile.
     * @return the speed of the projectile.
     */
    @Override
    public double getProjectileSpeedMultiplier() {
        return 1.3 * getChild().getProjectileSpeedMultiplier();
    }

    /**
     * The default speed of the player.
     * @return the speed of the player.
     */
    @Override
    public double getMoveSpeedMultiplier() {
        return 1 * getChild().getMoveSpeedMultiplier();
    }

    /**
     * The default spike delay.
     * @return the spike delay.
     */
    @Override
    public int getProjectileSpikeDelay() {
        return 0 + getChild().getProjectileSpikeDelay();
    }
}
