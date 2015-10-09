package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import static org.junit.Assert.*;

import java.io.InputStream;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.Coin;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.EnumPowerupTypes;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.Powerup;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.IImageViewObject;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CoinTest {

	@Mock
	private IGameObjects igo;

	@Mock
	private IImageViewObject image;

	private Coin coin;

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
				Mockito.any(Double.class))).thenReturn(image);

		coin = new Coin(igo, 400, 400);
	}

	@Test
	public void testHandlePlayerCollision() {
		Coin spy = Mockito.spy(coin);
		spy.handlePlayerCollision();
		verify(spy).destroy();
	}

}
