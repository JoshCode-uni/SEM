/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import java.io.InputStream;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.IPlayerModifier;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.PlayerModifier;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.AbstractPlayerModifierDecorator;
import nl.joshuaslik.tudelft.SEM.control.viewController.IKeyboard;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.IImageViewObject;
import nl.joshuaslik.tudelft.SEM.utility.GameLog;

/**
 * A class containing the position of the player. This class also controller the
 * player.
 *
 * @author faris
 */
public class Player extends AbstractPhysicsObject implements IUpdateable, ICollider  {

    private IPlayerModifier modifier = new PlayerModifier();
    private final IImageViewObject image;
    private final IKeyboard keyboard;
    private static final double MAX_SPEED = 300;

    /**
     * Create a player.
     * @param gameObjects
     * @param is
     * @param kb keyboard which controller the actions of the player.
     */
    public Player(IGameObjects gameObjects, InputStream is, IKeyboard kb) {
        super(gameObjects);
        
        image = getGameObjects().makeImage(is, 100, 100);
        image.setX((getGameObjects().getRightBorder() - 
                getGameObjects().getLeftBorder()) / 2.0);
        image.setY(getGameObjects().getBottomBorder() - 
                image.getHeight());
        keyboard = kb;
    }

    /**
     * Check if the player has collided with anything. Not implemented.
     *
     * @param obj2 object to check collision with
     * @param nanoFrameTime the framerate (nanoseconds/frame)
     */
    @Override
    public void checkCollision(IIntersectable obj2, long nanoFrameTime) {
        if (obj2 instanceof Bubble) {
            Bubble bubble = (Bubble) obj2;
            if (image.intersects(bubble.getCircleViewObject())) {
                getGameObjects().playerDied();
            }
        }
    }

    /**
     * Update the position of the player.
     *
     * @param nanoFrameTime the framerate (nanoseconds/frame)
     */
    @Override
    public void update(long nanoFrameTime) {
        if (keyboard.isMoveLeft() && getGameObjects().getLeftBorder() < image.getStartX()) {
            // move left
            image.setX(image.getStartX() + -MAX_SPEED * nanoFrameTime / 1_000_000_000 * getMoveSpeedMultiplier());
            image.setScaleX(1);
        } else if (keyboard.isMoveRight() && getGameObjects().getRightBorder() > image.getEndX()) {
            // move right
            image.setX(image.getStartX() + MAX_SPEED * nanoFrameTime / 1_000_000_000 * getMoveSpeedMultiplier());
            image.setScaleX(-1);
        }

        if (keyboard.isShoot() && !getGameObjects().hasProjectile()) {
            double bulletX = (image.getStartX() + image.getEndX()) / 2.0;
            double bulletY = image.getEndY();

            //shoot
            GameLog.addInfoLog("Player shoots at: (" + Double.toString(bulletX)
                    + ", " + Double.toString(bulletY) + ")");
            getGameObjects().addProjectile(
                    makeProjectile(getGameObjects(), bulletX, bulletY));
        }
    }

    /**
     * Method for testing purposes.
     * @return the max speed.
     */
    public static double getMAX_SPEED() {
        return MAX_SPEED;
    }
    
    /**
     * Method for testing puproses. Avoiding object creation.
     * @param gameObjects game object.
     * @param startX start X.
     * @param startY start Y.
     * @return the requested projectile.
     */
    public Projectile makeProjectile(IGameObjects gameObjects, double startX, 
            double startY){
        return new Projectile(gameObjects, startX, startY, getProjectileSpeedMultiplier(), 
            getProjectileSpikeDelay());
    }

    public IImageViewObject getImage() {
        return image;
    }
    
    public void addModifier(AbstractPlayerModifierDecorator newmod) {
        modifier = newmod.decorate(modifier);
    }
    
    private double getMoveSpeedMultiplier() {
        return modifier.getMoveSpeedMultiplier();
    }
    
    private double getProjectileSpeedMultiplier() {
        return modifier.getProjectileSpeedMultiplier();
    }
    
    private int getProjectileSpikeDelay() {
        return modifier.getProjectileSpikeDelay();
    }
}
