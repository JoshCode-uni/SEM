/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.bubble;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.IDecorator;

/**
 * The abstract bubble decorator.
 * @author faris
 */
public abstract class AbstractBubbleDecorator implements IBubbleModifier, IDecorator<IBubbleModifier> {

    private IBubbleModifier child;

    /**
     * Decorate this decorator with the modifier.
     * @param mod the modifier.
     * @return the decorated modifier.
     */
    @Override
    public IBubbleModifier decorate(IBubbleModifier mod) {
        child = (IBubbleModifier) mod;
        return this;
    }

    /**
     * Get the child of this decorator.
     * @return the child decorator.
     */
    protected IBubbleModifier getChild() {
        return child;
    }
}
