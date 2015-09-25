/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.Powerup;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.EnumPowerupTypes;
import java.util.Random;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.IGameObjects;
import nl.joshuaslik.tudelft.SEM.model.container.Point;

/**
 *
 * @author faris
 */
public class PickupGenerater {

    private final IGameObjects gameObjects;

    public PickupGenerater(IGameObjects gameObjects) {
        this.gameObjects = gameObjects;
    }

    public void generatePickup(Point p) {
        Random rand = new Random();

         //50% chance to create a pickup:
        if (!rand.nextBoolean()) {
            return;
        }

        // 40% chance for a pickup and 60% chance for a powerup:
        if (rand.nextDouble() < 0.6) {
             //create powerup
            Powerup powerup = new Powerup(gameObjects, EnumPowerupTypes.getRandomPowerup(),
            p.getxPos(), p.getyPos());
            gameObjects.addObject(powerup);
        } else {
            // create pickup
        }
    }
}
