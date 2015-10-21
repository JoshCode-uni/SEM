/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
import nl.joshuaslik.tudelft.SEM.model.container.GameMode;
import nl.joshuaslik.tudelft.SEM.model.container.Levels;
import nl.joshuaslik.tudelft.SEM.model.container.PlayerMode;
import nl.joshuaslik.tudelft.SEM.model.container.Point;
import nl.joshuaslik.tudelft.SEM.model.container.Vector;
import nl.joshuaslik.tudelft.SEM.utility.IObservable;
import nl.joshuaslik.tudelft.SEM.utility.IObserver;
import org.apache.commons.lang3.ClassUtils;

/**
 * Game objects stores all objects of a level and updates them. It also keeps track of other things,
 * like if there are any bubbles left and the score.
 *
 * @author faris
 */
public class GameObjects implements IUpdateable, IGameObjects, IOberservableGameObjectContainer {

    private final ArrayList<IUpdateable> updateableObjects = new ArrayList<>();
    private final ArrayList<IPrepareable> prepUpdateableObjects = new ArrayList<>();
    private final ArrayList<ICollider> colliderObjects = new ArrayList<>();
    private final ArrayList<IIntersectable> intersectableObjects = new ArrayList<>();
    private final ArrayList<IObservable> oberservableObjects = new ArrayList<>();

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
    
    private ArrayList<IObserver> observerList = new ArrayList<>();

    /**
     * Construct all required objects for the given level.
     *
     * @param draw interface to the drawing class which allows us to draw the game objects.
     * @param topBorder y value of the top border.
     * @param rightBorder x value of the right border.
     * @param bottomBorder y value of the bottom border.
     * @param leftBorder x value of the left border.
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
        checkSurvivalMode();
        addBufferedDynamicObjects();
        removeBufferedDynamicObjects();
        for (IPrepareable e : prepUpdateableObjects) {
            e.prepare(nanoFrameTime);
        }
        for (ICollider collider : colliderObjects) {
            for (IIntersectable intersectable : intersectableObjects) {
                if (intersectable != collider) {
                    collider.checkCollision(intersectable, nanoFrameTime);
                }
            }
        }
        for (IUpdateable e : updateableObjects) {
            e.update(nanoFrameTime);
        }
    }

    /**
     * Add a random bubble with a chance of 1/300 (~once every 5 seconds) at a random location.
     */
    private void checkSurvivalMode() {
        if (!GameMode.SURVIVAL.equals(GameInfo.getInstance().getGameMode())) {
            return;
        }
        if (allBubblesDestroyed() || (Math.random() < 1.0 / 300.0 && bubblesLeft() < 10)) {
            Point topLeft = Levels.getCircleSpawnPointTopLeft();
            Point bottomRight = Levels.getCircleSpawnPointBottomRight();
            int x = (int) getRandomBetween(topLeft.getxPos(), bottomRight.getxPos());
            int y = (int) getRandomBetween(topLeft.getyPos(), bottomRight.getyPos());
            spawnBubble(new Point(x, y), randomBubbleSize());
        }
    }

    /**
     * Get a random value between a and b.
     *
     * @param a a number.
     * @param b a number.
     * @return a number between a and b.
     */
    private double getRandomBetween(double a, double b) {
        if (a < b) {
            return (a + (b - a) * Math.random());
        } else {
            return (b + (a - b) * Math.random());
        }
    }

    /**
     * Returns either 20, 40 or 80. 40: 50%, 20: 25%, 80: 25% chance.
     *
     * @return 20, 40 or 80.
     */
    private int randomBubbleSize() {
        double random = Math.random();
        if (random < 0.5) {
            return 40;
        } else if (random < 0.75) {
            return 20;
        } else {
            return 80;
        }
    }

    /**
     * Create a bubble at the specified location.
     *
     * @param location a point.
     */
    private void spawnBubble(Point location, int size) {
        Bubble b = new Bubble(this, location, size, new Vector(1, 0));
        addObject(b);
    }

