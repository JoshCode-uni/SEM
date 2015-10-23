/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.IDecorator;

/**
 * Abstract player decorator.
 * @author faris
 */
public abstract class AbstractPlayerDecorator implements IPlayerModifier, IDecorator<IPlayerModifier> {

    private IPlayerModifier child;

    /**
     * Decorate a modifier.
     * @param mod the modifier to decorate.
     * @return the decorated modifier.
     */
    @Override
    public IPlayerModifier decorate(IPlayerModifier mod) {
        child = (IPlayerModifier) mod;
        return this;
    }

    /**
     * Get the child modifier.
     * @return the child modifier.
     */
    protected IPlayerModifier getChild() {
        return child;
    }
}
