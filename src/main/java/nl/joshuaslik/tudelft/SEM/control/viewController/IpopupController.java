/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.viewController;

import javafx.scene.control.PopupControl;

/**
 * Interface of all popups.
 *
 * @author faris
 */
public interface IpopupController {

    /**
     * Set the controller class of the main view.
     *
     * @param controller the controller class of the main view.
     */
    public void setMainViewController(IviewController controller);

    /**
     * Set the PopupControl class.
     *
     * @param popupControl PopupControl class.
     */
    public void setPopupControl(PopupControl popupControl);
}
