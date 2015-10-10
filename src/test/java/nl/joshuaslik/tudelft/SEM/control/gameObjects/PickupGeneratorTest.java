package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import java.io.InputStream;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Random;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.Coin;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.Life;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.PickupGenerator;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.Powerup;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.IImageViewObject;
import nl.joshuaslik.tudelft.SEM.model.container.Point;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnit44Runner;

@RunWith(MockitoJUnit44Runner.class)
public class PickupGeneratorTest {

    @Mock
    IGameObjects igo;

    @Mock
    Point point;

    @Mock
    Powerup powerup;

    PickupGenerator pg;

    @Mock
    Coin coin;

    @Mock
    Life life;

    Random rnd;

    @Mock
    IImageViewObject image;

    /**
     * Initialize mocks.
     */
    @Before
    public void setup() {
        when(igo.getLeftBorder()).thenReturn(0.0);
        when(igo.getRightBorder()).thenReturn(10.0);
        when(igo.getTopBorder()).thenReturn(0.0);
        when(igo.getBottomBorder()).thenReturn(10.0);
        when(igo.makeImage(Mockito.any(InputStream.class), Mockito.any(Double.class),
                Mockito.any(Double.class))).thenReturn(image);

        pg = new PickupGenerator(igo);

    }

    /**
     * Tests if no pickup is spawned under the right circumstances
     */
    @Test
    public void testNoPickup() {
        Random rand = Mockito.mock(Random.class);
        when(rand.nextBoolean()).thenReturn(Boolean.FALSE);

        pg.generatePickup(point, rand);
        verify(igo, times(0)).addObject(Mockito.any());
    }

    /**
     * Tests if a powerup is spawned under the right circumstances
     */
    @Test
    public void testPowerup() {
        Random rand = Mockito.mock(Random.class);
        when(rand.nextBoolean()).thenReturn(Boolean.TRUE);
        when(rand.nextDouble()).thenReturn(0.0);
        pg.generatePickup(point, rand);

        verify(igo, times(1)).addObject(Mockito.any(Powerup.class));
    }

    /**
     * Tests if a coin is dropped under the right circumstances
     */
    @Test
    public void testCoin() {
        Random rand = Mockito.mock(Random.class);
        when(rand.nextBoolean()).thenReturn(Boolean.TRUE);
        when(rand.nextDouble()).thenReturn(1.0, 0.0);
        pg.generatePickup(point, rand);

        verify(igo, times(1)).addObject(Mockito.any(Coin.class));
    }

    /**
     * Tests if a life is dropped under the right circumstances
     */
    @Test
    public void testLife() {
        Random rand = Mockito.mock(Random.class);
        when(rand.nextBoolean()).thenReturn(Boolean.TRUE);
        when(rand.nextDouble()).thenReturn(1.0, 1.0);
        pg.generatePickup(point, rand);

        verify(igo, times(1)).addObject(Mockito.any(Life.class));
    }
}
