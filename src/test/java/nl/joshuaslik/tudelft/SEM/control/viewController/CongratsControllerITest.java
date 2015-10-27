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
 * Test the congrats controller class with an integration test.
 * @author Faris
 */
@RunWith(JavaFxJUnit4ClassRunner.class)
public class CongratsControllerITest {

    /**
     * Test of handleMainMenuButton method, of class CongratsViewController.
     */
    @Test
    public void handleMainMenuButton() {
        GameViewController.loadView();
        CongratsViewController.loadPopup(Launcher.getController());
        CongratsViewController controller = (CongratsViewController) Launcher.getPopupController();
        controller.fireMainMenuButton();
        assertTrue(Launcher.getController() instanceof MainMenuViewController);
    }
}
