/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.bubble;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.IDecorator;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.IModifier;

/**
 * @param <T>
 * @author faris
 */
public abstract class AbstractBubbleModifierDecorator<T extends IBubbleModifier> implements IBubbleModifier, IDecorator<T> {

    private IBubbleModifier child;

    @Override
    public T decorate(IModifier mod) {
        child = (IBubbleModifier) mod;
        return (T) this;
    }

    public IBubbleModifier getChild() {
        return child;
    }
}
