/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

/**
 * Object which has physics (you can collide with it).
 *
 * @author faris
 */
public interface PhysicsObject {
    
    /**
     * Set the game object interface, which allows this class to interact via a
     * limited set of methods, with other game objects.
     * @param gameObjects the game objects interface.
     */
    public void setIGameObjects(IGameObjects gameObjects);

}
