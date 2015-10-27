/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.viewController;

import nl.joshuaslik.tudelft.SEM.JavaFxJUnit4ClassRunner;
import nl.joshuaslik.tudelft.SEM.Launcher;
import nl.joshuaslik.tudelft.SEM.model.container.Levels;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.runner.RunWith;

/**
 * This is an integration test for all buttons of the main menu.
 *
 * @author Faris
 */
@RunWith(JavaFxJUnit4ClassRunner.class)
public class MainMenuControllerITest {

    /**
     * Make sure system.exit() won't actually stop the application.
     */
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    /**
     * Controller of the main menu.
     */
    private MainMenuViewController controller;

    /**
     * Make sure the main menu is always loaded first and set the right controller.
     */
    @Before
    @SuppressWarnings("checkstyle:magicnumber")
    public final void startGame() {
        MainMenuViewController.loadView();
        controller = (MainMenuViewController) Launcher.getController();
        assert controller != null;

        Levels.setUnlockedLevel(5);
    }

    /**
     * Test of handlePlayButton method, of class MainMenuViewController.
     */
    @Test
    public final void testHandlePlayButton() {
        controller.firePlayButton();
        assertTrue(Launcher.getController() instanceof GameViewController);
    }

    /**
     * Test of handleChooseLevelButton method, of class MainMenuViewController.
     */
    @Test
    public final void testHandleChooseLevelButton() {
        assertFalse(controller.getChooseLevelBox().isVisible());
        controller.fireChooseLevelButton();
        assertTrue(controller.getChooseLevelBox().isVisible());
    }

    /**
     * Test of handleOptionsButton method, of class MainMenuViewController.
     */
    @Test
    public final void testHandleOptionsButton() {
        assertFalse(controller.getOptionsPane().isVisible());
        controller.fireOptionsButton();
        assertTrue(controller.getOptionsPane().isVisible());
    }

    /**
     * Test of handleQuitButton method, of class MainMenuViewController. System.exit(0) will be called,
     * because we are using the "ExpectedSystemExit" rule, we expect a runtime exception.
     */
    @Test(expected = RuntimeException.class)
    public final void testHandleQuitButton() {
        exit.expectSystemExitWithStatus(0);
        controller.fireQuitButton();
    }

    /**
     * Test of handleLevel1Button method, of class MainMenuViewController.
     */
    @Test
    public final void testHandleLevel1Button() {
        controller.fireLevel1Button();
        assertTrue(Launcher.getController() instanceof GameViewController);
        assertEquals(0, Levels.getCurrentLevel());
    }

    /**
     * Test of handleLevel2Button method, of class MainMenuViewController.
     */
    @Test
    public final void testHandleLevel2Button() {
        controller.fireLevel2Button();
        assertTrue(Launcher.getController() instanceof GameViewController);
        assertEquals(1, Levels.getCurrentLevel());
    }

    /**
     * Test of handleLevel3Button method, of class MainMenuViewController.
     */
    @Test
    public final void testHandleLevel3Button() {
        controller.fireLevel3Button();
        assertTrue(Launcher.getController() instanceof GameViewController);
        assertEquals(2, Levels.getCurrentLevel());
    }

    /**
     * Test of handleLevel4Button method, of class MainMenuViewController.
     */
    @Test
    @SuppressWarnings("checkstyle:magicnumber")
    public final void testHandleLevel4Button() {
        controller.fireLevel4Button();
        assertTrue(Launcher.getController() instanceof GameViewController);
        assertEquals(3, Levels.getCurrentLevel());
    }

    /**
     * Test of handleLevel5Button method, of class MainMenuViewController.
     */
    @Test
    @SuppressWarnings("checkstyle:magicnumber")
    public final void testHandleLevel5Button() {
        controller.fireLevel5Button();
        assertTrue(Launcher.getController() instanceof GameViewController);
        assertEquals(4, Levels.getCurrentLevel());
    }
}
