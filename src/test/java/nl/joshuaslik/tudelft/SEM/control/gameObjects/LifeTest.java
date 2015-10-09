package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import java.io.InputStream;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.Life;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ImageViewObject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class) 
public class LifeTest {

	@Mock
	GameObjects go;
	
	@Mock
	ImageViewObject image;
	
	Life life;
	@Before
	public void setup() {
		Mockito.when(go.makeImage(Mockito.any(InputStream.class), Mockito.anyDouble(), Mockito.anyDouble())).thenReturn(image);
		Mockito.when(go.getLeftBorder()).thenReturn(0.0);
		Mockito.when(go.getRightBorder()).thenReturn(100.0);
		Mockito.when(go.getTopBorder()).thenReturn(0.0);
		Mockito.when(go.getBottomBorder()).thenReturn(100.0);
		life = new Life(go, 400, 400);
	}
	
	@Test
	public void testHandlePlayerCollision() {
		Life spyLife = Mockito.spy(life);
		spyLife.handlePlayerCollision();
		verify(spyLife).destroy();
		verify(go).addLife();
	}

}
