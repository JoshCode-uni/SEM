/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.bubble.bubbleMods;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.bubble.BubbleBaseModifier;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.bubble.IBubbleModifier;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Test the bubble slow down class.
 * @author Faris
 */
public class BubbleSlowdownTest {

    private final IBubbleModifier bubbleBaseMod = new BubbleBaseModifier();

    /**
     * Test of getBubbleSpeedMultiplier method, of class BubbleSlowdown.
     */
    @Test
    public void testGetBubbleSpeedMultiplierSingleInstance() {
        IBubbleModifier slowdownOnce = new BubbleSlowdown().decorate(bubbleBaseMod);
        assertEquals(0.5, slowdownOnce.getBubbleSpeedMultiplier(), 0.001);
    }

    /**
     * Test of getBubbleSpeedMultiplier method, of class BubbleSlowdown.
     */
    @Test
    public void testGetBubbleSpeedMultiplierMultipleSlowdowns() {
        IBubbleModifier slowdownOnce = new BubbleSlowdown().decorate(bubbleBaseMod);
        slowdownOnce = new BubbleSlowdown().decorate(slowdownOnce);
        slowdownOnce = new BubbleSlowdown().decorate(slowdownOnce);
        assertEquals(0.5 * 0.5 * 0.5, slowdownOnce.getBubbleSpeedMultiplier(), 0.001);
    }

}
