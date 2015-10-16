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
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.bubble.AbstractBubbleDecorator;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.AbstractPlayerDecorator;
import nl.joshuaslik.tudelft.SEM.control.viewController.GameController;
import nl.joshuaslik.tudelft.SEM.control.viewController.IKeyboard;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ICircleViewObject;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.IImageViewObject;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ILineViewObject;
import nl.joshuaslik.tudelft.SEM.model.container.GameInfo;
import nl.joshuaslik.tudelft.SEM.model.container.Levels;
import nl.joshuaslik.tudelft.SEM.model.container.PlayerMode;
import nl.joshuaslik.tudelft.SEM.model.container.Point;
import org.apache.commons.lang3.ClassUtils;

/**
 * Game objects stores all objects of a level and updates them. It also keeps
 * track of other things, like if there are any bubbles left and the score.
 *
 * @author faris
 */
public class GameObjects implements IUpdateable, IGameObjects {

    private final ArrayList<IUpdateable> updateableObjects = new ArrayList<>();
    private final ArrayList<IPrepareable> prepUpdateableObjects = new ArrayList<>();
    private final ArrayList<ICollider> colliderObjects = new ArrayList<>();
    private final ArrayList<IIntersectable> intersectableObjects = new ArrayList<>();

    private final ArrayList<IPhysicsObject> addObjectBuffer = new ArrayList<>();
    private final ArrayList<IPhysicsObject> removeObjectBuffer = new ArrayList<>();

    private final PickupGenerator pickupGenerator = new PickupGenerator((IGameObjects) this);
    private final ArrayList<Bubble> bubbles = new ArrayList<>();
    private Player player;
    private Player player2;

    private boolean p1HasProjectile = false;
    private boolean p2HasProjectile = false;
    private boolean isActive = true;
    private Projectile projectile = null;
    private Projectile projectile2 = null;
    private double topBorder, rightBorder, bottomBorder, leftBorder;

    private int score = 0;

    private final IDraw draw;

    /**
     * Construct all required objects for the given level.
     *
     * @param draw         interface to the drawing class which allows us to draw the
     *                     game objects.
     * @param topBorder    y value of the top border.
     * @param rightBorder  x value of the right border.
     * @param bottomBorder y value of the bottom border.
     * @param leftBorder   x value of the left border.
     * @param keyBoard
     */
    public GameObjects(final IDraw draw, final double topBorder, final double rightBorder, final double bottomBorder,
                       final double leftBorder, final IKeyboard keyBoard) {
        this.draw = draw;
        initializeBorders(topBorder, rightBorder, bottomBorder, leftBorder);
        initializePlayer(keyBoard);
        initializeLevel();
        addBufferedDynamicObjects();
    }

