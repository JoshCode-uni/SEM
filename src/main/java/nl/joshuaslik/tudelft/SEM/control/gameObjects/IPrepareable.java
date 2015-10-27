/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

/**
 * Interface defining the methods needed for all prepareable objects.
 * @author faris
 */
interface IPrepareable extends IPhysicsObject {

    /**
     * Prepare for an update (if needed).
     *
     * @param nanoFrameTime the framerate (nanoseconds/frame)
     */
    void prepare(final long nanoFrameTime);
}
