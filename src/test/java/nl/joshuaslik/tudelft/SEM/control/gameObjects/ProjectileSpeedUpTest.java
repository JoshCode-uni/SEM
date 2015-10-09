package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import static org.junit.Assert.*;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.IPlayerModifier;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.PlayerBaseModifier;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.playerMods.ProjectileSpeedUp;

import org.junit.Test;

public class ProjectileSpeedUpTest {

	private final IPlayerModifier ipm = new PlayerBaseModifier();
	IPlayerModifier proj = new ProjectileSpeedUp().decorate(ipm);
	
	@Test
	public void testGetProjectileSpeedMultiplier() {
		assertEquals(1.3, proj.getProjectileSpeedMultiplier(), 0.001);
	}
	
	@Test
	public void testGetMoveSpeedMultiplier() {
		assertEquals(1, proj.getMoveSpeedMultiplier(), 0.001);
	}

	@Test
	public void testGetProjectileSpikeDelay() {
		assertEquals(0, proj.getProjectileSpikeDelay(), 0.001);
	}
}
