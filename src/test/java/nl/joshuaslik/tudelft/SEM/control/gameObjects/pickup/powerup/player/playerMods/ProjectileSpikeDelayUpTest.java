package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.playerMods;

import static org.junit.Assert.*;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.IPlayerModifier;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.PlayerBaseModifier;

import org.junit.Test;

public class ProjectileSpikeDelayUpTest {

    private final IPlayerModifier ipm = new PlayerBaseModifier();
    private IPlayerModifier delay = new ProjectileSpikeDelayUp().decorate(ipm);

    @Test
    public void testGetProjectileSpikeDelay() {
        assertEquals(1.0, delay.getProjectileSpikeDelay(), 0.001);
    }

    @Test
    public void testGetMoveSpeedMultiplier() {
        assertEquals(1.0, delay.getMoveSpeedMultiplier(), 0.001);
    }

    @Test
    public void testGetProjectileSpeedMultiplier() {
        assertEquals(1.0, delay.getProjectileSpeedMultiplier(), 0.001);
    }

}
