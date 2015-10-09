package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.EnumPowerupTypes;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.Powerup;

import org.junit.Test;
import org.mockito.Mockito;

public class PowerupTest {

	IGameObjects igo = Mockito.mock(IGameObjects.class);
	EnumPowerupTypes ept = Mockito.mock(EnumPowerupTypes.class);
	Powerup powerup = new Powerup(igo, ept, 400, 400);
	
	@Test
	public void testHandlePlayerCollision() {
		powerup.handlePlayerCollision();
		verify(powerup).destroy();
	}

}
