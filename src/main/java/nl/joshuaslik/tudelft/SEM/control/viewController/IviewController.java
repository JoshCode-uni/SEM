/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.viewController;

import javafx.scene.Scene;

/**
 * Interface of all views (not popups).
 *
 * @author faris
 */
public interface IviewController {

    /**
     * Start will be called after the fxml file has been initialized.
     */
    public void start(Scene scene);

    /**
     * Dis/enable the buttons of this view.
     *
     * @param disabled if the buttons should be disabled or enabled.
     */
    public void setButtonsDisiabled(boolean disabled);
}
