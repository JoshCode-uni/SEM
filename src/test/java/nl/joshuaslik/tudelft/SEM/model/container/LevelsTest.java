package nl.joshuaslik.tudelft.SEM.model.container;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.IGameObjects;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ICircleViewObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.when;

/**
 * Test the levels class.
 * @author Faris
 */
@RunWith(MockitoJUnitRunner.class)
public class LevelsTest {

    @Mock
    IGameObjects gameObjects;

    @Mock
    ICircleViewObject circle, circle2;

    /**
     * Make sure we start with levels unlocked == 0.
     */
    @Before
    public void initialize() {
        Levels.setCurrentLevel(0);
        Levels.setUnlockedLevel(0);
    }

    /**
     * Test the amountOfLevels method.
     */
    @Test
    public void testAmountOfLevels() {
        assertEquals(Levels.amountOfLevels(), 4);
        assertNotEquals(Levels.amountOfLevels(), 7);
    }

    /**
     * Test the getLevel [0] method.
     */
    @Test
    public void testGetLevel0() {
        when(gameObjects.makeCircle(200, 500, 20)).thenReturn(circle);
        assertEquals(Levels.getLevelObjects(gameObjects).size(), 1);
    }

    /**
     * Test the next level method.
     */
    @Test
    public void testNextLevel() {
        Levels.setCurrentLevel(0);
        Levels.nextLevel();
        assertEquals(Levels.getCurrentLevel(), 1);
    }

    /**
     * Test the last level.
     */
    @Test
    public void testLastLevel() {
        Levels.setCurrentLevel(4);
        Levels.nextLevel();
        assertEquals(Levels.getCurrentLevel(), 1);
    }

    /**
     * Test theunlockedLevels method.
     */
    @Test
    public void testUnlockedLevel() {
        assertEquals(0, Levels.getUnlockedLevel());
        Levels.nextLevel();
        assertEquals(1, Levels.getUnlockedLevel());
    }
}
