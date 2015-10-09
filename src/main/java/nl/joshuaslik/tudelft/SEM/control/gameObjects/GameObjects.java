/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import nl.joshuaslik.tudelft.SEM.control.IDraw;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.PickupGenerator;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.IModifier;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.bubble.AbstractBubbleModifierDecorator;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.AbstractPlayerModifierDecorator;
import nl.joshuaslik.tudelft.SEM.control.viewController.GameController;
import nl.joshuaslik.tudelft.SEM.control.viewController.IKeyboard;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ICircleViewObject;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.IImageViewObject;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ILineViewObject;
import nl.joshuaslik.tudelft.SEM.model.container.Levels;
import nl.joshuaslik.tudelft.SEM.model.container.Point;

/**
 * Game objects stores all objects of a level and updates them. It also keeps
 * track of other things, like if there are any bubbles left and the score.
 *
 * @author faris
 */
public class GameObjects implements IUpdateable, IGameObjects {

    private final ArrayList<IUpdateable>        updateableObjects     = new ArrayList<>();
    private final ArrayList<IPrepareUpdateable> prepUpdateableObjects = new ArrayList<>();
    private final ArrayList<ICollider>          colliderObjects       = new ArrayList<>();
    private final ArrayList<IIntersectable>     intersectableObjects  = new ArrayList<>();

    private final ArrayList<PhysicsObject> addObjectBuffer    = new ArrayList<>();
    private final ArrayList<PhysicsObject> removeObjectBuffer = new ArrayList<>();

    private final PickupGenerator   pickupGenerator = new PickupGenerator((IGameObjects) this);
    private final ArrayList<Bubble> bubbles         = new ArrayList<>();
    private Player player;

    private boolean    hasProjectile = false;
    private Projectile projectile    = null;
    private double topBorder, rightBorder, bottomBorder, leftBorder;

    private int score = 0;

    private final IDraw draw;

    /**
     * Construct all required objects for the given level.
     *
     * @param draw         interface to the drawing class which allows us to draw the
     *                     game objects.
     * @param level        the current level.
     * @param topBorder    y value of the top border.
     * @param rightBorder  x value of the right border.
     * @param bottomBorder y value of the bottom border.
     * @param leftBorder   x value of the left border.
     * @param keyBoard
     */
    public GameObjects(IDraw draw, int level, double topBorder, double rightBorder, double bottomBorder, double leftBorder,
                       IKeyboard keyBoard) {
        this.draw = draw;
        initializeBorders(topBorder, rightBorder, bottomBorder, leftBorder);
        initializePlayer(keyBoard);
        initializeLevel(level);
        addBufferedDynamicObjects();
    }

    /**
     * Update the positions of all objects.
     *
     * @param nanoFrameTime the time of a frame in nanoseconds.
     */
    @Override
    public void update(long nanoFrameTime) {
        // only add/remove objects at the beginning of each update
        addBufferedDynamicObjects();
        removeBufferedDynamicObjects();

        // calculate next positions
        for (IPrepareUpdateable e : prepUpdateableObjects) {
            e.prepareUpdate(nanoFrameTime);
        }

        // check for collisions
        for (ICollider collider : colliderObjects) {
            for (IIntersectable intersectable : intersectableObjects)
                if (intersectable != collider)
                    collider.checkCollision(intersectable, nanoFrameTime);
        }

        // update positions based on the calculated positions (which might've
        // been changed by a collision)
        for (IUpdateable e : updateableObjects) {
            e.update(nanoFrameTime);
        }
    }

