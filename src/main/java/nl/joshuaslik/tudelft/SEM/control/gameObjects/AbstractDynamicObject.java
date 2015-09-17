/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import javafx.scene.Node;

/**
 * This class can be extended instead of IDynamicObject if no other abstract
 * classes have to be extended. It implements some of the functionality of the
 * IDynamicObject. This class is intended to be extended whenever possible, to
 * avoid duplicate code.
 *
 * @author faris
 */
public abstract class AbstractDynamicObject implements IDynamicObject {

    private IGameObjects gameObjects;
    private final Node node;

    /**
     * Construct this dynamic object.
     * @param node the node of this object.
     */
    public AbstractDynamicObject(Node node) {
        this.node = node;
    }

    /**
     * Set the game objects interface of the gameobjects class which contains
     * this object.
     * @param gameObjects the game objects interface.
     */
    @Override
    public final void setIGameObjects(IGameObjects gameObjects) {
        this.gameObjects = gameObjects;
    }

    /**
     * Get the node of this dynamic object.
     * @return the node of this dynamic object.
     */
    @Override
    public final Node getNode() {
        return node;
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
