/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

/**
 * This class can be extended instead of IPhysicsObject if no other abstract
 * classes have to be extended. It implements some of the functionality of the
 * IPhysicsObject. This class is intended to be extended whenever possible, to
 * avoid duplicate code.
 *
 * @author faris
 */
public abstract class AbstractPhysicsObject implements IPhysicsObject {

    private final IGameObjects gameObjects;

    /**
     * Set the gameObject reference.
     *
     * @param gameObjects game objects interface.
     */
    public AbstractPhysicsObject(final IGameObjects gameObjects) {
        this.gameObjects = gameObjects;
    }

    /**
     * Get the game objects interface which allows you to perform a limited
     * amount of methods of the game objects class.
     *
     * @return the game objects interface.
     */
    protected final IGameObjects getGameObjects() {
        return gameObjects;
    }
}
