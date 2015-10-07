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
public interface ICollideReceiver extends IPhysicsObject, IIntersectable {

    /**
     * Notify a dynamic object of the fact that we collided with it.
     *
     * @param obj2 the dynamic object with which you collided.
     * @param nanoFrameTime the frame time in nano seconds.
     */
    public void collide(final ICollider obj2, final long nanoFrameTime);
}
