package nl.joshuaslik.tudelft.SEM.control;

import java.io.IOException;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

public class MainMenu {
	
	/**
	 * Loads the Main Menu
	 * @throws IOException thrown when FXML is not parsed
	 */
	public static void start() throws IOException {
		Pane scene = (Pane) FXMLLoader.load(Class.class.getResource("/data/gui/pages/MainMenu.fxml"));
		/*Main.setCenter(scene);
		Main.setBottom(null);
		Main.setTop(null);
		
		Main.rootLayout.getStyleClass().removeAll("maingame");
		
		Main.rootLayout.getStyleClass().add("mainmenu");*/
	}
}
