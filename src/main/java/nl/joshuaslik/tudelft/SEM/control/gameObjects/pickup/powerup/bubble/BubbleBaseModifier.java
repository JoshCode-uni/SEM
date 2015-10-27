/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.bubble;

/**
 * The base modifier of all bubbles.
 *
 * @author faris
 */
public class BubbleBaseModifier implements IBubbleModifier {

    /**
     * Default speed modifier.
     *
     * @return the default speed modifier.
     */
    @Override
    public double getBubbleSpeedMultiplier() {
        return 1.0;
    }
}
