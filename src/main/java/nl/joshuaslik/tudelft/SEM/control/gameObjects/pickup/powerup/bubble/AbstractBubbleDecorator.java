/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.bubble;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.IDecorator;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.IModifier;

/**
 * 
 * @author faris
 */
public abstract class AbstractBubbleDecorator implements IBubbleModifier, IDecorator<IBubbleModifier> {

    private IBubbleModifier child;

    @Override
    public IBubbleModifier decorate(IBubbleModifier mod) {
        child = (IBubbleModifier) mod;
        return this;
    }

    public IBubbleModifier getChild() {
        return child;
    }
}
