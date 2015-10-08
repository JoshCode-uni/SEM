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
public abstract class AbstractPlayerDecorator implements IPlayerBaseModifier, IDecorator<IPlayerBaseModifier> {
	
	private IPlayerBaseModifier child;
	
        @Override
	public IPlayerBaseModifier decorate(IPlayerBaseModifier mod) {
		child = mod;
		return this;
	}
	
	public IPlayerBaseModifier getChild() {
		return child;
	}
}
