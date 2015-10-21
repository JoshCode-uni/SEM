package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import java.io.InputStream;
import static org.mockito.Mockito.verify;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.EnumPowerupTypes;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.Powerup;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.IImageViewObject;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test the powerup class.
 * @author Faris
 */
@RunWith(MockitoJUnitRunner.class)
public class PowerupTest {

    @Mock
    private IGameObjects igo;

    @Mock
    private IImageViewObject image;

    private Powerup powerup;

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

        powerup = new Powerup(igo, EnumPowerupTypes.PLAYER_SPEED_UP, 400, 400);
    }

    /**
     * Test the handlePlayerCollision method.
     */
    @Test
    public void testHandlePlayerCollision() {
        Powerup spy = Mockito.spy(powerup);
        spy.handlePlayerCollision();
        verify(spy).destroy();
    }
}
