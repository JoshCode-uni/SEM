package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.InputStream;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.Life;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.EnumPowerupTypes;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.Powerup;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.IImageViewObject;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class LifeTest {

	@Mock
	private IGameObjects igo;

	@Mock
	private IImageViewObject ivo;

	private Life life;

	/**
	 * Initialize and mock objects.
	 */
	@Before
	public void initialize() {
		when(igo.getLeftBorder()).thenReturn(0.0);
		when(igo.getRightBorder()).thenReturn(10.0);
		when(igo.getBottomBorder()).thenReturn(10.0);
		when(igo.getTopBorder()).thenReturn(0.0);
		when(igo.makeImage(Mockito.any(InputStream.class), Mockito.any(Double.class), 
				Mockito.any(Double.class))).thenReturn(ivo);

		life = new Life(igo, 400, 400);
	}

	@Test
	public void testHandlePlayerCollision() {
		Life spy = Mockito.spy(life);
		spy.handlePlayerCollision();
		verify(spy).destroy();
	}

}
