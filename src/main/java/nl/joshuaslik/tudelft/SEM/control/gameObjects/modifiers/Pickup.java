/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.modifiers;

import java.io.InputStream;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.AbstractPhysicsObject;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.ICollider;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.IDynamicObject;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.IGameObjects;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.IIntersectable;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.IUpdateable;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.Player;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.IImageViewObject;

/**
 * A pickup drops down in a straight line till it hits the ground. It also 
 * contains collision detection and will notify the player when it hits the
 * pickup.
 * @author faris
 */
public abstract class Pickup extends AbstractPhysicsObject implements IDynamicObject, IUpdateable, ICollider {
    
    private final IImageViewObject pickupImage;
    private final static double FALL_SPEED = 300;

    public Pickup(IGameObjects gameObjects, InputStream is, double height,
            double width) {
        super(gameObjects);
        pickupImage = gameObjects.makeImage(is, height, width);
        pickupImage.setBounds(getGameObjects().getLeftBorder(),
                getGameObjects().getTopBorder(),
                getGameObjects().getRightBorder(),
                getGameObjects().getBottomBorder());
    }
    
    /**
     * Handle picking up of the pickup by the player.
     * @param player the player.
     */
    protected abstract void getPickup(Player player);
    
    @Override
    public void checkCollision(IIntersectable obj2, long nanoFrameTime) {
        // don't check collision
        // player will check collision with us, nothing else can collide with us
    }

    @Override
    public void update(long nanoFrameTime) {
        pickupImage.setY(pickupImage.getStartX() + FALL_SPEED * nanoFrameTime /
                1_000_000_000);
    }
    
    public void pickedUp(Player player) {
        pickupImage.destroy();
        getGameObjects().removeObject(this);
        getPickup(player);
    }

    public IImageViewObject getPickupImage() {
        return pickupImage;
    }
}
