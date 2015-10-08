/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

/**
 * Interface to be implemented by all objects which should be updated by the
 * GameLoop.
 *
 * @author faris
 */
public interface IUpdateable extends IPhysicsObject {
	
	/**
	 * Update the object.
	 *
	 * @param nanoFrameTime nanoFrameTime the framerate (nanoseconds/frame)
	 */
	public abstract void update(final long nanoFrameTime);
}
