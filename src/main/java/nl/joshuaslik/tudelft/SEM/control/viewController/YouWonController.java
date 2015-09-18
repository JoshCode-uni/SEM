package nl.joshuaslik.tudelft.SEM.control.viewController;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PopupControl;
import javafx.scene.layout.Pane;
import nl.joshuaslik.tudelft.SEM.Launcher;
import utility.GameLog;

/**
 * Controller for the You Won screen.
 *
 * @author Bastijn
 */
public class YouWonController implements IpopupController {

    @FXML
    private Pane pane;

    @FXML
    private Button mainMenuButton, nextLevelButton;

    private Scene newScene;

    private IviewController mainController;
    private PopupControl popupControl;

    /**
     * Start the pop-up when player has won
     */
    public void start() {

    }

    /**
     * Handles clicking of the main menu button
     *
     * @param event the click of the button
     */
    @FXML
    private void handleMainMenuButton(ActionEvent event) {
        GameLog.addInfoLog("Main menu button pressed from you won screen");
        System.out.println("Main Menu button pressed!");
        popupControl.hide();
        mainController.setButtonsDisiabled(false);
    }

    /**
     * Handles clicking of the next level button
     *
     * @param event the click of the button
     */
    //Needs to change
    @FXML
    private void handleNextLevelButton(ActionEvent event) {
        GameLog.addInfoLog("Next level button pressed from you won screen");
        System.out.println("Next level button pressed!");
        mainController.setButtonsDisiabled(false);
        popupControl.hide();
        GameController.loadView();
    }

    public static void loadPopup(IviewController controller) {
        Launcher.loadPopup(controller, Class.class.getResource("/data/gui/pages/YouWon.fxml"));
    }

    @Override
    public void setMainViewController(IviewController controller) {
        mainController = controller;
    }

    @Override
    public void setPopupControl(PopupControl popupControl) {
        this.popupControl = popupControl;
    }

}
