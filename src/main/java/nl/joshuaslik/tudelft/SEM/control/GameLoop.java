/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.GameObjects;
import nl.joshuaslik.tudelft.SEM.control.viewController.GameController;
import nl.joshuaslik.tudelft.SEM.control.viewController.Keyboard;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ICircleViewObject;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.IImageViewObject;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ILineViewObject;
import nl.joshuaslik.tudelft.SEM.model.container.GameInfo;
import nl.joshuaslik.tudelft.SEM.model.container.GameMode;
import nl.joshuaslik.tudelft.SEM.sound.EffectPlayer;
import nl.joshuaslik.tudelft.SEM.utility.GameLog;

/**
 * The gameloop which updated all objects after each frame.
 *
 * @author faris
 */
public class GameLoop extends AnimationTimer implements IDraw {

    private final Keyboard kb;
    private static final int FIRST_FRAME_TIME = 165_000_000;
    private GameController gameController;
    private final GameObjects gameObjects;
    private long oldTime = 0;
	private final Highscore highscore;
	
    /**
     * @param gameController the controller of the game view.
     * @param top y value of the top border.
     * @param right x value of the right border.
     * @param bottom y value of the bottom border.
     * @param left x value of the left border.
     * @param scene the scene of the game (to add a keylistener to).
     */
    public GameLoop(final GameController gameController, final double top, final double right, final double bottom,
            final double left, final Scene scene) {
        this.gameController = gameController;
        kb = new Keyboard(scene);
        gameObjects = new GameObjects((IDraw) this, top, right, bottom, left, kb);
    
        highscore = new Highscore();
    }

    /**
     * Start the gameloop.
     */
    @Override
    public final void start() {
        super.start();
        kb.addListeners();
        EffectPlayer.getInstace().addListenersTo(gameObjects);
    }

    /**
     * Stop the gameloop.
     */
    @Override
    public void stop() {
        super.stop();
        kb.removeListeners();
        EffectPlayer.getInstace().destroy();
    }

    /**
     * Called every time JavaFX refreshes a frame. Calls all update methods.
     *
     * @param time the current time.
     */
    @Override
    public final void handle(final long time) {
        if (!GameMode.SURVIVAL.equals(GameInfo.getInstance().getGameMode())
                && gameObjects.allBubblesDestroyed()) {
            highscore.addScore(getUser1().toString(), getPlayer1Score());
            gameController.levelCompleted();
            return;
        }
        long frametime;
        if (this.oldTime != 0) {
            frametime = time - this.oldTime;
        } else {
            frametime = FIRST_FRAME_TIME;
        }
        this.oldTime = time;
        updateGameObjects(frametime);
    }

    /**
     * Update the game objects.
     *
     * @param frametime the time of a frame in ns.
     */
    private void updateGameObjects(long frametime) {
        try {
            gameController.updateTime(frametime);
            gameObjects.update(frametime);
        }
        catch (Exception ex) {
            stop();
            GameLog.addErrorLog("Exception in game loop");
            GameLog.addErrorLog(ex.getMessage());
            Logger.getLogger(GameLoop.class.getName()).log(Level.SEVERE, "Exception in game loop", ex);
        }
    }

    /**
     * Set the view controller class.
     *
     * @param gameController the view controller class.
     */
    public final void setViewController(final GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Get the score achieved in this level.
     *
     * @return the score.
     */
    public final int getScore() {
        return gameObjects.getScore();
    }

    /**
     * Get the score of player 1.
     *
     * @return the score.
     */
    public final int getPlayer1Score() {
        return gameObjects.getPlayer().getScore();
    }

    /**
     * Get the score of player 1.
     *
     * @return the score.
     */
    public final int getPlayer2Score() {
        return gameObjects.getPlayer2().getScore();
    }

    /**
     * Tells gameController the player has died
     */
    @Override
    public void playerDied() {
        stop();
        highscore.addScore(getUser1().toString(), getPlayer1Score());
        gameController.died();
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
        return gameController.makeCircle(centerX, centerY, radius);
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
    public IImageViewObject makeImage(final InputStream is, final double width, final double height) {
        return gameController.makeImage(is, width, height);
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
        return gameController.makeLine(startX, startY, endX, endY);
    }

    /**
     * Adds a life to the player.
     */
    @Override
    public void addLife() {
        gameController.addLife();
    }

    /**
     * Returns a gamecontroller.
     *
     * @return gameController
     */
    final GameController getGameController() {
        return gameController;
    }

	public User getUser1() {
		return gameObjects.getUser();
	}
	
	public User getUser2() {
		return gameObjects.getUser2();
	}
}
