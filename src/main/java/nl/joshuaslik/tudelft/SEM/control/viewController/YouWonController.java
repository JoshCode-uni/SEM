package nl.joshuaslik.tudelft.SEM.control.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PopupControl;
import nl.joshuaslik.tudelft.SEM.Launcher;
import nl.joshuaslik.tudelft.SEM.utility.GameLog;

/**
 * Controller for the You Won screen.
 *
 * @author Bastijn
 */
public class YouWonController implements IpopupController {

    @FXML
    private Button mainMenuButton, nextLevelButton;

    private IviewController mainController;
    private PopupControl popupControl;

    /**
     * Start the pop-up when player has won
     */
    public void start() {

    }

    /**
     * Handles clicking of the main menu button
     */
    @FXML
    private void handleMainMenuButton() {
        GameLog.addInfoLog("Main menu button pressed from you won screen");
        System.out.println("Main Menu button pressed!");
        popupControl.hide();
        MainMenuController.loadView();
    }

    /**
     * Handles clicking of the next level button
     */
    //Needs to change
    @FXML
    private void handleNextLevelButton() {
        GameLog.addInfoLog("Next level button pressed from you won screen");
        System.out.println("Next level button pressed!");
        popupControl.hide();
        mainController.setButtonsDisabled(false);
        GameController.loadView();
    }

    public static void loadPopup(final IviewController controller) {
        Launcher.loadPopup(controller, Class.class.getResource("/data/gui/pages/YouWon.fxml"));
    }

    @Override
    public void setMainViewController(final IviewController controller) {
        mainController = controller;
    }

    @Override
    public void setPopupControl(final PopupControl popupControl) {
        this.popupControl = popupControl;
    }

    /**
     * FOR TESTING PURPOSES ONLY.
     */
    protected void fireMainMenuButton() {
        mainMenuButton.fire();
    }

    /**
     * FOR TESTING PURPOSES ONLY.
     */
    protected void fireNextLevelButton() {
        nextLevelButton.fire();
    }
}