    /**
     * Initialize the borders of the game.
     *
     * @param topBorder    y value of the top border.
     * @param rightBorder  x value of the right border.
     * @param bottomBorder y value of the bottom border.
     * @param leftBorder   x value of the left border.
     */
    private void initializeBorders(double topBorder, double rightBorder, double bottomBorder, double leftBorder) {

        // add top, left, right and bottom lines
        Line top = new Line((IGameObjects) this, leftBorder, topBorder, rightBorder, topBorder);

        Line left = new Line((IGameObjects) this, leftBorder, topBorder, leftBorder, bottomBorder);

        Line right = new Line((IGameObjects) this, rightBorder, topBorder, rightBorder, bottomBorder);

        Line bottom = new Line((IGameObjects) this, leftBorder, bottomBorder, rightBorder, bottomBorder);

        addObject(top);
        addObject(left);
        addObject(right);
        addObject(bottom);

        setGameBounds(topBorder, rightBorder, bottomBorder, leftBorder);
    }

    /**
     * Initialize the player.
     *
     * @param scene the scene of the game (to add a keylistener to).
     */
    private void initializePlayer(IKeyboard keyBoard) {
        InputStream is;
        try {
            is = getClass().getResource("/data/gui/img/penguin.png").openStream();
        } catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, "Couldn't load player image", ex);
            return;
        }

        player = new Player((IGameObjects) this, is, keyBoard);
        addObject(player);
    }

    /**
     * Initialize the level.
     *
     * @param level the level to initialize.
     */
    private void initializeLevel(int level) {
        for (PhysicsObject e : Levels.getLevelObjects(level, (IGameObjects) this)) {
            addObject(e);
        }
    }

    /**
     * Add a Dynamic Object to the game.
     *
     * @param object the Dynamic Object to add to the scene.
     */
    @Override
    public void addObject(PhysicsObject object) {
        addObjectBuffer.add(object);
    }

    /**
     * Remove a Dynamic Object from the game.
     *
     * @param object the Dynamic Object to remove from the game.
     */
    @Override
    public void removeObject(PhysicsObject object) {
        removeObjectBuffer.add(object);
    }

    /**
     * Add all buffered dynamic objects to the scene.
     */
    private void addBufferedDynamicObjects() {
        for (PhysicsObject object : addObjectBuffer) {

            if (object instanceof IUpdateable) {
                updateableObjects.add((IUpdateable) object);
            }
            if (object instanceof IPrepareUpdateable) {
                prepUpdateableObjects.add((IPrepareUpdateable) object);
            }
            if (object instanceof IIntersectable) {
                intersectableObjects.add((IIntersectable) object);
            }
            if (object instanceof ICollider) {
                colliderObjects.add((ICollider) object);
            }

            if (object instanceof Bubble) {
                bubbles.add((Bubble) object);
            }
        }
        addObjectBuffer.clear();
    }

    /**
     * Remove all buffered dynamic objects from the game.
     */
    private void removeBufferedDynamicObjects() {
        for (PhysicsObject object : removeObjectBuffer) {

            if (object instanceof IUpdateable) {
                updateableObjects.remove((IUpdateable) object);
            }
            if (object instanceof IPrepareUpdateable) {
                prepUpdateableObjects.remove((IPrepareUpdateable) object);
            }
            if (object instanceof IIntersectable) {
                intersectableObjects.remove((IIntersectable) object);
            }
            if (object instanceof ICollider) {
                colliderObjects.remove((ICollider) object);
            }

            if (object instanceof Bubble) {
                bubbles.remove((Bubble) object);
                score += 10;
            }
        }
        removeObjectBuffer.clear();
    }

    /**
     * Set the bounds of the game.
     *
     * @param top    min y value.
     * @param right  max x value.
     * @param bottom max y value.
     * @param left   min x value.
     */
    private void setGameBounds(double top, double right, double bottom, double left) {
        topBorder = top;
        rightBorder = right;
        bottomBorder = bottom;
        leftBorder = left;
    }

    /**
     * Get the min y value.
     *
     * @return min y value.
     */
    @Override
    public double getTopBorder() {
        return topBorder;
    }

    /**
     * Get the maximum x value.
     *
     * @return max x value.
     */
    @Override
    public double getRightBorder() {
        return rightBorder;
    }

    /**
     * Get the maximum y value.
     *
     * @return max y value.
     */
    @Override
    public double getBottomBorder() {
        return bottomBorder;
    }

    /**
     * Get the minimum x value.
     *
     * @return min x value.
     */
    @Override
    public double getLeftBorder() {
        return leftBorder;
    }

    /**
     * Get the score achieved in this level.
     *
     * @return the score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Check if all bubbles are destroyed.
     *
     * @return if all bubbles are destroyed.
     */
    public boolean allBubblesDestroyed() {
        return bubbles.isEmpty();
    }

    /**
     * Check if the game currently has a spawned projectile.
     *
     * @return if the game currently has a spawned projectile.
     */
    @Override
    public boolean hasProjectile() {
        return hasProjectile;
    }

    /**
     * Handle event: player died.
     */
    @Override
    public void playerDied() {
        draw.playerDied();
    }

    /**
     * Add a projectile to the game.
     *
     * @param projectile the projectile to add.
     */
    @Override
    public void addProjectile(Projectile projectile) {
        addObject(projectile);
        this.projectile = projectile;
        hasProjectile = true;
    }

    /**
     * Remove the projectile from the game.
     */
    @Override
    public void removeProjectile() {
        removeObject(projectile);
        this.projectile = null;
        hasProjectile = false;
    }

    /**
     * Create a circle in the view.
     *
     * @param centerX the x coordinate of the center of the circle.
     * @param centerY the y coordinate of the center of the circle.
     * @param radius  the radius of the circle.
     * @return the interface of the circle view object.
     */
    @Override
    public ICircleViewObject makeCircle(double centerX, double centerY, double radius) {
        return draw.makeCircle(centerX, centerY, radius);
    }

    /**
     * Create a line in the view.
     *
     * @param startX the x coordinate of the start point of the line.
     * @param startY the y coordinate of the start point of the line.
     * @param endX   the x coordinate of the end point of the line.
     * @param endY   the y coordinate of the end point of the line.
     * @return the interface of the line view object.
     */
    @Override
    public ILineViewObject makeLine(double startX, double startY, double endX, double endY) {
        return draw.makeLine(startX, startY, endX, endY);
    }

    /**
     * Create an image in the view.
     *
     * @param is     the input stream of the image.
     * @param height the height of the image.
     * @param width  the width of the image.
     * @return the interface of the image view object.
     */
    @Override
    public IImageViewObject makeImage(InputStream is, double height, double width) {
        return draw.makeImage(is, width, height);
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void handleModifierCollision(IModifier mod, boolean isPlayerPickup, boolean isBubblePickup) {
        if (isPlayerPickup) {
            player.addModifier((AbstractPlayerModifierDecorator) mod);
        } else if (isBubblePickup) {
            for (Bubble b : bubbles)
                b.addModifier((AbstractBubbleModifierDecorator) mod);
        }
    }

    @Override
    public void handleBubbleSplit(Point p) {
        pickupGenerator.generatePickup(p);
    }

    @Override
    public void addPoints(int points) {
        score += points;
    }

    @Override
    public void addLife() {
        draw.addLife();
    }

    @Override
    public int bubblesLeft() {
        return bubbles.size();
    }

    //Methods for testing purposes

    GameObjects(Boolean testing, IDraw draw, int level, double topBorder, double rightBorder, double bottomBorder, double leftBorder,
                IKeyboard keyBoard) {
        this.draw = draw;
        addBufferedDynamicObjects();
    }

    void addBubbles(Bubble bubble) {
        bubbles.add(bubble);
    }

    ArrayList<IPrepareUpdateable> getPrepareUpdateable() {
        return prepUpdateableObjects;
    }

    ArrayList<IUpdateable> getUpdateable() {
        return updateableObjects;
    }

    ArrayList<ICollider> getCollider() {
        return colliderObjects;
    }

    ArrayList<IIntersectable> getIntersectable() {
        return intersectableObjects;
    }
}
