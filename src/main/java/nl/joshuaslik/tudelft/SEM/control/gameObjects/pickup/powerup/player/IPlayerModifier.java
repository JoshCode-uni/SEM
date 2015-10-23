/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player;

/**
 * The player modifier interface.
 * @author faris
 */
public interface IPlayerModifier {

    /**
     * Get the speed multiplier of the player.
     * @return the speed multiplier of the player.
     */
    double getMoveSpeedMultiplier();

    /**
     * Get the projectile speed multiplier.
     * @return the projectile speed multiplier.
     */
    double getProjectileSpeedMultiplier();

    /**
     * Get the projectile delay.
     * @return the projectile delay.
     */
    int getProjectileSpikeDelay();
}
