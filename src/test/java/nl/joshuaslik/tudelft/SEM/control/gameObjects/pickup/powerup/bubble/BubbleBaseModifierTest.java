/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.bubble;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Test the bubble base modifier class.
 * @author Faris
 */
public class BubbleBaseModifierTest {

    /**
     * Test of getBubbleSpeedMultiplier method, of class BubbleBaseModifier.
     */
    @Test
    public void testGetBubbleSpeedMultiplier() {
        IBubbleModifier bubbleBaseMod = new BubbleBaseModifier();
        assertEquals(1.0, bubbleBaseMod.getBubbleSpeedMultiplier(), 0.001);
    }

}
