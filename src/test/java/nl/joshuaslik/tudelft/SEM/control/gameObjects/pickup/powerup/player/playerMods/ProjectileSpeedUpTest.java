package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.playerMods;

import static org.junit.Assert.*;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.IPlayerModifier;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.PlayerBaseModifier;

import org.junit.Test;

/**
 * Test the projectile speed up class.
 * @author Faris
 */
public class ProjectileSpeedUpTest {

    private final IPlayerModifier ipm = new PlayerBaseModifier();
    IPlayerModifier proj = new ProjectileSpeedUp().decorate(ipm);

    /**
     * Test the getProjectileSpeedMultiplier method.
     */
    @Test
    public void testGetProjectileSpeedMultiplier() {
        assertEquals(1.3, proj.getProjectileSpeedMultiplier(), 0.001);
    }

    /**
     * Test the getMoveSpeedMultiplier method.
     */
    @Test
    public void testGetMoveSpeedMultiplier() {
        assertEquals(1, proj.getMoveSpeedMultiplier(), 0.001);
    }

    /**
     * Test the getProjectileSpikeDelay method.
     */
    @Test
    public void testGetProjectileSpikeDelay() {
        assertEquals(0, proj.getProjectileSpikeDelay(), 0.001);
    }
}
