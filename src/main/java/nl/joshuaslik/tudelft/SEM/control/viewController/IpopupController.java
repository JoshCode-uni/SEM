/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.viewController;

import javafx.scene.control.PopupControl;

/**
 *
 * @author faris
 */
public interface IpopupController {
	public void setMainViewController(IviewController controller);
	public void setPopupControl(PopupControl popupControl);
}
