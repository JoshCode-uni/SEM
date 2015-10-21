/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.IGameObjects;

/**
 * A coin which gives 10 points upon collision.
 * @author faris
 */
public class Coin extends AbstractPickup {

    /**
     * Create a goin.
     * @param gameObjects the game objects.
     * @param xCoordinate the x coordinate.
     * @param yCoordinate the y coordinate.
     */
    public Coin(IGameObjects gameObjects, double xCoordinate, double yCoordinate) {
        super(gameObjects, Life.class.getResourceAsStream("/data/gui/img/coin.png"), 50, 50, xCoordinate, yCoordinate);
    }

    /**
     * Handle player collision.
     */
    @Override
    public void handlePlayerCollision() {
        destroy();
        getGameObjects().addPoints(10);
    }

}
