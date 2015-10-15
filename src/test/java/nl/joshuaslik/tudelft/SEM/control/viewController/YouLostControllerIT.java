/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.viewController;

import nl.joshuaslik.tudelft.SEM.JavaFxJUnit4ClassRunner;
import nl.joshuaslik.tudelft.SEM.Launcher;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Faris
 */
@RunWith(JavaFxJUnit4ClassRunner.class)
public class YouLostControllerIT {

    /**
     * Controller of the main menu.
     */
    private YouLostController controller;

    /**
     * Make sure the main menu is always loaded first and set the right controller.
     */
    @Before
    public final void startGame() {
        GameController.loadView();
        YouLostController.loadPopup(Launcher.getController());
        controller = (YouLostController) Launcher.getPopupController();
        assert controller != null;
    }
    
    /**
     * Test of handleMainMenuButton method, of class YouLostController.
     */
    @Test
    public void testHandleMainMenuButton() {
        controller.fireMainMenuButton();
        assertTrue(Launcher.getController() instanceof MainMenuController);
    }

    /**
     * Test of handleTryAgainButton method, of class YouLostController.
     */
    @Test
    public void testHandleTryAgainButton() {
        controller.fireTryAgainButton();
        assertTrue(Launcher.getController() instanceof GameController);
    }
}
