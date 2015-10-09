package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.IBubbleModifier;

public class BubbleSlowdownTest {
	
	@Test
	public void testGetBubbleSpeedModifier() {
		double res = getBubbleSpeedModifier();
		assertEquals(0.5, res);
	}
}
