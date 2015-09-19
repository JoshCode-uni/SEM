package nl.joshuaslik.tudelft.SEM.control.viewController;

import java.io.InputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
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
import nl.joshuaslik.tudelft.SEM.model.container.Levels;
import utility.GameLog;

/**
 * Controller for the game UI.
 *
 * @author Bastijn
 */
public class GameController implements IviewController {
    
    @FXML
    private Pane pane;

    @FXML
    private Rectangle timeRectangle, negativeTimeRectangle;

    @FXML
    private Text livesText, levelText, scoreText;

    @FXML
    private Button quitButton, mainMenuButton;

    @FXML
    private Line top, right, bottom, left;
    @FXML
    private Group gameObjects;

    private GameLoop gl;

    private static int currentLevel = 0;
    private static final long MAX_TIME = 30_000_000_000l; // 30 seconds in ns
    private long timeLeft;

    /**
     * Handles clicking of the quit button
     *
     * @param event the click of the button
     */
    @FXML
    private void handleQuitButton(ActionEvent event) {
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
    private void handleMainMenuButton(ActionEvent event) {
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
    public void start(Scene scene) {
        levelText.setText(Integer.toString(currentLevel + 1));
        timeLeft = MAX_TIME;

        gl = new GameLoop(this, currentLevel, top.getStartY(), top.getEndX(),
                bottom.getStartY(), top.getStartX(), scene);
        gl.setViewController(this);

        gl.start();
    }

    /**
     * Draw a node in the game view.
     *
     * @param n node to draw.
     */
    public void drawNode(Node n) {
        gameObjects.getChildren().add(n);
    }

    /**
     * Remove a node from the game view.
     *
     * @param n node to remove.
     */
    public void removeNode(Node n) {
        gameObjects.getChildren().remove(n);
    }

    /**
     * Update the length of the "time left" rectangle.
     *
     * @param nanoTimePassed the framerate (nanoseconds/frame)
     */
    public void updateTime(Long nanoTimePassed) {
        timeLeft -= nanoTimePassed;
        if (timeLeft <= 0) {
            died();
            return;
        }
        scoreText.setText("Score: " + gl.getScore());
        timeRectangle.setWidth(negativeTimeRectangle.getWidth() * ((double) timeLeft / (double) MAX_TIME));
    }

    /**
     * Called when a level is completed
     */
    public void levelCompleted() {
        int totalScore = gl.getScore() + (int) (timeLeft / 100_000_000.0);
        GameLog.addInfoLog("Player completed level: " + currentLevel);
        GameLog.addInfoLog("level score: " + totalScore);
        
        MainMenuController.setScore(totalScore, currentLevel);
        gl.stop();
        gl = null;
        if (currentLevel + 1 < Levels.amountOfLevels()) {
            currentLevel++;
        }
        //		MainMenuController.loadView();
        IviewController contr = MainMenuController.loadView();
        YouWonController.loadPopup(contr);
    }

    /**
     * The player died, end level.
     */
    public void died() {
        GameLog.addInfoLog("Player died");
        System.out.println("Player died");
        gl.stop();
        gl = null;

        IviewController contr = MainMenuController.loadView();
        YouLostController.loadPopup(contr);
    }

    /**
     * Select the level which should be played.
     *
     * @param level
     */
    public static void setLevel(int level) {
        GameController.currentLevel = level;
    }

    /**
     * Disable all butons.
     *
     * @param disabled if the buttons should be disabled.
     */
    @Override
    public void setButtonsDisiabled(boolean disabled) {
        GameLog.addWarningLog("Called non-implemented method: " + 
                "setButtonsDisiabled in class GameController");
        //unnecesairy, no popup will occur in this view
    }
    
    public ICircleViewObject makeCircle(double centerX, double centerY, 
            double radius) {
        return new CircleViewObject(centerX, centerY, radius, this);
    }
    
    public IImageViewObject makeImage(InputStream is, 
            double width, double height) {
        return new ImageViewObject(is, width, height, this);
    }
    
    public ILineViewObject makeLine(double startX, double startY, double endX,
            double endY) {
        return new LineViewObject(startX, startY, endX, endY, this);
    }
}
