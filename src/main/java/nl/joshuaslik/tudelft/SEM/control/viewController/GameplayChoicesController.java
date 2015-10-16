package nl.joshuaslik.tudelft.SEM.control.viewController;

import nl.joshuaslik.tudelft.SEM.Launcher;
import nl.joshuaslik.tudelft.SEM.control.GameLoop;
import nl.joshuaslik.tudelft.SEM.model.container.GameInfo;
import nl.joshuaslik.tudelft.SEM.model.container.Levels;
import nl.joshuaslik.tudelft.SEM.model.container.PlayerMode;
import nl.joshuaslik.tudelft.SEM.utility.GameLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PopupControl;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.util.StringConverter;

public class GameplayChoicesController implements IpopupController {

	@FXML
	private Button startButton, returnButton;

	@FXML
	private ChoiceBox<String> SingleMultiChoice, CoopVersusChoice;

	@FXML
	private TextField user1, user2;

	private IviewController mainController;
	private PopupControl popupControl;

	/**
	 * Initialize things on this pop-up
	 */
	public void start(final Scene scene) {

	}

	/**
	 * Handles clicking on the return button
	 */
	@FXML
	private void handleReturnButton() {
		GameLog.addInfoLog("Return button pressed from gameplay screen");
		System.out.println("Return button pressed!");
		popupControl.hide();
		mainController.setButtonsDisabled(false);
	}

	/**
	 * Handles clicking of the start button
	 */
	@FXML
	private void handleStartButton() {
		GameLog.addInfoLog("Play button pressed from gameplay screen");
		System.out.println("Play button pressed!");
		prepareGame();
	}

	/**
	 * Handles the choices of the player for the instance of the game
	 */
	private void prepareGame() {
		String username1 = user1.getText();
		String username2 = user2.getText();

		if (!(SingleMultiChoice.getValue().equals(null)) && !(username1.equals(""))) {
			if (SingleMultiChoice.getValue().equals("Single Player") && !(username1.equals(""))) {
				GameInfo.getInstance().setPlayerMode(PlayerMode.SINGLE_PLAYER);
			}

			if (SingleMultiChoice.getValue().equals("Multiplayer") && !(CoopVersusChoice.getValue() == (null))
					&& !(username2.equals(""))) {
				if (CoopVersusChoice.getValue().equals("Co-op")) {
					GameInfo.getInstance().setPlayerMode(PlayerMode.MULTI_PLAYER_COOP);

				}
				if (CoopVersusChoice.getValue().equals("Versus")) {
					GameInfo.getInstance().setPlayerMode(PlayerMode.MULTI_PLAYER_VERSUS);

				}
				GameInfo.getInstance().setPlayerName(1, username2);
			}

			GameInfo.getInstance().setPlayerName(0, username1);
			popupControl.hide();
			mainController.setButtonsDisabled(false);
			GameController.loadView();
		}
	}

	/**
	 * Load the Gameplay popup
	 *
	 * @param controller
	 *            the controller class of the currently loaded view.
	 */
	public static void loadPopup(final IviewController controller) {
		Launcher.loadPopup(controller,
				Class.class.getResource("/data/gui/pages/GameplayChoices.fxml"));
	}

	@Override
	public void setMainViewController(IviewController controller) {
		mainController = controller;

	}

	@Override
	public void setPopupControl(PopupControl popupControl) {
		this.popupControl = popupControl;
		SingleMultiChoice.setItems(FXCollections.observableArrayList(
				"Single Player", "Multiplayer"));
		CoopVersusChoice.setItems(FXCollections.observableArrayList("Co-op",
				"Versus"));

		// if (SingleMultiChoice.getValue().equals("Single Player")) {
		// CoopVersusChoice.setDisable(true);
		// }
	}

}
