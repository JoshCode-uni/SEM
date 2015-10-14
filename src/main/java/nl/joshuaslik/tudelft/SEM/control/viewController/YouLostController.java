package nl.joshuaslik.tudelft.SEM.control.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PopupControl;
import nl.joshuaslik.tudelft.SEM.Launcher;
import nl.joshuaslik.tudelft.SEM.utility.GameLog;

public class YouLostController implements IpopupController {

    @FXML
    private Button mainMenuButton, tryAgainButton;

    private IviewController mainController;
    private PopupControl popupControl;

    /**
     * Handles clicking of the main menu button
     */
    @FXML
    private void handleMainMenuButton() {
        GameLog.addInfoLog("Retry button pressed from you lost screen");
        System.out.println("Retry button pressed!");
        popupControl.hide();
        MainMenuController.loadView();
    }

    /**
     * Handles clicking of the try again button
     */
    @FXML
    private void handleTryAgainButton() {
        GameLog.addInfoLog("Main menu button pressed from you lost screen");
        System.out.println("Main Menu button pressed!");
        popupControl.hide();
        mainController.setButtonsDisabled(false);
        GameController.loadView();
    }

    /**
     * Load this popup.
     *
     * @param controller the controller class of the currently loaded view.
     */
    public static void loadPopup(final IviewController controller) {
        Launcher.loadPopup(controller, Class.class.getResource("/data/gui/pages/YouLost.fxml"));
    }

    /**
     * Set the controller of the main view.
     *
     * @param controller the main view controller.
     */
    @Override
    public void setMainViewController(final IviewController controller) {
        mainController = controller;
    }

    /**
     * Set the popupControl of this popup.
     *
     * @param popupControl PopupControl.
     */
    @Override
    public void setPopupControl(final PopupControl popupControl) {
        this.popupControl = popupControl;
    }

    /**
     * FOR TESTING PURPOSES ONLY.
     *
     * @return view element
     */
    protected Button getMainMenuButton() {
        return mainMenuButton;
    }

    /**
     * FOR TESTING PURPOSES ONLY.
     *
     * @return view element
     */
    protected Button getTryAgainButton() {
        return tryAgainButton;
    }
}
