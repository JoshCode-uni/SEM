/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import nl.joshuaslik.tudelft.SEM.model.container.IntersectionPoint;
import nl.joshuaslik.tudelft.SEM.model.container.Point;
import utility.GameLog;

/**
 * A class containing the position of the player. This class also controller the
 * player.
 *
 * @author faris
 */
public class Player extends AbstractDynamicObject {

    private final ImageView image;
    private final Keyboard keyboard;
    private static final double MAX_SPEED = 200;

    private int lives;

    /**
     * Create a player.
     *
     * @param img image of the player.
     * @param kb keyboard which controller the actions of the player.
     */
    public Player(ImageView img, Keyboard kb) {
        super(img);
        image = img;
        keyboard = kb;

        lives = 1;
    }

    /**
     * Prepare for updating.
     *
     * @param nanoFrameTime the framerate (nanoseconds/frame)
     */
    @Override
    public void prepareUpdate(long nanoFrameTime) {
        GameLog.addWarningLog("Called non-implemented method: " + 
                "prepareUpdate in class Player");
        // no preparation needed
    }

    /**
     * Check if the player has colldided with anything. Not implemented.
     *
     * @param obj2 object to check collision with
     * @param nanoFrameTime the framerate (nanoseconds/frame)
     */
    @Override
    public void checkCollision(PhysicsObject obj2, long nanoFrameTime) {
        GameLog.addWarningLog("Called non-implemented method: " + 
                "checkCollision in class Player");
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Update the position of the player.
     *
     * @param nanoFrameTime the framerate (nanoseconds/frame)
     */
    @Override
    public void update(long nanoFrameTime) {
        if (keyboard.isMoveLeft() && getGameObjects().getLeftBorder() < image.getLayoutBounds().getMinX()) {
            // move left
            image.setX(image.getX() + -MAX_SPEED * nanoFrameTime / 1_000_000_000);
            image.setScaleX(1);
        } else if (keyboard.isMoveRight() && getGameObjects().getRightBorder() > image.getLayoutBounds().getMaxX()) {
            // move right
            image.setX(image.getX() + MAX_SPEED * nanoFrameTime / 1_000_000_000);
            image.setScaleX(-1);
        }

        if (checkBubbleCollision()) {
            if (lives > 0) {
                System.out.println("Player loses life");
                lives--;
                // Reset level
            } else {
                System.out.println("Player dies");
                getGameObjects().playerDied();
            }
        }

        if (keyboard.isShoot() && !getGameObjects().hasProjectile()) {
            double bulletX = (image.getX() + image.getLayoutBounds().getMaxX()) / 2.0;
            double bulletY = image.getY() + image.getLayoutBounds().getHeight();
            
            //shoot
            GameLog.addInfoLog("Player shoots at: (" + Double.toString(bulletX)
                    + Double.toString(bulletY) + ")");
            getGameObjects().addProjectile(new Projectile(bulletX, bulletY));
        }
    }

    /**
     * Check if we collided with a bubble.
     *
     * @return if we collided with a bubble.
     */
    public boolean checkBubbleCollision() {
        ArrayList<PhysicsObject> objects = ((GameObjects) getGameObjects()).getAllObjects();//GameLoop.getAllObjects(); // <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< MUST BE CHANGED

        for (PhysicsObject o : objects) {
            if (o instanceof Bubble) {
                if (image.intersects(o.getNode().getLayoutBounds())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Not implemented.
     * @param p -
     * @return intersection -
     */
    @Override
    public IntersectionPoint getClosestIntersection(Point p) {
        GameLog.addWarningLog("Called non-implemented method: " + 
                "getClosestIntersection in class Player");
        // player is special: getClosestIntersection won't be called
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Never called, because player is special.
     * @param obj2 -
     * @param nanoFrameTime -
     */
    @Override
    public void collide(IDynamicObject obj2, long nanoFrameTime) {
        GameLog.addWarningLog("Called non-implemented method: " + 
                "collide in class Player");
        // player is special: collide won't be called
    }
}
