package nl.joshuaslik.tudelft.SEM.control.viewController;

import nl.joshuaslik.tudelft.SEM.Launcher;
import nl.joshuaslik.tudelft.SEM.model.container.GameInfo;
import nl.joshuaslik.tudelft.SEM.model.container.PlayerMode;
import nl.joshuaslik.tudelft.SEM.utility.GameLog;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PopupControl;
import javafx.scene.control.TextField;

public class GameplayChoicesController implements IpopupController {

    @FXML
    private Label player2Label, gameModeLabel;

    @FXML
    private ComboBox SingleMultiChoice, coopVersusChoice;

    @FXML
    private TextField user1, user2;

    private IviewController mainController;
    private PopupControl popupControl;

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
        if (!(username1.equals(""))) {
            if ((username1.equals(""))) {
                return;
            }
            switch (SingleMultiChoice.getSelectionModel().getSelectedIndex()) {
                case 0:
                    GameInfo.getInstance().setPlayerMode(PlayerMode.SINGLE_PLAYER);
                    break;
                case 1:
                    if (!username2.equals("")) {
                        GameInfo.getInstance().setPlayerName(1, username2);
                        if (coopVersusChoice.getValue().equals("Co-op")) {
                            GameInfo.getInstance().setPlayerMode(PlayerMode.MULTI_PLAYER_COOP);

                        }
                        if (coopVersusChoice.getValue().equals("Versus")) {
                            GameInfo.getInstance().setPlayerMode(PlayerMode.MULTI_PLAYER_VERSUS);
                        }
                    } else {
                        return;
                    }
                    break;
                default:
                    throw new AssertionError();
            }
            GameInfo.getInstance().setPlayerName(0, username1);
            popupControl.hide();
            mainController.setButtonsDisabled(false);
        }
    }

    /**
     * Load the Gameplay popup
     *
     * @param controller the controller class of the currently loaded view.
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
        coopVersusChoice.setItems(FXCollections.observableArrayList("Co-op",
                "Versus"));
        SingleMultiChoice.addEventHandler(ActionEvent.ACTION, onSelected);
        SingleMultiChoice.getSelectionModel().select(0);
        coopVersusChoice.getSelectionModel().select(0);
        user2.setVisible(false);
        player2Label.setVisible(false);
        gameModeLabel.setVisible(false);
        coopVersusChoice.setVisible(false);
    }

    /**
     * This is an event handler class definition, which will be triggered when an items in the
     * dropdown list is selected.
     */
    private final EventHandler onSelected = (EventHandler<ActionEvent>) (ActionEvent t) -> {
        boolean visible = SingleMultiChoice.getSelectionModel().getSelectedIndex() == 1;
        user2.setVisible(visible);
        player2Label.setVisible(visible);
        gameModeLabel.setVisible(visible);
        coopVersusChoice.setVisible(visible);
    };

}
