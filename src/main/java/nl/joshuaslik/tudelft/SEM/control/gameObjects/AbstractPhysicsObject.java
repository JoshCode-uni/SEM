/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import utility.GameLog;


/**
 * This class can be extended instead of IPhysicsObject if no other abstract
 * classes have to be extended. It implements some of the functionality of the
 * IPhysicsObject. This class is intended to be extended whenever possible, to
 * avoid duplicate code.
 *
 * @author faris
 */
public abstract class AbstractPhysicsObject implements PhysicsObject {

    private final IGameObjects gameObjects;

    /**
     * Set the gameObject reference.
     * @param gameObjects game objects interface.
     */
    public AbstractPhysicsObject(IGameObjects gameObjects) {
        this.gameObjects = gameObjects;
    }
    
    /**
     * Set the game objects interface of the gameobjects class which contains
     * this object.
     * @param gameObjects the game objects interface.
     */
    @Override
    public final void setIGameObjects(IGameObjects gameObjects) {
        GameLog.addErrorLog("Method call: setIGameObjects in class "
                + "AbstractPhysicsObject. \n"
                + "This method shouldn't be "
                + "called. Only classes directly implementing the interface "
                + "\"Physics Objects\" may use it.");
        throw new UnsupportedOperationException("This method shouldn't be "
                + "called. Only classes directly implementing the interface "
                + "\"Physics Objects\" may use it.");
    }

    /**
     * Get the game objects interface which allows you to perform a limited
     * amount of methods of the game objects class.
     * @return the game objects interface.
     */
    protected final IGameObjects getGameObjects() {
        return gameObjects;
    }
}
