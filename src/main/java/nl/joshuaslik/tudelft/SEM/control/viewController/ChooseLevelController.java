package nl.joshuaslik.tudelft.SEM.control.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import nl.joshuaslik.tudelft.SEM.Launcher;
import utility.GameLog;

public class ChooseLevelController implements IviewController {

    @FXML
    private Pane pane;

    @FXML
    private Button level1Button, level2Button, level3Button, level4Button, level5Button, mainMenuButton;

    /**
     * Handles clicking of the Level 1 button
     *
     * @param event the click of the button
     */
    @FXML
    protected void handleLevel1Button(ActionEvent event) {
        GameLog.addInfoLog("Level 1 button pressed");
        System.out.println("Level 1 button pressed!");
        GameController.setLevel(0);
        GameController.loadView();

    }

    /**
     * Handles clicking of the Level 2 button
     *
     * @param event the click of the button
     */
    @FXML
    protected void handleLevel2Button(ActionEvent event) {
        GameLog.addInfoLog("Level 2 button pressed");
        System.out.println("Level 2 button pressed!");
        GameController.setLevel(1);
        GameController.loadView();
    }

    /**
     * Handles clicking of the Level 3 button
     *
     * @param event the click of the button
     */
    @FXML
    protected void handleLevel3Button(ActionEvent event) {
        GameLog.addInfoLog("Level 3 button pressed");
        System.out.println("Level 3 button pressed!");
        GameController.setLevel(2);
        GameController.loadView();

    }

    /**
     * Handles clicking of the Level 4 button
     *
     * @param event the click of the button
     */
    @FXML
    protected void handleLevel4Button(ActionEvent event) {
        GameLog.addInfoLog("Level 4 button pressed");
        System.out.println("Level 4 button pressed!");
        GameController.setLevel(3);
        GameController.loadView();
    }

    /**
     * Handles clicking of the Level 5 button
     *
     * @param event the click of the button
     */
    @FXML
    protected void handleLevel5Button(ActionEvent event) {
        GameLog.addInfoLog("Level 5 button pressed");
        System.out.println("Level 5 button pressed!");
        GameController.setLevel(4);
        GameController.loadView();
    }

    /**
     * Handles clicking of the main menu button
     *
     * @param event the click of the button
     */
    @FXML
    protected void handleMainMenuButton(ActionEvent event) {
        GameLog.addInfoLog("Main Menu button pressed from choose level");
        System.out.println("Main Menu button pressed!");
        MainMenuController.loadView();
    }

    /**
     * Handles clicking of the start button
     *
     * @param event the click of the button
     */
    @FXML
    protected void handlePlayButton(ActionEvent event) {
        GameLog.addInfoLog("Play button pressed from choose level");
        System.out.println("Play button pressed!");
        GameController.loadView();
    }

    /**
     * Handles clicking of the choose level button
     *
     * @param event the click of the button
     */
    @FXML
    protected void handleChooseLevelButton(ActionEvent event) {
        GameLog.addInfoLog("Choose level button pressed from choose level");
        System.out.println("Choose Level button pressed!");
        ChooseLevelController.loadView();
    }

    /**
     * Handles clicking of the options button
     *
     * @param event the click of the button
     */
    @FXML
    protected void handleOptionsButton(ActionEvent event) {
        GameLog.addInfoLog("Options button pressed from choose level");
        System.out.println("Options button pressed!");
        OptionsController.loadView();
    }

    /**
     * Handles clicking of the quit button
     *
     * @param event the click of the button
     */
    @FXML
    protected void handleQuitButton(ActionEvent event) {
        GameLog.addInfoLog("Quit button pressed from choose level");
        System.out.println("Quit button pressed!");
        System.exit(0);
    }

    /**
     * Load this view.
     */
    public static void loadView() {
        Launcher.loadView(Class.class.getResource("/data/gui/pages/ChooseLevel.fxml"));
    }

    /**
     * Initialize.
     *
     * @param scene the scene of this view.
     */
    @Override
    public void start(Scene scene) {

    }

    @Override
    public void setButtonsDisabled(boolean disabled) {
        //not currently needed
    }
}