    /**
     * Initialize the borders of the game.
     *
     * @param topBorder y value of the top border.
     * @param rightBorder x value of the right border.
     * @param bottomBorder y value of the bottom border.
     * @param leftBorder x value of the left border.
     */
    private void initializeBorders(final double topBorder, final double rightBorder, final double bottomBorder, final double leftBorder) {
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
        InputStream is, is2;
        try {
            is = getClass().getResource("/data/gui/img/penguin.png").openStream();
            is2 = getClass().getResource("/data/gui/img/penguin.png").openStream();
        }
        catch (IOException ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, "Couldn't load player image", ex);
            return;
        }
        player = new Player((IGameObjects) this, is, keyBoard, false);
        addObject(player);
        if (GameInfo.getInstance().getPlayerMode().equals(PlayerMode.MULTI_PLAYER_COOP)
                || GameInfo.getInstance().getPlayerMode().equals(PlayerMode.MULTI_PLAYER_VERSUS)) {
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
        if (!GameMode.SURVIVAL.equals(GameInfo.getInstance().getGameMode())) {
            for (IPhysicsObject e : Levels.getLevelObjects((IGameObjects) this)) {
                addObject(e);
            }
        } else {
            for (IPhysicsObject e : Levels.getSurvivalLevelObjects((IGameObjects) this)) {
                addObject(e);
            }
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
            if (object == null) {
                continue;
            }
            List<Class<?>> interfaces = ClassUtils.getAllInterfaces(object.getClass());
            if (interfaces.contains(IUpdateable.class)) {
                updateableObjects.add((IUpdateable) object);
            }
            if (interfaces.contains(IPrepareable.class)) {
                prepUpdateableObjects.add((IPrepareable) object);
            }
            if (interfaces.contains(IIntersectable.class)) {
                intersectableObjects.add((IIntersectable) object);
            }
            if (interfaces.contains(ICollider.class)) {
                colliderObjects.add((ICollider) object);
            }
            if (Bubble.class.isAssignableFrom(object.getClass())) {
                bubbles.add((Bubble) object);
            }
            if (interfaces.contains(IObservable.class)) {
                oberservableObjects.add((IObservable) object);
                for(IObserver o : observerList) {
                    ((IObservable) object).addObserver(o);
                }
            }
        }
        addObjectBuffer.clear();
    }

    /**
     * Remove all buffered dynamic objects from the game.
     */
    private void removeBufferedDynamicObjects() {
        for (IPhysicsObject object : removeObjectBuffer) {
            if (object == null) {
                continue;
            }
            List<Class<?>> interfaces = ClassUtils.getAllInterfaces(object.getClass());
            if (interfaces.contains(IUpdateable.class)) {
                updateableObjects.remove((IUpdateable) object);
            }
            if (interfaces.contains(IPrepareable.class)) {
                prepUpdateableObjects.remove((IPrepareable) object);
            }
            if (interfaces.contains(IIntersectable.class)) {
                intersectableObjects.remove((IIntersectable) object);
            }
            if (interfaces.contains(ICollider.class)) {
                colliderObjects.remove((ICollider) object);
            }
            if (Bubble.class.isAssignableFrom(object.getClass())) {
                bubbles.remove((Bubble) object);
                score += 10;
            }
            if (interfaces.contains(IObservable.class)) {
                oberservableObjects.remove((IObservable) object);
                for(IObserver o : observerList) {
                    ((IObservable) object).deleteObserver(o);
                }
            }
        }
        removeObjectBuffer.clear();
    }

    /**
     * Set the bounds of the game.
     *
     * @param top min y value.
     * @param right max x value.
     * @param bottom max y value.
     * @param left min x value.
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
     * @param p2 if this method is called by player 2.
     * @return if the game currently has a spawned projectile.
     */
    @Override
    public boolean hasProjectile(boolean p2) {
        if (!p2) {
            return p1HasProjectile;
        }
        return p2HasProjectile;
    }

    /**
     * Handle event: player died.
     */
    @Override
    public void playerDied() {
        if (GameInfo.getInstance().getPlayerMode().equals(PlayerMode.MULTI_PLAYER_VERSUS)) {
            if (player.isDead() && player2.isDead()) {
                draw.playerDied();
                isActive = false;
            }
            if (player.isDead()) {
                removeObject(player);
                player.getImage().destroy();
            }
            if (player2.isDead()) {
                removeObject(player2);
                player2.getImage().destroy();
            }

        } else if (isActive) {
            draw.playerDied();
            isActive = false;
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
        if (projectile.getPlayer().equals(player)) {
            this.projectile = projectile;
            p1HasProjectile = true;
        }
        if (projectile.getPlayer().equals(player2)) {
            this.projectile2 = projectile;
            p2HasProjectile = true;
        }
    }

    /**
     * Remove the projectile from the game.
     */
    @Override
    public void removeProjectile(boolean p2) {
        if (!p2) {
            removeObject(projectile);
            this.projectile = null;
            p1HasProjectile = false;
        }
        if (p2) {
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
     * @param radius the radius of the circle.
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
     * @param endX the x coordinate of the end point of the line.
     * @param endY the y coordinate of the end point of the line.
     * @return the interface of the line view object.
     */
    @Override
    public ILineViewObject makeLine(final double startX, final double startY, final double endX, final double endY) {
        return draw.makeLine(startX, startY, endX, endY);
    }

    /**
     * Create an image in the view.
     *
     * @param is the input stream of the image.
     * @param height the height of the image.
     * @param width the width of the image.
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

    /**
     * Add observers to existing observable objects and make sure they are added to newly created
     * observable objects.
     * @param observer an observer.
     */
    @Override
    public void addObserver(IObserver observer) {
        observerList.add(observer);
        for(IObservable o : oberservableObjects) {
            o.addObserver(observer);
        }
    }
}
