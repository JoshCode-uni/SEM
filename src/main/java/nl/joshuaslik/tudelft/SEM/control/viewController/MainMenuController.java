package nl.joshuaslik.tudelft.SEM.control.viewController;

import java.util.ArrayList;
import nl.joshuaslik.tudelft.SEM.Launcher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import nl.joshuaslik.tudelft.SEM.model.container.Levels;

/**
 * Controller for the main menu UI.
 *
 * @author Bastijn
 */
public class MainMenuController implements IviewController {

	@FXML
	private static Pane pane;

	@FXML
	private Button playButton, chooseLevelButton, optionsButton, quitButton;

	@FXML
	private Text totalScore;

	private static ArrayList<Integer> scoresPerLevel = new ArrayList<>(Levels.amountOfLevels());

	static {
		for (int i = 0; i < Levels.amountOfLevels(); i++) {
			scoresPerLevel.add(0);
		}
	}

	public void initialize() {
		int score = 0;
		for (int e : scoresPerLevel) {
			score += e;
		}
		totalScore.setText("Total Score: " + score);
	}

	/**
	 * Handles clicking of the start button
	 *
	 * @param event the click of the button
	 */
	@FXML
	protected void handlePlayButton(ActionEvent event) {
		System.out.println("Play button pressed!");
		GameController.loadView();
	}

	/**
	 * Handles clicking of the choose level button
	 *
	 * @param event the click of the button
	 */
	@FXML
	protected void handleChooseLevelButton(ActionEvent event) {
		System.out.println("Choose Level button pressed!");
		ChooseLevelController.loadView();
	}

	/**
	 * Handles clicking of the options button
	 *
	 * @param event the click of the button
	 */
	@FXML
	protected void handleOptionsButton(ActionEvent event) {
		System.out.println("Options button pressed!");
		OptionsController.loadView();
	}

	/**
	 * Handles clicking of the quit button
	 *
	 * @param event the click of the button
	 */
	@FXML
	protected void handleQuitButton(ActionEvent event) {
		System.out.println("Quit button pressed!");
		System.exit(0);
	}

	public static void loadView() {
		Launcher.loadView(Class.class.getResource("/data/gui/pages/MainMenu.fxml"));
	}

	@Override
	public void start(Scene scene) {

	}

	public static void setScore(int score, int level) {
		if (scoresPerLevel.get(level) < score) {
			MainMenuController.scoresPerLevel.set(level, score);
		}
	}
}
