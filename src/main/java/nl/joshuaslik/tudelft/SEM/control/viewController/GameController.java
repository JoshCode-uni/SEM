package nl.joshuaslik.tudelft.SEM.control.viewController;

import java.io.InputStream;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import nl.joshuaslik.tudelft.SEM.Launcher;
import nl.joshuaslik.tudelft.SEM.control.GameLoop;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.CircleViewObject;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ICircleViewObject;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.IImageViewObject;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ILineViewObject;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ImageViewObject;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.LineViewObject;
import nl.joshuaslik.tudelft.SEM.model.container.GameInfo;
import nl.joshuaslik.tudelft.SEM.model.container.GameMode;
import nl.joshuaslik.tudelft.SEM.model.container.Levels;
import nl.joshuaslik.tudelft.SEM.model.container.PlayerMode;
import nl.joshuaslik.tudelft.SEM.utility.GameLog;

/**
 * Controller for the game UI.
 *
 * @author Bastijn
 */
public class GameController implements IviewController {

    @FXML
    private Rectangle timeRectangle, negativeTimeRectangle;

    @FXML
    private Text levelText, scoreText, scoreTextPlayer2;

    @FXML
    private ImageView background;
    @FXML
    private ImageView lives;

    @FXML
    private Button quitButton, mainMenuButton;

    @FXML
    private Line top, bottom;
    @FXML
    private Group gameObjects;

    private GameLoop gl;

    private static final long MAX_TIME = 60_000_000_000l; // 60 seconds in ns

    private long timeLeft;

    /**
     * Handles clicking of the quit button
     *
     * @param event the click of the button
     */
    @FXML
    private void handleQuitButton() {
        GameLog.addInfoLog("Quit button pressed from game screen");
        System.out.println("Quit button pressed!");
        System.exit(0);
    }

    /**
     * Handles clicking of the main menu button
     *
     * @param event the click of the button
     */
    @FXML
    private void handleMainMenuButton() {
        GameLog.addInfoLog("Main Menu button pressed from game screen");
        System.out.println("Main Menu button pressed!");
        gl.stop();
        gl = null;
        MainMenuController.loadView();
    }

    /**
     * Method to return the timeRectangle
     *
     * @return Rectangle timeRectangle
     */
    public Rectangle getTimeRectangle() {
        return timeRectangle;
    }

    /**
     * Load this view.
     */
    public static void loadView() {
        GameLog.addInfoLog("Quit button pressed from game screen");
        Launcher.loadView(Class.class.getResource("/data/gui/pages/GameGUI.fxml"));
    }

    /**
     * Initialize stuff of this view.
     *
     * @param scene the scene which this view is loaded into.
     */
    @Override
    public void start(final Scene scene) {
        int lvl = Levels.getCurrentLevel() + 1;
        Image bg;
        if (!GameMode.SURVIVAL.equals(GameInfo.getInstance().getGameMode())) {
            bg = new Image(Class.class.getResourceAsStream("/data/gui/img/BackgroundForLevel" + lvl + ".jpg"));
        } else {
            bg = new Image(Class.class.getResourceAsStream("/data/gui/img/BackgroundForLevel1.jpg"));
        }

        assert (bg != null);
        assert (background != null);
        background.setImage(bg);
        levelText.setText("Level " + Integer.toString(lvl));
        timeLeft = MAX_TIME;
        resetLives();
        gl = new GameLoop(this, top.getStartY(), top.getEndX(), bottom.getStartY(), top.getStartX(), scene);
        gl.setViewController(this);
        gl.start();
    }

    private void resetLives() {
        if (!GameMode.SURVIVAL.equals(GameInfo.getInstance().getGameMode())) {
            Image image = new Image(Class.class.getResourceAsStream("/data/gui/img/heart" + GameInfo.getInstance().getLives() + ".png"));
            lives.setImage(image);
        } else {
            Image image = new Image(Class.class.getResourceAsStream("/data/gui/img/heart0.png"));
            lives.setImage(image);
        }
    }

    /**
     * Draw a node in the game view.
     *
     * @param n node to draw.
     */
    public void drawNode(final Node n) {
        gameObjects.getChildren().add(n);
    }

    /**
     * Remove a node from the game view.
     *
     * @param n node to remove.
     */
    public void removeNode(final Node n) {
        gameObjects.getChildren().remove(n);
    }

