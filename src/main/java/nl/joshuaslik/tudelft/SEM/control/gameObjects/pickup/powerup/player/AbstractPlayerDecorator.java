/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.IDecorator;


/**
 * 
 * @author faris
 */
public abstract class AbstractPlayerDecorator implements IPlayerModifier, IDecorator<IPlayerModifier> {
	
	private IPlayerModifier child;
	
        @Override
	public IPlayerModifier decorate(IPlayerModifier mod) {
		child = mod;
		return this;
	}
	
	public IPlayerModifier getChild() {
		return child;
	}
}
