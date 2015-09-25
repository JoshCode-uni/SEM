/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.player.playerMods;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.player.AbstractPlayerModifierDecorator;

/**
 *
 * @author faris
 */
public class ProjectileSpeedUp extends AbstractPlayerModifierDecorator<ProjectileSpikeDelayUp> {

    @Override
    public double getProjectileSpeedMultiplier() {
        return 1.3 * super.getProjectileSpeedMultiplier();
    }
}