/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import utility.GameLog;

/**
 *
 * @author faris
 */
public abstract class AbstractPhysicsObject implements PhysicsObject {

    private IGameObjects gameObjects;

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
