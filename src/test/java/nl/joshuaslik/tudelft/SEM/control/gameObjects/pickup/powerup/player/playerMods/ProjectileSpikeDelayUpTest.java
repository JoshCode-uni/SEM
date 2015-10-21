package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.playerMods;

import static org.junit.Assert.*;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.IPlayerModifier;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.PlayerBaseModifier;

import org.junit.Test;

/**
 * Test the projectile spike delay up class.
 * @author Faris
 */
public class ProjectileSpikeDelayUpTest {

    private final IPlayerModifier ipm = new PlayerBaseModifier();
    private IPlayerModifier delay = new ProjectileSpikeDelayUp().decorate(ipm);

    /**
     * Test the getProjectileSpikeDelay method.
     */
    @Test
    public void testGetProjectileSpikeDelay() {
        assertEquals(1.0, delay.getProjectileSpikeDelay(), 0.001);
    }

    /**
     * Test the getMoveSpeedMultiplier method.
     */
    @Test
    public void testGetMoveSpeedMultiplier() {
        assertEquals(1.0, delay.getMoveSpeedMultiplier(), 0.001);
    }

    /**
     * Test the getProjectileSpeedMultiplier method.
     */
    @Test
    public void testGetProjectileSpeedMultiplier() {
        assertEquals(1.0, delay.getProjectileSpeedMultiplier(), 0.001);
    }

}