    /**
     * Update the length of the "time left" rectangle.
     *
     * @param nanoTimePassed the framerate (nanoseconds/frame)
     */
    public void updateTime(final Long nanoTimePassed) {
        GameInfo gi = GameInfo.getInstance();
        if (!GameMode.SURVIVAL.equals(GameInfo.getInstance().getGameMode())) {
            timeLeft -= nanoTimePassed;
            if (timeLeft <= 0) {
                died();
                return;
            }
            timeRectangle.setWidth(negativeTimeRectangle.getWidth() * ((double) timeLeft
                    / (double) MAX_TIME));
        }
        if (!gi.getPlayerMode().equals(PlayerMode.MULTI_PLAYER_VERSUS)) {
            scoreText.setText("Score: " + gl.getScore());
        } else {
            scoreText.setText("Score of " + gi.getPlayerNames().get(0) + ": " + gl.getPlayer1Score());
            scoreTextPlayer2.setText("Score of " + gi.getPlayerNames().get(1) + ": " + gl.getPlayer2Score());
        }
    }

    /**
     * Called when a level is completed
     */
    public void levelCompleted() {
        logLevelCompletedData();
        gl.stop();
        gl = null;
        Levels.nextLevel();

        if (Levels.getCurrentLevel() < 5) {
            YouWonController.loadPopup(this);
        } else {
            CongratsController.loadPopup(this);
        }
    }

    /**
     * Log player completed level data.
     */
    private void logLevelCompletedData() {
        if (GameInfo.getInstance().getPlayerMode().equals(PlayerMode.SINGLE_PLAYER)) {
            int totalScore = gl.getScore() + (int) (timeLeft / 100_000_000.0);
            GameLog.addInfoLog("Player completed level: " + Levels.getCurrentLevel());
            GameLog.addInfoLog("level score: " + totalScore);
            GameInfo.getInstance().setLevelScore(Levels.getCurrentLevel(), totalScore);
        } else if (GameInfo.getInstance().getPlayerMode().equals(PlayerMode.MULTI_PLAYER_COOP)) {
            int totalScore = gl.getScore() + (int) (timeLeft / 100_000_000.0);
            GameLog.addInfoLog("Players completed level: " + Levels.getCurrentLevel());
            GameLog.addInfoLog("Player 1 score:" + gl.getPlayer1Score());
            GameLog.addInfoLog("Player 2 score:" + gl.getPlayer2Score());
            GameLog.addInfoLog("level score: " + totalScore);
            GameInfo.getInstance().setPlayer1LevelScore(Levels.getCurrentLevel(), totalScore);
            GameInfo.getInstance().setPlayer1LevelScore(Levels.getCurrentLevel(), totalScore);
        } else {
            GameInfo.getInstance().setPlayer1LevelScore(Levels.getCurrentLevel(), gl.getPlayer1Score());
            GameInfo.getInstance().setPlayer2LevelScore(Levels.getCurrentLevel(), gl.getPlayer2Score());
        }
    }

    /**
     * The player died, end level.
     */
    public void died() {
        GameLog.addInfoLog("Player died");
        System.out.println("Player died");
        gl.stop();
        gl = null;
        GameInfo gi = GameInfo.getInstance();
        int ilives = gi.getLives() - 1;
        gi.loseLife();
        if (ilives >= 0 && !GameMode.SURVIVAL.equals(GameInfo.getInstance().getGameMode())) {
            GameController.loadView();
        } else {
            YouLostController.loadPopup(this);
            gi.resetLives();
        }
    }

    /**
     * Disable all buttons.
     *
     * @param disabled if the buttons should be disabled.
     */
    @Override
    public void setButtonsDisabled(final boolean disabled) {
        quitButton.setDisable(disabled);
        mainMenuButton.setDisable(disabled);
    }

    /**
     * Create a circle in the view.
     *
     * @param centerX the x coordinate of the center of the circle.
     * @param centerY the y coordinate of the center of the circle.
     * @param radius the radius of the circle.
     * @return the interface of the circle view object.
     */
    public ICircleViewObject makeCircle(final double centerX, final double centerY, final double radius) {
        return new CircleViewObject(centerX, centerY, radius, this);
    }

    /**
     * Create an image in the view.
     *
     * @param is the input stream of the image.
     * @param height the height of the image.
     * @param width the width of the image.
     * @return the interface of the image view object.
     */
    public IImageViewObject makeImage(final InputStream is, final double width, final double height) {
        return new ImageViewObject(is, width, height, this);
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
    public ILineViewObject makeLine(final double startX, final double startY, final double endX, final double endY) {
        return new LineViewObject(startX, startY, endX, endY, this);
    }

    /**
     * Add a life.
     */
    public void addLife() {
        GameInfo.getInstance().addLife();
        resetLives();
    }

    /**
     * FOR TESTING PURPOSES ONLY.
     */
    protected final void fireQuitButton() {
        quitButton.fire();
    }

    /**
     * FOR TESTING PURPOSES ONLY.
     */
    protected final void fireMainMenuButton() {
        mainMenuButton.fire();
    }
}
