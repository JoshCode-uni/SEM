/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import nl.joshuaslik.tudelft.SEM.model.container.Vector;

/**
 * @author faris
 */
public interface DynamicObject extends PhysicsObject, IUpdateable {
	
	public abstract void prepareUpdate(final long nanoFrameTime);
	public abstract void checkCollision(final PhysicsObject obj2, final long nanoFrameTime);
//	public abstract void collide(final DynamicObject obj2, final long nanoFrameTime);
	
	public abstract Vector getSpeedVector();
}
