package nl.joshuaslik.tudelft.SEM.control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.InputStream;

import javafx.scene.Scene;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.GameObjects;
import nl.joshuaslik.tudelft.SEM.control.viewController.GameController;
import nl.joshuaslik.tudelft.SEM.control.viewController.Keyboard;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(GameLoop.class)
public class GameLoopT {

    @Mock
    GameController gameController;

    @Mock
    Scene scene;

    private GameObjects gameObjects;

    private Keyboard kb;

    private GameLoop gl;
    private GameLoop spyGL;

    /**
     * Setup mocks
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        kb = PowerMockito.mock(Keyboard.class);
        gameObjects = PowerMockito.mock(GameObjects.class);
        PowerMockito.whenNew(Keyboard.class).withArguments(scene).thenReturn(kb);
        PowerMockito.whenNew(GameObjects.class).withAnyArguments().thenReturn(gameObjects);
        gl = new GameLoop(gameController, 0, 25, 25, 0, 0, scene);
        spyGL = Mockito.spy(gl);
    }

    /**
     * Tests if GameLoop is correctly initialized
     */
    @Test
    public void testGameLoop() {
        assertEquals(gl.getScore(), 0);
    }

    /**
     * Tests if deaths are correctly handled.
     */
    @Test
    public void testPlayerDied() {
        GameLoop spyGL = Mockito.spy(gl);
        spyGL.playerDied();
        Mockito.verify(spyGL).stop();
        Mockito.verify(gameController).died();
	}
	
	/**
	 * Tests if the GameLoop is started/terminated in the correct way
	 */
	@Test
	public void testStartStop() {
		gl.start();
		Mockito.verify(kb).addListeners();
		gl.stop();
		Mockito.verify(kb).removeListeners();
	}
	
	/**
	 * Tests if it is correctly handled if all bubbles are destroyed.
	 */
	@Test
	public void testHandlerNoMoreBubbles() {
		Mockito.doReturn(true).when(gameObjects).allBubblesDestroyed();
		gl.handle(0l);
		Mockito.verify(gameController).levelCompleted();
	}
	
	/**
	 * Tests if the handler runs correct.
	 */
	@Test
	public void testHandler() {
		gl.handle(0l);
		Mockito.verify(gameController).updateTime(165_000_000l);
		Mockito.verify(gameObjects).update(165_000_000l);
	}
	
	/**
	 * Tests if the handler handles exceptions in the correct way.
	 */
	@Test
	public void testHandlerException() {
		GameLoop spyGL = Mockito.spy(gl);
		Mockito.doThrow(new IllegalArgumentException()).when(gameController).updateTime(165_000_000l);
		spyGL.handle(0l);
		Mockito.verify(spyGL).stop();
	}
	
	/**
	 * Tests if the viewController is correctly initialized
	 */
	@Test
	public void testSetViewController() {
		GameController gameController2 = Mockito.mock(GameController.class);
		assertEquals(gl.getGameController(), gameController);
		assertNotEquals(gl.getGameController(), gameController2);
		gl.setViewController(gameController2);
		assertEquals(gl.getGameController(), gameController2);
		assertNotEquals(gl.getGameController(), gameController);
	}
	
	/**
	 * Tests if the score is called correct.
	 */
	@Test
	public void testGetScore() {
		gl.getScore();
		Mockito.verify(gameObjects).getScore();
	}
	
	/**
	 * Tests if circles are constructed correct.
	 */
	@Test
	public void testMakeCircle() {
		gl.makeCircle(100, 200, 50);
		Mockito.verify(gameController).makeCircle(100, 200, 50);
	}
	
	/**
	 * Tests if images are constructed correct.
	 */
	@Test
	public void testMakeImage() {
		InputStream is = Mockito.mock(InputStream.class);
		gl.makeImage(is, 50, 100);
		Mockito.verify(gameController).makeImage(is, 50, 100);
	}
	
	/**
	 * Tests if lines are constructed correct.
	 */
	@Test
	public void testMakeLine() {
		gl.makeLine(1000, 50, 1500, 500);
		Mockito.verify(gameController).makeLine(1000, 50, 1500, 500);
	}
	
	/**
	 * Tests if adding a life is handled correct.
	 */
	@Test
	public void testAddLife() {
		gl.addLife();
		Mockito.verify(gameController).addLife();
	}
}
