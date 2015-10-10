/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.viewController;

import static org.junit.Assert.assertTrue;

import nl.joshuaslik.tudelft.SEM.JavaFxJUnit4ClassRunner;
import nl.joshuaslik.tudelft.SEM.Launcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.runner.RunWith;

/**
 * @author Faris
 */
@RunWith(JavaFxJUnit4ClassRunner.class)
public class GameControllerIT {
    
    /**
     * Make sure system.exit() won't actually stop the application.
     */
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    /**
     * Controller of the main menu.
     */
    private GameController controller;

    /**
     * Make sure the main menu is always loaded first and set the right controller.
     */
    @Before
    public final void startGame() {
        MainMenuController.loadView();
        ((MainMenuController) Launcher.getController()).getPlayButton().fire();
        controller = (GameController) Launcher.getController();
        assert controller != null;
    }

    /**
     * Test of handleQuitButton method, of class MainMenuController.
     * System.exit(0) will be called, because we are using the "ExpectedSystemExit" rule, we expect
     * a runtime exception.
     * @throws java.lang.InterruptedException ignore.
     */
    @Test(expected=RuntimeException.class)
    public void testHandleQuitButton() throws InterruptedException {
        exit.expectSystemExitWithStatus(0);
        controller.getQuitButton().fire();
    }

    /**
     * Test of handleMainMenuButton method, of class GameController.
     */
    @Test
    public void testHandleMainMenuButton() {
        controller.getMainMenuButton().fire();
        assertTrue(Launcher.getController() instanceof MainMenuController);
    }
}
