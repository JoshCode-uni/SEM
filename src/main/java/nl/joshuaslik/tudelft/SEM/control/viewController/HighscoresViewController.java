package nl.joshuaslik.tudelft.SEM.control.viewController;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.PopupControl;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import nl.joshuaslik.tudelft.SEM.Launcher;
import nl.joshuaslik.tudelft.SEM.control.HighscoreHandler;
import nl.joshuaslik.tudelft.SEM.model.container.Highscore;
import nl.joshuaslik.tudelft.SEM.utility.GameLog;

/**
 * The highscores view controller.
 * @author Faris
 */
public class HighscoresViewController implements IpopupController {

    @FXML
    private TableView<Highscore> highscoresTable;
    @FXML
    private TableColumn<Highscore, Number> score;
    @FXML
    private TableColumn<Highscore, String> name;

    private PopupControl popupControl;

    /**
     * Add content to the highscores table.
     */
    @FXML
    private void initialize() {
        score.setCellValueFactory(cellData
                -> new SimpleIntegerProperty(cellData.getValue().getScore()));
        name.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getUser()));
        highscoresTable.setItems(FXCollections.observableArrayList(
                HighscoreHandler.getInstance().getHighscores()));
    }

    /**
     * Handles clicking of the return button
     */
    @FXML
    private void handleReturnButton() {
        GameLog.addInfoLog("Return button pressed from highscores screen");
        popupControl.hide();
        MainMenuViewController.loadView();
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
