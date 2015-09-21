package nl.joshuaslik.tudelft.SEM.control.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PopupControl;
import nl.joshuaslik.tudelft.SEM.Launcher;
import utility.GameLog;

public class YouLostController implements IpopupController {

    @FXML
    private Button mainMenuButton;

    private IviewController mainController;
    private PopupControl popupControl;

    /**
     * Handles clicking of the main menu button
     *
     * @param event the click of the button
     */
    @FXML
    private void handleMainMenuButton(ActionEvent event) {
        GameLog.addInfoLog("Retry button pressed from you lost screen");
        System.out.println("Retry button pressed!");
        popupControl.hide();
        mainController.setButtonsDisabled(false);
        MainMenuController.loadView();
    }

    /**
     * Handles clicking of the try again button
     *
     * @param event the click of the button
     */
    @FXML
    private void handleTryAgainButton(ActionEvent event) {
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
    public static void loadPopup(IviewController controller) {
        Launcher.loadPopup(controller, Class.class.getResource("/data/gui/pages/YouLost.fxml"));
    }

    /**
     * Set the controller of the main view.
     *
     * @param controller the main view controller.
     */
    @Override
    public void setMainViewController(IviewController controller) {
        mainController = controller;
    }

    /**
     * Set the popupControl of this popup.
     *
     * @param popupControl PopupControl.
     */
    @Override
    public void setPopupControl(PopupControl popupControl) {
        this.popupControl = popupControl;
    }
}
