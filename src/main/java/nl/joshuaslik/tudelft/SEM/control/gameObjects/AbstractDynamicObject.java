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

    public AbstractDynamicObject(Node node) {
        this.node = node;
    }

    @Override
    public final void setIGameObjects(IGameObjects gameObjects) {
        this.gameObjects = gameObjects;
    }

    @Override
    public final Node getNode() {
        return node;
    }

    protected final IGameObjects getGameObjects() {
        return gameObjects;
    }
}
