/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.IGameObjects;

/**
 * A pickup which grants the player an extra life.
 * @author faris
 */
public class Life extends AbstractPickup {

    /**
     * Create a life.
     * @param gameObjects the gameobjects.
     * @param xCoordinate the x coordinate.
     * @param yCoordinate the y coordinate. 
     */
    public Life(IGameObjects gameObjects, double xCoordinate, double yCoordinate) {
        super(gameObjects, Life.class.getResourceAsStream("/data/gui/img/life2.png"), 50,
                48.5, xCoordinate, yCoordinate);
    }

    /**
     * Handle the collision with the player.
     */
    @Override
    public void handlePlayerCollision() {
        destroy();
        getGameObjects().addLife();
    }

}