    /**
     * Update the positions of all objects.
     *
     * @param nanoFrameTime the time of a frame in nanoseconds.
     */
    @Override
    public void update(final long nanoFrameTime) {
        // only add/remove objects at the beginning of each update
        addBufferedDynamicObjects();
        removeBufferedDynamicObjects();

        // calculate next positions
        for (IPrepareable e : prepUpdateableObjects) {
            e.prepare(nanoFrameTime);
        }

        // check for collisions
        for (ICollider collider : colliderObjects) {
            for (IIntersectable intersectable : intersectableObjects) {
                if (intersectable != collider) {
                    collider.checkCollision(intersectable, nanoFrameTime);
                }
            }
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
    private void initializeBorders(final double topBorder, final double rightBorder, final double bottomBorder, final double leftBorder) {

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
     */
    private void initializePlayer(final IKeyboard keyBoard) {
        InputStream is;
        InputStream is2;
        try {
            is = getClass().getResource("/data/gui/img/penguin.png").openStream();
            is2 = getClass().getResource("/data/gui/img/penguin.png").openStream();
        } catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, "Couldn't load player image", ex);
            return;
        }
        GameInfo.getInstance().setPlayerMode(PlayerMode.MULTI_PLAYER_VERSUS);
       	player = new Player((IGameObjects) this, is, keyBoard, false);
       	addObject(player);
        if (GameInfo.getInstance().getPlayerMode().equals(PlayerMode.MULTI_PLAYER_COOP)||GameInfo.getInstance().getPlayerMode().equals(PlayerMode.MULTI_PLAYER_VERSUS)){
        	
        	player2 = new Player((IGameObjects) this, is2, keyBoard, true);
        	addObject(player2);
        	
        }
    }

    /**
     * Initialize the level.
     *
     * @param level the level to initialize.
     */
    private void initializeLevel() {
        for (IPhysicsObject e : Levels.getLevelObjects((IGameObjects) this)) {
            addObject(e);
        }
    }

    /**
     * Add a Dynamic Object to the game.
     *
     * @param object the Dynamic Object to add to the scene.
     */
    @Override
    public void addObject(final IPhysicsObject object) {
        addObjectBuffer.add(object);
    }

    /**
     * Remove a Dynamic Object from the game.
     *
     * @param object the Dynamic Object to remove from the game.
     */
    @Override
    public void removeObject(final IPhysicsObject object) {
        removeObjectBuffer.add(object);
    }

    /**
     * Add all buffered dynamic objects to the scene.
     */
    private void addBufferedDynamicObjects() {
        for (IPhysicsObject object : addObjectBuffer) {

            if(object == null)
                continue;
            
            if (ClassUtils.getAllInterfaces(object.getClass()).contains(IUpdateable.class)) {
                updateableObjects.add((IUpdateable) object);
            }
            if (ClassUtils.getAllInterfaces(object.getClass()).contains(IPrepareable.class)) {
                prepUpdateableObjects.add((IPrepareable) object);
            }
            if (ClassUtils.getAllInterfaces(object.getClass()).contains(IIntersectable.class)) {
                intersectableObjects.add((IIntersectable) object);
            }
            if (ClassUtils.getAllInterfaces(object.getClass()).contains(ICollider.class)) {
                colliderObjects.add((ICollider) object);
            }

            if (Bubble.class.isAssignableFrom(object.getClass())) {
                bubbles.add((Bubble) object);
            }
        }
        addObjectBuffer.clear();
    }

    /**
     * Remove all buffered dynamic objects from the game.
     */
    private void removeBufferedDynamicObjects() {
        for (IPhysicsObject object : removeObjectBuffer) {

            if(object == null)
                continue;
            
            if (ClassUtils.getAllInterfaces(object.getClass()).contains(IUpdateable.class)) {
                updateableObjects.remove((IUpdateable) object);
            }
            if (ClassUtils.getAllInterfaces(object.getClass()).contains(IPrepareable.class)) {
                prepUpdateableObjects.remove((IPrepareable) object);
            }
            if (ClassUtils.getAllInterfaces(object.getClass()).contains(IIntersectable.class)) {
                intersectableObjects.remove((IIntersectable) object);
            }
            if (ClassUtils.getAllInterfaces(object.getClass()).contains(ICollider.class)) {
                colliderObjects.remove((ICollider) object);
            }

            if (Bubble.class.isAssignableFrom(object.getClass())) {
                bubbles.remove((Bubble) object);
           //     score += 10;
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
    private void setGameBounds(final double top, final double right, final double bottom, final double left) {
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
    public boolean hasProjectile(boolean p2) {
        if(!p2){
        	return p1HasProjectile;
        }
        return p2HasProjectile;
    }

    /**
     * Handle event: player died.
     */
    @Override
    public void playerDied() {
    	if(GameInfo.getInstance().getPlayerMode().equals(PlayerMode.MULTI_PLAYER_VERSUS)){
    		if(player.isDead()&&player2.isDead()){
    			draw.playerDied();
    			isActive=false;
    		}
    		if(player.isDead()){
    			removeObject(player);
    			player.getImage().destroy();
    			player.addPoints(-1000);
    		}
    		if(player2.isDead()){
    			removeObject(player2);
    			player2.getImage().destroy();
    			player2.addPoints(-1000);
    		}
    		
    	}
    	else if(isActive){
    		draw.playerDied();
    		isActive=false;
    	}
    }

    /**
     * Add a projectile to the game.
     *
     * @param projectile the projectile to add.
     */
    @Override
    public void addProjectile(final Projectile projectile) {
        addObject(projectile);
        if(projectile.getPlayer().equals(player)){
        	this.projectile = projectile;
        	p1HasProjectile = true;
        }
        if(projectile.getPlayer().equals(player2)){
        	this.projectile2 = projectile;
        	p2HasProjectile = true;
        }
    }

    /**
     * Remove the projectile from the game.
     */
    @Override
    public void removeProjectile(boolean p2) {
        if(!p2){
        	removeObject(projectile);
        	this.projectile = null;
        	p1HasProjectile = false;
        }
        if(p2) {
        	removeObject(projectile2);
        	this.projectile2 = null;
        	p2HasProjectile = false;
        }
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
    public ICircleViewObject makeCircle(final double centerX, final double centerY, final double radius) {
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
    public ILineViewObject makeLine(final double startX, final double startY, final double endX, final double endY) {
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
    public IImageViewObject makeImage(final InputStream is, final double height, final double width) {
        return draw.makeImage(is, width, height);
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Player getPlayer2() {
    	return player2;
    }
    
    @Override
    public void handleModifierCollision(final Object mod, final boolean isPlayerPickup, final boolean isBubblePickup) {
        if (isPlayerPickup) {
            player.addModifier((AbstractPlayerDecorator) mod);
        } else if (isBubblePickup) {
            for (Bubble b : bubbles) {
                b.addModifier((AbstractBubbleDecorator) mod);
            }
        }
    }

    @Override
    public void handleBubbleSplit(final Point p) {
        pickupGenerator.generatePickup(p);
    }

    @Override
    public void addPoints(final int points) {
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

    //CHECKSTYLE.OFF
    //Methods for testing purposes

    GameObjects(final IDraw draw) {
        this.draw = draw;
        addBufferedDynamicObjects();
    }

    protected void addBubbles(final Bubble bubble) {
        bubbles.add(bubble);
    }

    protected ArrayList<IPrepareable> getPrepareUpdateable() {
        return prepUpdateableObjects;
    }

    protected ArrayList<IUpdateable> getUpdateable() {
        return updateableObjects;
    }

    protected ArrayList<ICollider> getCollider() {
        return colliderObjects;
    }

    protected ArrayList<IIntersectable> getIntersectable() {
        return intersectableObjects;
    }
    //CHECKSTYLE.ON
}
