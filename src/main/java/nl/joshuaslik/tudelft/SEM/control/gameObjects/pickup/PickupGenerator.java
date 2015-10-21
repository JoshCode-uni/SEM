/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup;

import java.util.Random;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.IGameObjects;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.EnumPowerupTypes;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.Powerup;
import nl.joshuaslik.tudelft.SEM.model.container.Point;

/**
 * Useful to create a random pickup.
 *
 * @author faris
 */
public class PickupGenerator {

    private final IGameObjects gameObjects;

    /**
     * Construct a pickup generator.
     * @param gameObjects the gameobjects.
     */
    public PickupGenerator(IGameObjects gameObjects) {
        this.gameObjects = gameObjects;
    }

    /**
     * Generate a random pickup.
     * @param p the point of the pickup
     */
    public void generatePickup(Point p) {
        generatePickup(p, new Random());
    }

    /**
     * Generate a random pickup.
     * @param p the point of the pickup.
     * @param rand an instace of random.
     */
    public void generatePickup(Point p, Random rand) {
        if (!rand.nextBoolean()) {
            return;
        }
        if (rand.nextDouble() < 0.6) {
            Powerup powerup = new Powerup(gameObjects, EnumPowerupTypes.getRandomPowerup(), p.getxPos(), p.getyPos());
            gameObjects.addObject(powerup);
        } else {
            if (rand.nextDouble() < 0.9) {
                Coin coin = new Coin(gameObjects, p.getxPos(), p.getyPos());
                gameObjects.addObject(coin);
            } else {
                Life life = new Life(gameObjects, p.getxPos(), p.getyPos());
                gameObjects.addObject(life);
            }
        }
    }
}
