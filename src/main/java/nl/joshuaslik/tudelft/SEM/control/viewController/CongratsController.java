package nl.joshuaslik.tudelft.SEM.control.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PopupControl;
import nl.joshuaslik.tudelft.SEM.Launcher;
import nl.joshuaslik.tudelft.SEM.utility.GameLog;

public class CongratsController implements IpopupController {

    @FXML
    private Button mainMenuButton;

    private PopupControl popupControl;

    /**
     * Start the pop-up when player has finished the game
     */
    public void start() {
    }

    /**
     * Handles clicking of the main menu button
     *
     * @param event the click of the button
     */
    @FXML
    private void handleMainMenuButton() {
        GameLog.addInfoLog("Main menu button pressed from congrats screen");
        System.out.println("Main Menu button pressed!");
        popupControl.hide();
        MainMenuController.loadView();
    }

    /**
     * Load the popup.
     * @param controller the main view controller.
     */
    public static void loadPopup(final IviewController controller) {
        Launcher.loadPopup(controller, Class.class.getResource("/data/gui/pages/Congrats.fxml"));
    }

    /**
     * Set the main view controller.
     * @param controller the main view controller.
     */
    @Override
    public void setMainViewController(final IviewController controller) {
    }

    /**
     * Set the popup controller.
     * @param popupControl the popup controller.
     */
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
}
