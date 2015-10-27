package nl.joshuaslik.tudelft.SEM.control.viewController;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import nl.joshuaslik.tudelft.SEM.Launcher;
import nl.joshuaslik.tudelft.SEM.model.container.Users;
import nl.joshuaslik.tudelft.SEM.model.container.Levels;
import nl.joshuaslik.tudelft.SEM.model.container.PlayerMode;
import nl.joshuaslik.tudelft.SEM.sound.EffectPlayer;
import nl.joshuaslik.tudelft.SEM.sound.MusicLoop;
import nl.joshuaslik.tudelft.SEM.utility.GameLog;

/**
 * Controller for the main menu UI.
 *
 * @author Bastijn
 * @author Faris
 */
public class MainMenuViewController implements IviewController {

    @FXML
    private HBox chooseLevelBox;

    @FXML
    private VBox gameModeBox;

    @FXML
    private Pane optionsPane;

    @FXML
    private Button playButton, chooseLevelButton, highscoresButton, optionsButton, quitButton, 
            classicButton;

    @FXML
    private Text totalScore, p1Score, p2Score;

    @FXML
    private Button level1Button, level2Button, level3Button, level4Button, level5Button;

    /**
     * Initialize.
     */
    public void initialize() {
        showGameModeButtons();
        totalScore.setText("Total Score: " + Users.getInstance().getTotalScore());
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
     * showGameModeButtons Let game mode buttons be only shown when play button is hovered over
     */
    private void showGameModeButtons() {
        gameModeBox.setVisible(false);
        playButton.setOnMouseClicked((MouseEvent me) -> {
            gameModeBox.setVisible(true);
        });
        playButton.setOnMouseEntered((MouseEvent me) -> {
            gameModeBox.setVisible(true);
        });
        gameModeBox.setOnMouseEntered((MouseEvent me) -> {
            gameModeBox.setVisible(true);
        });
        playButton.setOnMouseExited((MouseEvent me) -> {
            gameModeBox.setVisible(false);
        });
        gameModeBox.setOnMouseExited((MouseEvent me) -> {
            gameModeBox.setVisible(false);
        });
    }

    /**
     * Handles clicking of the start button
     */
    @FXML
    protected void handlePlayButton() {
        GameLog.addInfoLog("Play button pressed from main menu");
        gameModeBox.setVisible(!gameModeBox.isVisible());
    }

    /**
     * Handles clicking of the classic mode button
     */
    @FXML
    protected void handleClassicButton() {
        GameLog.addInfoLog("Classic button pressed from main menu");
        Users.getInstance().setClassicMode();
        GameViewController.loadView();

    }

    /**
     * Handles clicking of the survival mode button
     */
    @FXML
    protected void handleSurvivalButton() {
        GameLog.addInfoLog("Survival button pressed from main menu");
        Users.getInstance().setSurvivalMode();
        GameViewController.loadView();
    }

    /**
     * Handles clicking of the choose level button. Toggles choose button visibility.
     */
    @FXML
    protected void handleChooseLevelButton() {
        GameLog.addInfoLog("Choose level button pressed from main menu");
        chooseLevelBox.setVisible(!chooseLevelBox.isVisible());
    }

    /**
     * Handles clicking of the highscores button.
     */
    @FXML
    protected void handleHighscoresButton() {
        GameLog.addInfoLog("Highscores button pressed from main menu");
        HighscoresViewController.loadPopup(this);
    }

    /**
     * Handles clicking of the options button
     */
    @FXML
    protected void handleOptionsButton() {
        GameLog.addInfoLog("Options button pressed from main menu");
        optionsPane.setVisible(!optionsPane.isVisible());
    }

    /**
     * Handles clicking of the quit button
     */
    @FXML
    protected void handleQuitButton() {
        GameLog.addInfoLog("Quit button pressed from main menu");
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
        Users gi = Users.getInstance();
        if (gi.getPlayerMode().equals(PlayerMode.MULTI_PLAYER_VERSUS)) {
            totalScore.setVisible(false);
            p1Score.setText("Score player 1: " + gi.getPlayer1Score());
            p2Score.setText("Score player 2: " + gi.getPlayer2Score());
            p1Score.setVisible(true);
            p2Score.setVisible(true);
        }
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
        highscoresButton.setDisable(disabled);
        optionsButton.setDisable(disabled);
        quitButton.setDisable(disabled);
    }

    /**
     * Handles clicking of the Level 1 button
     */
    @FXML
    protected void handleLevel1Button() {
        GameLog.addInfoLog("Level 1 button pressed");
        Levels.setCurrentLevel(0);
        GameViewController.loadView();

    }

    /**
     * Handles clicking of the Level 2 button
     */
    @FXML
    protected void handleLevel2Button() {
        GameLog.addInfoLog("Level 2 button pressed");
        Levels.setCurrentLevel(1);
        GameViewController.loadView();
    }

    /**
     * Handles clicking of the Level 3 button
     */
    @FXML
    protected void handleLevel3Button() {
        GameLog.addInfoLog("Level 3 button pressed");
        Levels.setCurrentLevel(2);
        GameViewController.loadView();

    }

    /**
     * Handles clicking of the Level 4 button
     */
    @FXML
    protected void handleLevel4Button() {
        GameLog.addInfoLog("Level 4 button pressed");
        Levels.setCurrentLevel(3);
        GameViewController.loadView();
    }

    /**
     * Handles clicking of the Level 5 button
     */
    @FXML
    protected void handleLevel5Button() {
        GameLog.addInfoLog("Level 5 button pressed");
        Levels.setCurrentLevel(4);
        GameViewController.loadView();
    }

    /**
     * Toggle the sound.
     */
    @FXML
    private void toggleSoundButton() {
        MusicLoop.getInstance().toggleMusic();
        EffectPlayer.getInstace().toggleSound();
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
        classicButton.fire();
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
