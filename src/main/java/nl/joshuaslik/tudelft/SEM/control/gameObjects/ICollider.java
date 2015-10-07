/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

/**
 *
 * @author faris
 */
public interface ICollider extends IPhysicsObject {

    /**
     * Check if you collide with the object (and change directions accordingly).
     *
     * @param obj2 the object to check collision with.
     * @param nanoFrameTime the framerate (nanoseconds/frame)
     */
    public void checkCollision(final IIntersectable obj2, final long nanoFrameTime);
}
