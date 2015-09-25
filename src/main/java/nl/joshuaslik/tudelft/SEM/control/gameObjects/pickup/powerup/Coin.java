/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup;

import java.io.InputStream;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.IGameObjects;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.AbstractPickup;

/**
 *
 * @author faris
 */
public class Coin extends AbstractPickup {

    public Coin(IGameObjects gameObjects, InputStream is, double height, double width, double xCoordinate, double yCoordinate) {
        super(gameObjects, is, height, width, xCoordinate, yCoordinate);
    }

    @Override
    public void handlePlayerCollision() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
