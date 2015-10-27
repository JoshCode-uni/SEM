/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.bubble.bubbleMods;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.bubble.AbstractBubbleDecorator;

/**
 * Slow down a bubble.
 * @author faris
 */
public class BubbleSlowdown extends AbstractBubbleDecorator {

    /**
     * Lower the speed.
     * @return a lower speed multiplier.
     */
    @Override
    public double getBubbleSpeedMultiplier() {
        return 0.5 * getChild().getBubbleSpeedMultiplier();
    }
}
