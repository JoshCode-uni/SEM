/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

/**
 * @author faris
 */
public interface IPrepareUpdateable extends PhysicsObject {
	
	/**
	 * Prepare for an update (if needed).
	 *
	 * @param nanoFrameTime the framerate (nanoseconds/frame)
	 */
	public void prepareUpdate(final long nanoFrameTime);
}
