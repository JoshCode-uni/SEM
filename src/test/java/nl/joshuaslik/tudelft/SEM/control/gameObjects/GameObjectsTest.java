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
	
	@Before
	public void setUp() throws Exception {
/*		when(gameObjects.makeLine(0, 100, 100, 100)).thenReturn(topL);
		when(gameObjects.makeLine(0, 100, 0, 0)).thenReturn(leftL);
		when(gameObjects.makeLine(100, 100, 100, 0)).thenReturn(rightL);
		when(gameObjects.makeLine(0, 0, 100, 0)).thenReturn(botL);	*/
		gameObjects = new GameObjects(true,gl, 0, 100, 100, 0, 0, kb);
		spyGameObjects = Mockito.spy(gameObjects);
	}

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

	@Test
	public void testAllBubblesDestroyed() {
		assertTrue(gameObjects.allBubblesDestroyed());
		gameObjects.addBubbles(bl);
		assertFalse(gameObjects.allBubblesDestroyed());
	}

	@Test
	public void testAddProjectile() {
		spyGameObjects.addProjectile(pj);
		verify(spyGameObjects).addObject(pj);
	}
	
	@Test
	public void testRemoveProjectile() {
		spyGameObjects.addProjectile(pj);
		spyGameObjects.removeProjectile();
		verify(spyGameObjects).removeObject(isA(Projectile.class));
	}

	@Test
	public void testHandleBubbleSplit() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddPoints() {
		assertEquals(gameObjects.getScore(),0);
		gameObjects.addPoints(50);
		assertEquals(gameObjects.getScore(),50);
	}

	@Test
	public void testAddLife() {
		gameObjects.addLife();
		verify(gl).addLife();
	}

	@Test
	public void testBubblesLeft() {
		assertEquals(gameObjects.bubblesLeft(),0);
		gameObjects.addBubbles(bl);
		assertEquals(gameObjects.bubblesLeft(),1);
	}
	
	@Test
	public void testMakeCircle() {
		gameObjects.makeCircle(100,200,25);
		verify(gl).makeCircle(100, 200, 25);
	}
	
	@Test
	public void testMakeLine() {
		gameObjects.makeLine(25, 0, 100, 50);
		verify(gl).makeLine(25, 0, 100, 50);
	}
	
	@Test
	public void testMakeImage() {
		gameObjects.makeImage(is, 75, 25);
		verify(gl).makeImage(is, 25, 75);
	}
	
	@Test
	public void testPlayerDied() {
		gameObjects.playerDied();
		verify(gl).playerDied();
	}
	
	@Test
	public void testHasProjectile() {
		assertFalse(gameObjects.hasProjectile());
		gameObjects.addProjectile(pj);
		assertTrue(gameObjects.hasProjectile());
	}
	
	@Test
	public void testHandleModifierCollision() {
		fail("Not correctly implemented");
	}

}
