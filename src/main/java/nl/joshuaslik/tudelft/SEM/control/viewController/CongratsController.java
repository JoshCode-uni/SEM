package nl.joshuaslik.tudelft.SEM.control.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PopupControl;
import javafx.scene.layout.Pane;
import nl.joshuaslik.tudelft.SEM.Launcher;
import nl.joshuaslik.tudelft.SEM.utility.GameLog;

public class CongratsController implements IpopupController{

    @FXML
    private Pane pane;

    @FXML
    private Button mainMenuButton;

    private IviewController mainController;
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
    private void handleMainMenuButton(ActionEvent event) {
        GameLog.addInfoLog("Main menu button pressed from congrats screen");
        System.out.println("Main Menu button pressed!");
        popupControl.hide();
        MainMenuController.loadView();
    }


    public static void loadPopup(IviewController controller) {
        Launcher.loadPopup(controller, Class.class.getResource("/data/gui/pages/Congrats.fxml"));
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
