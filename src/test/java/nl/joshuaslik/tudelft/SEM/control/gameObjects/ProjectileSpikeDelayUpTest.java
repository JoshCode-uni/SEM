package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import static org.junit.Assert.*;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.IPlayerModifier;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.PlayerBaseModifier;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.playerMods.PlayerSpeedUp;

import org.junit.Test;

public class ProjectileSpikeDelayUpTest {

	private final IPlayerModifier ipm = new PlayerBaseModifier();
	IPlayerModifier delay = new PlayerSpeedUp().decorate(ipm);
	
	@Test
	public void testGetProjectileSpikeDelay() {
		assertEquals(0, delay.getProjectileSpikeDelay(), 0.001);
	}

	@Test
	public void testGetMoveSpeedMultiplier() {
		assertEquals(1.3, delay.getMoveSpeedMultiplier(), 0.001);
	}
	
	@Test
	public void testGetProjectileSpeedMultiplier() {
		assertEquals(1, delay.getProjectileSpeedMultiplier(), 0.001);
	}

}
