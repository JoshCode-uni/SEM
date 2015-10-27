/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

/**
 * Defines the methods needed by an object which can collide with other objects.
 * @author faris
 */
interface ICollider {

    /**
     * Check if you collide with the object (and change directions accordingly).
     *
     * @param obj2 the object to check collision with.
     * @param nanoFrameTime the framerate (nanoseconds/frame)
     */
    void checkCollision(final IIntersectable obj2, final long nanoFrameTime);
}
