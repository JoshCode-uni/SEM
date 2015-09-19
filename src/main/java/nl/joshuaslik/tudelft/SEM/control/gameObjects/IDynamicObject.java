/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

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

    /**
     * Notify a dynamic object of the fact that we collided with it.
     * @param obj2 the dynamic object with which you collided.
     * @param nanoFrameTime the frame time in nano seconds.
     */
    public void collide(final IDynamicObject obj2, final long nanoFrameTime);
}
