package nl.joshuaslik.tudelft.SEM.model.container;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.IGameObjects;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ICircleViewObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class LevelsTest {
	
	@Mock
	IGameObjects gameObjects;
	
	@Mock
	ICircleViewObject circle, circle2;
	

	@Test
	public void testAmountOfLevels() {
		assertEquals(Levels.amountOfLevels(), 4);
		assertNotEquals(Levels.amountOfLevels(), 7);
	}
	
	@Test
	public void testGetLevel0() {
		when(gameObjects.makeCircle(200, 500, 20)).thenReturn(circle);
		assertEquals(Levels.getLevelObjects(0, gameObjects).size(), 1);
	}
	
	@Test
	public void testGetLevel1() {
	}
	
	@Test
	public void testNextLevel() {
		Levels.setCurrentLevel(0);
		Levels.nextLevel();
		assertEquals(Levels.getCurrentLevel(), 1);
	}
	
	@Test
	public void testLastLevel() {
		Levels.setCurrentLevel(4);
		Levels.nextLevel();
		assertEquals(Levels.getCurrentLevel(), 1);
	}
	
	@Test
	public void testUnlockedLevel() {
		assertEquals(Levels.getUnlockedLevel(), 1);
		Levels.nextLevel();
		assertEquals(Levels.getUnlockedLevel(), 2);
	}
}
