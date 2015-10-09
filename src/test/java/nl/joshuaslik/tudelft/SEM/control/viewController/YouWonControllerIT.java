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
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 *
 * @author Faris
 */
@RunWith(JavaFxJUnit4ClassRunner.class)
public class YouWonControllerIT {
    
    /**
     * Controller of the main menu.
     */
    private YouWonController controller;

    /**
     * Make sure the main menu is always loaded first and set the right controller.
     */
    @Before
    public final void startGame() {
        GameController.loadView();
        YouWonController.loadPopup(Launcher.getController());
        controller = (YouWonController) Launcher.getPopupController();
        assert controller != null;
    }
    /**
     * Test of handleMainMenuButton method, of class YouWonController.
     */
    @Test
    public void testHandleMainMenuButton() {
        controller.getMainMenuButton().fire();
        assertTrue(Launcher.getController() instanceof MainMenuController);
    }

    /**
     * Test of handleNextLevelButton method, of class YouWonController.
     */
    @Test
    public void testHandleNextLevelButton() {
        controller.getNextLevelButton().fire();
        assertTrue(Launcher.getController() instanceof GameController);
    }
}
