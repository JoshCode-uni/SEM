/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.viewController;

import nl.joshuaslik.tudelft.SEM.JavaFxJUnit4ClassRunner;
import nl.joshuaslik.tudelft.SEM.Launcher;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Faris
 */
@RunWith(JavaFxJUnit4ClassRunner.class)
public class CongratsControllerIT {

    /**
     * Test of handleMainMenuButton method, of class CongratsController.
     */
    @Test
    public void handleMainMenuButton() {
        GameController.loadView();
        CongratsController.loadPopup(Launcher.getController());
        CongratsController controller = (CongratsController) Launcher.getPopupController();
        controller.fireMainMenuButton();
        assertTrue(Launcher.getController() instanceof MainMenuController);
    }
}
