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
public class PlayerSpeedUp extends AbstractPlayerModifierDecorator<PlayerSpeedUp> {
    
    @Override
    public double getMoveSpeedMultiplier() {
        return 1.3 * getChild().getMoveSpeedMultiplier();
    }

    @Override
    public double getProjectileSpeedMultiplier() {
        return 1 * getChild().getProjectileSpeedMultiplier();
    }

    @Override
    public int getProjectileSpikeDelay() {
        return 0 + getChild().getProjectileSpikeDelay();
    }
}
