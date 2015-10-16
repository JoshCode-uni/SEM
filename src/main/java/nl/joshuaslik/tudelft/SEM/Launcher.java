/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PopupControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import nl.joshuaslik.tudelft.SEM.control.viewController.IpopupController;
import nl.joshuaslik.tudelft.SEM.control.viewController.IviewController;
import nl.joshuaslik.tudelft.SEM.utility.GameLog;

/**
 * The launcher of the application.
 *
 * @author faris
 */
public class Launcher extends Application {

    public static final int SCREEN_WIDTH = 600;
    private static final int SCREEN_HEIGHT = 600;
    public static final double GRAVITY = 700;
    public static final double ENERGY = GRAVITY * SCREEN_HEIGHT; // E = .5v2 + gh
    private static final BorderPane BP = new BorderPane();
    private static Stage stage;

    // These controllers are only intended to be used for testing purposes:
    private static IviewController controller;
    private static IpopupController popupController;
    private static final Object LOCK = new Object();
    private static boolean initialized = false;
    private static boolean hideViewForTesting = false;

    /**
     * Start up the game.
     */
    @Override
    public void start(final Stage primaryStage) {
        GameLog.constructor();
        loadView(getClass().getResource("/data/gui/pages/MainMenu.fxml"));
        Scene scene = new Scene(BP);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        if (!hideViewForTesting) {
            primaryStage.show();
        }
        Launcher.stage = primaryStage;
        synchronized (LOCK) {
            initialized = true;
        }
    }

    /**
     * Load the fxml file for the screen.
     *
     * @param fxmlURL URL of the FXML file.
     * @return the controller of the loaded view.
     */
    public static IviewController loadView(final URL fxmlURL) {
        GameLog.addInfoLog("Load view: " + fxmlURL.getPath());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(fxmlURL);
        try {
            Pane pane = loader.load();
            IviewController res = ((IviewController) loader.getController());
            res.start(BP.getScene());
            BP.setCenter(pane);
            controller = res;
            return res;
        } catch (IOException ex) {
            GameLog.addErrorLog("Failed to load fxml file: " + fxmlURL.toString());
            GameLog.addErrorLog(ex.getMessage());
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, "Failed to load fxml file: " + fxmlURL.toString(), ex);
            return null;
        }
    }

    /**
     * Load a popup screen.
     *
     * @param mainViewController the controller of the current view.
     * @param fxmlURL            the URL of the FXML file of the popup.
     */
    public static void loadPopup(final IviewController mainViewController, final URL fxmlURL) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(fxmlURL);
        mainViewController.setButtonsDisabled(true);
        try {
            Pane pane = loader.load();
            PopupControl popup = new PopupControl();
            popup.getScene().setRoot(pane);
            popup.show(stage);
            IpopupController popupContrl = (IpopupController) loader.getController();
            popupContrl.setPopupControl(popup);
            popupContrl.setMainViewController(mainViewController);
            Launcher.popupController = popupContrl;
        } catch (IOException ex) {
            GameLog.addErrorLog("Failed to load fxml file: " + fxmlURL.toString());
            GameLog.addErrorLog(ex.getMessage());
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, 
                    "Failed to load fxml file: " + fxmlURL.toString(), ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        launch(args);
    }

    /**
     * Get the controller of the last loaded view.
     * FOR TESTING PURPSOSES ONLY!
     *
     * @return the view controller
     */
    public static IviewController getController() {
        return controller;
    }

    /**
     * Get the controller of the last loaded popup.
     * FOR TESTING PURPSOSES ONLY!
     *
     * @return the popup controller
     */
    public static IpopupController getPopupController() {
        return popupController;
    }

    /**
     * Wait till the initial view is initialized.
     * FOR TESTING PURPSOSES ONLY!
     */
    public static void waitTillInitialized() {
        while (true) {
            synchronized (LOCK) {
                if (initialized) {
                    break;
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Hide the view for integration testing.
     * FOR TESTING PURPSOSES ONLY!
     *
     * @param hideViewForTesting if the view must be hidden
     */
    public static void setHideViewForTesting(boolean hideViewForTesting) {
        Launcher.hideViewForTesting = hideViewForTesting;
    }
}
