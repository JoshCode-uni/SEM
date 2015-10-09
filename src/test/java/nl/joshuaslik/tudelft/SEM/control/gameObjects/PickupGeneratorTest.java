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

import org.junit.Test;
import org.mockito.Mockito;

public class PickupGeneratorTest {

	IGameObjects igo = Mockito.mock(IGameObjects.class);
	Point p = Mockito.mock(Point.class);
	Powerup powerup = Mockito.mock(Powerup.class);
	PickupGenerator pg = new PickupGenerator(igo);
	Coin coin = Mockito.mock(Coin.class);
	Life life = Mockito.mock(Life.class);
	
//	@Test
	public void testNoPickup() {
		Random rand = new Random();
		while(rand.nextBoolean()){
			rand = new Random();
		}
		pg.generatePickup(p);
		verify(igo, times(0)).addObject(powerup);
	}
	
//	@Test
	public void testPowerup() {
		Random rand = new Random();
		while(!(rand.nextDouble() < 0.6 && rand.nextBoolean())) {
			rand = new Random();
		}
		
		pg.generatePickup(p);
		verify(igo, times(1)).addObject(powerup);
	}
	
//	@Test
	public void testCoin() {
		Random rand = new Random();
		while(!(rand.nextBoolean() && !(rand.nextDouble() < 0.6) && rand.nextDouble() < 0.5)){
			rand = new Random();
		}
		pg.generatePickup(p);
		verify(igo, times(1)).addObject(coin);
	}

//	@Test
	public void testLife() {
		Random rand = new Random();
		while(!(rand.nextBoolean() && !(rand.nextDouble() < 0.6) && !(rand.nextDouble() < 0.5))){
			rand = new Random();
		}
		pg.generatePickup(p);
		verify(igo, times(1)).addObject(life);
	}
}
