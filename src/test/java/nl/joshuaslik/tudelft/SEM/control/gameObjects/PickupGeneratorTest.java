package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Random;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.Coin;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.Life;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.PickupGenerator;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.Powerup;
import nl.joshuaslik.tudelft.SEM.model.container.Point;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
@PrepareForTest(PickupGenerator.class)
public class PickupGeneratorTest {

	IGameObjects igo = Mockito.mock(IGameObjects.class);
	Point p = Mockito.mock(Point.class);
	Powerup powerup = Mockito.mock(Powerup.class);
	PickupGenerator pg;
	Coin coin = Mockito.mock(Coin.class);
	Life life = Mockito.mock(Life.class);
	Random rnd;
	@Before
	public void setup() throws Exception {
		rnd = PowerMockito.mock(Random.class);
		PowerMockito.whenNew(Random.class).withAnyArguments().thenReturn(rnd);
		PowerMockito.whenNew(Powerup.class).withAnyArguments().thenReturn(powerup);
		PowerMockito.whenNew(Coin.class).withAnyArguments().thenReturn(coin);
		PowerMockito.whenNew(Life.class).withAnyArguments().thenReturn(life);
		pg = new PickupGenerator(igo);
		
	}
	
	/**
	 * Tests if no pickup is spawned under the right circumstances
	 */
	@Test
	public void testNoPickup() {
		Mockito.when(rnd.nextBoolean()).thenReturn(false);

		pg.generatePickup(p);
		verify(igo, times(0)).addObject(powerup);
	}
	
	/**
	 * Tests if a powerup is spawned under the right circumstances
	 * @throws Exception
	 */
	@Test
	public void testPowerup() throws Exception {

		Mockito.when(rnd.nextBoolean()).thenReturn(true);
		Mockito.when(rnd.nextDouble()).thenReturn(0.5);
		pg.generatePickup(p);
		verify(igo, times(1)).addObject(powerup);
	}
	
	/**
	 * Tests if a coin is dropped under the right circumstances
	 */
	@Test
	public void testCoin() {
		Mockito.when(rnd.nextBoolean()).thenReturn(true);
		Mockito.when(rnd.nextDouble()).thenReturn(0.7);
		Mockito.when(rnd.nextDouble()).thenReturn(0.4);
		pg.generatePickup(p);
		verify(igo, times(1)).addObject(powerup);
	}

	/**
	 * Tests if a life is dropped under the right circumstances
	 */
	@Test
	public void testLife() {
		Mockito.when(rnd.nextBoolean()).thenReturn(true);
		Mockito.when(rnd.nextDouble()).thenReturn(0.7);
		Mockito.when(rnd.nextDouble()).thenReturn(0.6);
		pg.generatePickup(p);
		verify(igo, times(1)).addObject(life);
	}
}
