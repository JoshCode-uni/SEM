package nl.joshuaslik.tudelft.SEM.control.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PopupControl;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import nl.joshuaslik.tudelft.SEM.Launcher;
import nl.joshuaslik.tudelft.SEM.utility.GameLog;

public class highscoresController implements IpopupController{

    @FXML
    private Button returnButton;

	//@FXML
	//private TableView<User> highscoresTable;
	//@FXML
	//private TableColumn<User, Int> score;
	//@FXML
	//private TableColumn<User, String> name;
    
    private IviewController mainController;
    private PopupControl popupControl;

    /**
     * Handles clicking of the return button
     */
    @FXML
    private void handleReturnButton() {
        GameLog.addInfoLog("Return button pressed from highscores screen");
        System.out.println("Return button pressed!");
        popupControl.hide();
        MainMenuController.loadView();
    }

   /**
     * Load this popup.
     *
     * @param controller the controller class of the currently loaded view.
     */
    public static void loadPopup(final IviewController controller) {
        Launcher.loadPopup(controller, Class.class.getResource("/data/gui/pages/Highscores.fxml"));
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
}
