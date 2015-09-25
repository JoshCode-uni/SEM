/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup;

import java.io.InputStream;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.AbstractPhysicsObject;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.IGameObjects;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.IUpdateable;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.Player;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.IImageViewObject;

/**
 * A pickup drops down in a straight line till it hits the ground. It also 
 * contains collision detection and will notify the player when it hits the
 * pickup.
 * @author faris
 */
public abstract class AbstractPickup extends AbstractPhysicsObject implements IUpdateable {
    
    private final IImageViewObject pickupImage;
    private final static double FALL_SPEED = 300;

    public AbstractPickup(IGameObjects gameObjects, InputStream is, double height,
            double width) {
        super(gameObjects);
        pickupImage = gameObjects.makeImage(is, height, width);
        pickupImage.setBounds(getGameObjects().getLeftBorder(),
                getGameObjects().getTopBorder(),
                getGameObjects().getRightBorder(),
                getGameObjects().getBottomBorder());
    }
    
    public abstract void handlePlayerCollision();

    @Override
    public void update(long nanoFrameTime) {
        pickupImage.setY(pickupImage.getStartX() + FALL_SPEED * nanoFrameTime /
                1_000_000_000);
        
        // check collision with player:
        Player pl = getGameObjects().getPlayer();
        if(pickupImage.intersects(pl.getImage())) {
            handlePlayerCollision();
        }
    }

    public IImageViewObject getPickupImage() {
        return pickupImage;
    }
}
