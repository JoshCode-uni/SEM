package nl.joshuaslik.tudelft.SEM.control.viewController;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import nl.joshuaslik.tudelft.SEM.Launcher;
import nl.joshuaslik.tudelft.SEM.model.container.Levels;
import nl.joshuaslik.tudelft.SEM.utility.GameLog;

/**
 * Controller for the main menu UI.
 *
 * @author Bastijn
 * @author Faris
 */
public class MainMenuController implements IviewController {

    @FXML
    private HBox chooseLevelBox;

    @FXML
    private Pane optionsPane;

    @FXML
    private Button playButton, chooseLevelButton, optionsButton, quitButton;

    @FXML
    private Text totalScore;

    @FXML
    private Button level1Button, level2Button, level3Button, level4Button, level5Button;

    private static final ArrayList<Integer> scoresPerLevel = new ArrayList<>(Levels.amountOfLevels());

    static {
        for (int i = 0; i < Levels.amountOfLevels() + 1; i++) {
            scoresPerLevel.add(0);
        }
    }

    /**
     * Initialize.
     */
    public void initialize() {
        totalScore.setText("Total Score: " + calculateTotalScore());
        level5Button.setDisable(true);
        level4Button.setDisable(true);
        level3Button.setDisable(true);
        level2Button.setDisable(true);
        switch (Levels.getUnlockedLevel()) {
            case (4):
                level5Button.setDisable(false);
                // intended fallthrough
            case 3:
                level4Button.setDisable(false);
            case 2:
                level3Button.setDisable(false);
            case 1:
                level2Button.setDisable(false);
        }
    }

    /**
     * Calculate the total score.
     * @return the total score.
     */
    private static int calculateTotalScore() {
        int score = 0;
        for (int e : scoresPerLevel) {
            score += e;
        }
        return score;
    }

    /**
     * Handles clicking of the start button
     */
    @FXML
    protected void handlePlayButton() {
        GameLog.addInfoLog("Play button pressed from main menu");
        System.out.println("Play button pressed!");
        GameController.loadView();
    }

    /**
     * Handles clicking of the choose level button. Toggles choose button
     * visibility.
     */
    @FXML
    protected void handleChooseLevelButton() {
        GameLog.addInfoLog("Choose level button pressed from main menu");
        System.out.println("Choose Level button pressed!");
        chooseLevelBox.setVisible(!chooseLevelBox.isVisible());
    }

    /**
     * Handles clicking of the options button
     */
    @FXML
    protected void handleOptionsButton() {
        GameLog.addInfoLog("Options button pressed from main menu");
        System.out.println("Options button pressed!");
        optionsPane.setVisible(!optionsPane.isVisible());
    }

    /**
     * Handles clicking of the quit button
     */
    @FXML
    protected void handleQuitButton() {
        GameLog.addInfoLog("Quit button pressed from main menu");
        System.out.println("Quit button pressed!");
        System.exit(0);
    }

    /**
     * Load this view.
     *
     * @return the view controller.
     */
    public static IviewController loadView() {
        return Launcher.loadView(Class.class.getResource("/data/gui/pages/MainMenu.fxml"));
    }

    /**
     * Initialize (after loading).
     *
     * @param scene the scene of this view.
     */
    @Override
    public void start(final Scene scene) {

    }

    /**
     * Set the score of a level.
     *
     * @param score the score.
     * @param level the level.
     */
    public static void setScore(final int score, final int level) {
        if (scoresPerLevel.get(level) < score) {
            MainMenuController.scoresPerLevel.set(level, score);
        }
        GameLog.addInfoLog("new total score: " + calculateTotalScore());
    }

    /**
     * Disable the buttons of this view.
     *
     * @param disabled if the buttons should be disabled.
     */
    @Override
    public void setButtonsDisabled(final boolean disabled) {
        playButton.setDisable(disabled);
        chooseLevelButton.setDisable(disabled);
        optionsButton.setDisable(disabled);
        quitButton.setDisable(disabled);
    }

    /**
     * Handles clicking of the Level 1 button
     */
    @FXML
    protected void handleLevel1Button() {
        GameLog.addInfoLog("Level 1 button pressed");
        System.out.println("Level 1 button pressed!");
        Levels.setCurrentLevel(0);
        GameController.loadView();

    }

    /**
     * Handles clicking of the Level 2 button
     */
    @FXML
    protected void handleLevel2Button() {
        GameLog.addInfoLog("Level 2 button pressed");
        System.out.println("Level 2 button pressed!");
        Levels.setCurrentLevel(1);
        GameController.loadView();
    }

    /**
     * Handles clicking of the Level 3 button
     */
    @FXML
    protected void handleLevel3Button() {
        GameLog.addInfoLog("Level 3 button pressed");
        System.out.println("Level 3 button pressed!");
        Levels.setCurrentLevel(2);
        GameController.loadView();

    }

    /**
     * Handles clicking of the Level 4 button
     */
    @FXML
    protected void handleLevel4Button() {
        GameLog.addInfoLog("Level 4 button pressed");
        System.out.println("Level 4 button pressed!");
        Levels.setCurrentLevel(3);
        GameController.loadView();
    }

    /**
     * Handles clicking of the Level 5 button
     */
    @FXML
    protected void handleLevel5Button() {
        GameLog.addInfoLog("Level 5 button pressed");
        System.out.println("Level 5 button pressed!");
        Levels.setCurrentLevel(4);
        GameController.loadView();
    }

    /**
     * FOR TESTING PURPOSES ONLY.
     *
     * @return view element
     */
    protected final HBox getChooseLevelBox() {
        return chooseLevelBox;
    }

    /**
     * FOR TESTING PURPOSES ONLY.
     *
     * @return view element
     */
    protected final Pane getOptionsPane() {
        return optionsPane;
    }

    /**
     * FOR TESTING PURPOSES ONLY.
     */
    protected final void firePlayButton() {
        playButton.fire();
    }

    /**
     * FOR TESTING PURPOSES ONLY.
     */
    protected final void fireChooseLevelButton() {
        chooseLevelButton.fire();
    }

    /**
     * FOR TESTING PURPOSES ONLY.
     */
    protected final void fireOptionsButton() {
        optionsButton.fire();
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
    protected final void fireLevel1Button() {
        level1Button.setDisable(false);
        level1Button.fire();
    }

    /**
     * FOR TESTING PURPOSES ONLY.
     */
    protected final void fireLevel2Button() {
        level2Button.setDisable(false);
        level2Button.fire();
    }

    /**
     * FOR TESTING PURPOSES ONLY.
     */
    protected final void fireLevel3Button() {
        level3Button.setDisable(false);
        level3Button.fire();
    }

    /**
     * FOR TESTING PURPOSES ONLY.
     */
    protected final void fireLevel4Button() {
        level4Button.setDisable(false);
        level4Button.fire();
    }

    /**
     * FOR TESTING PURPOSES ONLY.
     */
    protected final void fireLevel5Button() {
        level5Button.setDisable(false);
        level5Button.fire();
    }
}
