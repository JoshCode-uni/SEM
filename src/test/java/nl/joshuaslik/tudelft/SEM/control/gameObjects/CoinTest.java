package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import static org.junit.Assert.*;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.Coin;

import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;

public class CoinTest {

	GameObjects go = Mockito.mock(GameObjects.class);
	
	@Test
	public void testHandlePlayerCollision() {
		
		Coin coin = new Coin(go, 400, 400);
		coin.handlePlayerCollision();
		verify(coin).destroy();
	}

}
