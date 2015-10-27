/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player;

/**
 * The base player modifier.
 * @author faris
 */
public class PlayerBaseModifier implements IPlayerModifier {

    /**
     * The speed multiplier.
     * @return speed multiplier.
     */
    @Override
    public double getMoveSpeedMultiplier() {
        return 1.0;
    }

    /**
     * The projectile speed multiplier.
     * @return projectile speed multiplier.
     */
    @Override
    public double getProjectileSpeedMultiplier() {
        return 1.0;
    }

    /**
     * The projectile delay.
     * @return projectile delay.
     */
    @Override
    public int getProjectileSpikeDelay() {
        return 0;
    }
}
