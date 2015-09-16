/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import nl.joshuaslik.tudelft.SEM.model.container.Vector;

/**
 * Interface to be implemented by all objects which can move.
 *
 * @author faris
 */
public interface IDynamicObject extends PhysicsObject, IUpdateable {

    /**
     * Check if you collide with the object (and change directions accordingly).
     *
     * @param obj2 the object to check collision with.
     * @param nanoFrameTime the framerate (nanoseconds/frame)
     */
    public void checkCollision(final PhysicsObject obj2, final long nanoFrameTime);
    //	public abstract void collide(final DynamicObject obj2, final long nanoFrameTime);

    /**
     * Get the speed of this object as a x/y vector.
     *
     * @return x/y vector representing the speed.
     */
    public Vector getSpeedVector();

    public void setIGameObjects(IGameObjects gameObjects);
}
