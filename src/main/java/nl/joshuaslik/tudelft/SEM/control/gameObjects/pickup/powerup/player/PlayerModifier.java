/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player;

/**
 * @author faris
 */
public class PlayerModifier implements IPlayerBaseModifier {
	
	@Override
	public double getMoveSpeedMultiplier() {
		return 1.0;
	}
	
	@Override
	public double getProjectileSpeedMultiplier() {
		return 1.0;
	}
	
	@Override
	public int getProjectileSpikeDelay() {
		return 0;
	}
}
