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
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * You won controller integration test class.
 * @author Faris
 */
@RunWith(JavaFxJUnit4ClassRunner.class)
public class YouWonControllerITest {

    /**
     * Controller of the main menu.
     */
    private YouWonViewController controller;

    /**
     * Make sure the main menu is always loaded first and set the right controller.
     */
    @Before
    public final void startGame() {
        GameViewController.loadView();
        YouWonViewController.loadPopup(Launcher.getController());
        controller = (YouWonViewController) Launcher.getPopupController();
        assert controller != null;
    }

    /**
     * Test of handleMainMenuButton method, of class YouWonViewController.
     */
    @Test
    public void testHandleMainMenuButton() {
        controller.fireMainMenuButton();
        assertTrue(Launcher.getController() instanceof MainMenuViewController);
    }

    /**
     * Test of handleNextLevelButton method, of class YouWonViewController.
     */
    @Test
    public void testHandleNextLevelButton() {
        controller.fireNextLevelButton();
        assertTrue(Launcher.getController() instanceof GameViewController);
    }
}
