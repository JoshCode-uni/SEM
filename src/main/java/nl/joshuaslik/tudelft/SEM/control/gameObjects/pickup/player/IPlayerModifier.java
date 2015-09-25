/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.player;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.IModifier;

/**
 *
 * @author faris
 */
public interface IPlayerModifier extends IModifier {

    public double getMoveSpeedMultiplier();

    public double getProjectileSpeedMultiplier();

    public int getProjectileSpikeDelay();
}
