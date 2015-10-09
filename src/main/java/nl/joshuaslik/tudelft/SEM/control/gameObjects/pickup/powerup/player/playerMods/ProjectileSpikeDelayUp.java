/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.playerMods;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.AbstractPlayerModifierDecorator;

/**
 * @author faris
 */
public class ProjectileSpikeDelayUp extends AbstractPlayerModifierDecorator<ProjectileSpikeDelayUp> {

    @Override
    public int getProjectileSpikeDelay() {
        return 1 + getChild().getProjectileSpikeDelay();
    }

    @Override
    public double getMoveSpeedMultiplier() {
        return 1 * getChild().getMoveSpeedMultiplier();
    }

    @Override
    public double getProjectileSpeedMultiplier() {
        return 1 * getChild().getProjectileSpeedMultiplier();
    }
}
