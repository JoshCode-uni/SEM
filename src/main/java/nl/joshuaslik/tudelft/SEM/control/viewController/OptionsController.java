package nl.joshuaslik.tudelft.SEM.control.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import nl.joshuaslik.tudelft.SEM.Launcher;
import utility.GameLog;

public class OptionsController implements IviewController {

    @FXML
    private Button playButton, chooseLevelButton, optionsButton, quitButton;

    /**
     * Handles clicking of the start button
     *
     * @param event the click of the button
     */
    @FXML
    protected void handlePlayButton(ActionEvent event) {
        GameLog.addInfoLog("Play button pressed from option menu");
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
        GameLog.addInfoLog("Choose level button pressed from option menu");
        System.out.println("Choose Level button pressed!");
//        ChooseLevelController.loadView();
    }

    /**
     * Handles clicking of the options button
     *
     * @param event the click of the button
     */
    @FXML
    protected void handleOptionsButton(ActionEvent event) {
        GameLog.addInfoLog("Options button pressed from option menu");
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
        GameLog.addInfoLog("Quit button pressed from option menu");
        System.out.println("Quit button pressed!");
        System.exit(0);
    }

    /**
     * Load this view.
     */
    public static void loadView() {
        Launcher.loadView(Class.class.getResource("/data/gui/pages/Options.fxml"));
    }

    /**
     * Initialize.
     *
     * @param scene the scene of this view.
     */
    @Override
    public void start(Scene scene) {

    }

    /**
     * Disable the buttons of this view.
     *
     * @param disabled if the buttons should be disabled.
     */
    @Override
    public void setButtonsDisiabled(boolean disabled) {
        // not currently needed
    }
}
