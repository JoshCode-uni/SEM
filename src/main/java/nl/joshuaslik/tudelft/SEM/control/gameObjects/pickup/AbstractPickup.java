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
import nl.joshuaslik.tudelft.SEM.model.container.GameInfo;
import nl.joshuaslik.tudelft.SEM.model.container.PlayerMode;
import nl.joshuaslik.tudelft.SEM.utility.Time;

/**
 * A pickup drops down in a straight line till it hits the ground. It also contains collision
 * detection and will notify the player when it hits the pickup.
 *
 * @author faris
 */
public abstract class AbstractPickup extends AbstractPhysicsObject implements IUpdateable {

    private final IImageViewObject pickupImage;
    private final static double FALL_SPEED = 300;
    private double EXISTENCE_TIME = 5.0 * Time.SECOND_NANO;

    protected AbstractPickup(IGameObjects gameObjects, InputStream is, double height, double width,
            double xCoordinate, double yCoordinate) {
        super(gameObjects);
        pickupImage = gameObjects.makeImage(is, height, width);
        pickupImage.setBounds(getGameObjects().getLeftBorder(), getGameObjects().getTopBorder(),
                getGameObjects().getRightBorder(), getGameObjects().getBottomBorder());
        pickupImage.setX(xCoordinate);
        pickupImage.setY(yCoordinate);
    }

    /**
     * Abstract method which handles the collision with a player.
     */
    protected abstract void handlePlayerCollision();

    /**
     * Update the position.
     *
     * @param nanoFrameTime the frame time in ns.
     */
    @Override
    public void update(long nanoFrameTime) {
        EXISTENCE_TIME -= nanoFrameTime;
        if (EXISTENCE_TIME <= 0) {
            destroy();
        }
        pickupImage.setY(pickupImage.getStartY() + FALL_SPEED * nanoFrameTime / Time.SECOND_NANO);
        Player pl = getGameObjects().getPlayer();

        if (pl.intersectsWith(pickupImage)) {
            handlePlayerCollision();
            return;
        }
        if (GameInfo.getInstance().getPlayerMode().equals(PlayerMode.MULTI_PLAYER_COOP) || GameInfo.getInstance().getPlayerMode().equals(PlayerMode.MULTI_PLAYER_VERSUS)) {
            Player pl2 = getGameObjects().getPlayer2();
            if (pl2.intersectsWith(pickupImage)) {
                handlePlayerCollision();
            }
        }
    }

    /**
     * Get the image of the pickup.
     *
     * @return the image of the pickup.
     */
    public IImageViewObject getPickupImage() {
        return pickupImage;
    }

    /**
     * Get the relative x position of the bubble compared to the view.
     *
     * @return the relative x position of the bubble compared to the view.
     */
    public double getRelativeXPos() {
        double xPos = pickupImage.getStartX();
        return xPos / (getGameObjects().getRightBorder() - getGameObjects().getLeftBorder());
    }

    /**
     * Destroy the pickup.
     */
    public void destroy() {
        pickupImage.destroy();
        getGameObjects().removeObject(this);
    }
}
