package nl.joshuaslik.tudelft.SEM.control.gameObjects;


import java.io.InputStream;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.Coin;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ImageViewObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class) 
public class CoinTest {

	@Mock
	GameObjects go;
	
	@Mock
	ImageViewObject image;
	
	Coin coin;
	
	@Before
	public void setup() {
		Mockito.when(go.makeImage(Mockito.any(InputStream.class), Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(image);
		Mockito.when(go.getLeftBorder()).thenReturn(0.0);
		Mockito.when(go.getRightBorder()).thenReturn(100.0);
		Mockito.when(go.getTopBorder()).thenReturn(0.0);
		Mockito.when(go.getBottomBorder()).thenReturn(100.0);
		coin = new Coin(go, 400, 400);
	}
	@Test
	public void testHandlePlayerCollision() {
		Coin spyCoin = Mockito.spy(coin);
		spyCoin.handlePlayerCollision();
		verify(spyCoin).destroy();
		verify(go).addPoints(10);
	}

}
