/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.IGameObjects;

/**
 *
 * @author faris
 */
public class Coin extends AbstractPickup {

    public Coin(IGameObjects gameObjects, double xCoordinate, double yCoordinate) {
        super(gameObjects, Life.class.getResourceAsStream("/data/gui/img/coin.png"), 50, 50, 
                xCoordinate, yCoordinate);
    }

    @Override
    public void handlePlayerCollision() {
        destroy();
        getGameObjects().addPoints(10);
    }
    
}
