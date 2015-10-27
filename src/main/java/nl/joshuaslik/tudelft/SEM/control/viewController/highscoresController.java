package nl.joshuaslik.tudelft.SEM.control.viewController;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PopupControl;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import nl.joshuaslik.tudelft.SEM.Launcher;
import nl.joshuaslik.tudelft.SEM.control.Highscore;
import nl.joshuaslik.tudelft.SEM.utility.GameLog;

public class HighscoresController implements IpopupController {

	@FXML
	private Button returnButton;

	@FXML
	private TableView<Highscore> highscoresTable;
	@FXML
	private TableColumn<Highscore, Number> score;
	@FXML
	private TableColumn<Highscore, String> name;

	private IviewController mainController;
	private PopupControl popupControl;

	@FXML
	private void initialize() {
		score.setCellValueFactory(cellData -> {
			int index = highscoresTable.getItems().indexOf(cellData.getValue());
			return new SimpleIntegerProperty(cellData.getValue().getScores().get(index));
		});
		name.setCellValueFactory(cellData -> {
			int index = highscoresTable.getItems().indexOf(cellData.getValue());
			return new SimpleStringProperty(cellData.getValue().getUsers().get(index));
		});
//		highscoresTable.setItems(null);
	}

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
