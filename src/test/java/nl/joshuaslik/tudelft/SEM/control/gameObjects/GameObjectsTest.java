package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.isA;

import java.io.InputStream;

import static org.mockito.Mockito.verify;

import nl.joshuaslik.tudelft.SEM.control.GameLoop;
import nl.joshuaslik.tudelft.SEM.control.viewController.Keyboard;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ILineViewObject;

@RunWith(MockitoJUnitRunner.class)
public class GameObjectsTest {
	
	@Mock
	GameLoop gl;
	
	@Mock
	Keyboard kb;
	
	@Mock
	ILineViewObject topL,botL,leftL,rightL;
	
//	@Mock
	GameObjects gameObjects;
	
	@Mock
	InputStream is;
	
	@Mock 
	Projectile pj;
	
	@Mock
	Bubble bl;
	
	@Mock
	Player p;
	
	@Mock
	IPrepareUpdateable prepUp;
	
	@Mock
	IUpdateable up;
	
	@Mock
	ICollider col;
	
	@Mock 
	IIntersectable intersectable;
	
	GameObjects spyGameObjects;
	
	/**
     * Setup the mocks.
     */
	@Before
	public void setUp() {
		gameObjects = new GameObjects(true,gl, 0, 100, 100, 0, 0, kb);
		spyGameObjects = Mockito.spy(gameObjects);
	}

	/**
	 * Tests if the gameObjects are updated correctly.
	 */
	@Test
	public void testUpdate() {
		spyGameObjects.getPrepareUpdateable().add(prepUp);
		spyGameObjects.getUpdateable().add(up);
		spyGameObjects.getCollider().add(col);
		spyGameObjects.getIntersectable().add(intersectable);
		spyGameObjects.update(0);
		verify(prepUp).prepareUpdate(0);
		verify(up).update(0);
		verify(col).checkCollision(intersectable,0);
	//	fail("Not yet implemented");
	}

	/**
	 * Tests if all bubbles are indeed destroyed.
	 */
	@Test
	public void testAllBubblesDestroyed() {
		assertTrue(gameObjects.allBubblesDestroyed());
		gameObjects.addBubbles(bl);
		assertFalse(gameObjects.allBubblesDestroyed());
	}

	/**
	 * Tests if a projectile is added correctly.
	 */
	@Test
	public void testAddProjectile() {
		spyGameObjects.addProjectile(pj);
		verify(spyGameObjects).addObject(pj);
	}
	
	/**
	 * Tests if the projectile is removed correctly.
	 */
	@Test
	public void testRemoveProjectile() {
		spyGameObjects.addProjectile(pj);
		spyGameObjects.removeProjectile();
		verify(spyGameObjects).removeObject(isA(Projectile.class));
	}

	/**
	 *
	 */
	@Test
	public void testHandleBubbleSplit() {
		fail("Not yet implemented");
	}

	/**
	 * Tests if points are correctly added.
	 */
	@Test
	public void testAddPoints() {
		assertEquals(gameObjects.getScore(),0);
		gameObjects.addPoints(50);
		assertEquals(gameObjects.getScore(),50);
	}

	/**
	 * Tests if a life is added correctly.
	 */
	@Test
	public void testAddLife() {
		gameObjects.addLife();
		verify(gl).addLife();
	}

	/**
	 * Tests if the bubbles left are updated correctly.
	 */
	@Test
	public void testBubblesLeft() {
		assertEquals(gameObjects.bubblesLeft(),0);
		gameObjects.addBubbles(bl);
		assertEquals(gameObjects.bubblesLeft(),1);
	}
	
	/**
	 * Tests if a circle is made correctly.
	 */
	@Test
	public void testMakeCircle() {
		gameObjects.makeCircle(100,200,25);
		verify(gl).makeCircle(100, 200, 25);
	}
	
	/**
	 * Tests if a line is made correctly.
	 */
	@Test
	public void testMakeLine() {
		gameObjects.makeLine(25, 0, 100, 50);
		verify(gl).makeLine(25, 0, 100, 50);
	}
	
	/**
	 * Tests if an iamge is created correctly.
	 */
	@Test
	public void testMakeImage() {
		gameObjects.makeImage(is, 75, 25);
		verify(gl).makeImage(is, 25, 75);
	}
	
	/**
	 * Tests if it is correctly updated if the player dies.
	 */
	@Test
	public void testPlayerDied() {
		gameObjects.playerDied();
		verify(gl).playerDied();
	}
	
	/**
	 * Tests if the projectile is correctly updated.
	 */
	@Test
	public void testHasProjectile() {
		assertFalse(gameObjects.hasProjectile());
		gameObjects.addProjectile(pj);
		assertTrue(gameObjects.hasProjectile());
	}
	
	/**
	 * 
	 */
	@Test
	public void testHandleModifierCollision() {
		fail("Not correctly implemented");
	}

}
