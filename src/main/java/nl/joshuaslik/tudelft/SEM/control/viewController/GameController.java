package nl.joshuaslik.tudelft.SEM.control.viewController;

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
import nl.joshuaslik.tudelft.SEM.model.container.Levels;
import nl.joshuaslik.tudelft.SEM.model.container.Point;
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
        GameLog.addInfoLog("Quit button pressed");
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
        GameLog.addInfoLog("Main Menu button pressed");
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
        // !!!!!!!! Needs to be changed !!!!!!!!!
        System.out.println("Level completed!");
        int totalScore = gl.getScore() + (int) (timeLeft / 100_000_000.0);
        System.out.println("level score = " + totalScore);
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
        //unnecesairy, no popup will occur in this view
    }
}
