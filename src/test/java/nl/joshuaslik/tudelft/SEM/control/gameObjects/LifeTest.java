package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.Life;
import org.junit.Test;
import org.mockito.Mockito;

public class LifeTest {

	GameObjects go = Mockito.mock(GameObjects.class);
	Life life = new Life(go, 400, 400);
	
	@Test
	public void testHandlePlayerCollision() {
		verify(life).destroy();
		verify(life).getGameObjects().addLife();;
	}

}
