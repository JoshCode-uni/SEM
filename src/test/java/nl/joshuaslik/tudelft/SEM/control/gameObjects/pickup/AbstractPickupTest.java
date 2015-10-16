/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup;

import java.io.InputStream;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.GameObjects;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.Player;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.IImageViewObject;
import nl.joshuaslik.tudelft.SEM.model.container.GameInfo;
import nl.joshuaslik.tudelft.SEM.model.container.PlayerMode;
import nl.joshuaslik.tudelft.SEM.utility.Time;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Faris
 */
@RunWith(MockitoJUnitRunner.class)
public class AbstractPickupTest {

    @Mock
    private GameObjects gameObjects;

    @Mock
    private IImageViewObject image;

    @Mock
    private Player pl;
    
    @Mock
    private Player pl2;

    private AbstractPickup pickup;

    /**
     * Setup mocks.
     */
    @Before
    public void setup() {
        when(gameObjects.makeImage(Mockito.any(InputStream.class), Mockito.anyDouble(),
                Mockito.anyDouble())).thenReturn(image);
        pickup = new Coin(gameObjects, 0, 0);
        GameInfo.getInstance().setPlayerMode(PlayerMode.MULTI_PLAYER_COOP);
    }

    /**
     * Test of handlePlayerCollision method, of class AbstractPickup.
     */
    @Test
    public void testHandlePlayerCollision() {
    }

    /**
     * Test of update method, of class AbstractPickup.
     */
    @Test
    public void testUpdate() {
        when(gameObjects.getPlayer()).thenReturn(pl);
        when(gameObjects.getPlayer2()).thenReturn(pl2);
        IImageViewObject image2 = Mockito.mock(IImageViewObject.class);
        IImageViewObject image3 = Mockito.mock(IImageViewObject.class);
        when(pl.getImage()).thenReturn(image2);
        when(pl2.getImage()).thenReturn(image3);
        when(image.intersects(image2)).thenReturn(false);
        when(image.intersects(image3)).thenReturn(false);
        pickup.update((long) Time.SECOND_NANO);
        verify(image).setY(300.0);
    }

    /**
     * Test of update method, of class AbstractPickup.
     */
    @Test
    public void testUpdateIntersection() {
        when(gameObjects.getPlayer()).thenReturn(pl);
        when(gameObjects.getPlayer2()).thenReturn(pl2);
        IImageViewObject image2 = Mockito.mock(IImageViewObject.class);
        IImageViewObject image3 = Mockito.mock(IImageViewObject.class);
        when(pl.getImage()).thenReturn(image2);
        when(pl2.getImage()).thenReturn(image3);
        when(image.intersects(image2)).thenReturn(true);
        when(image.intersects(image3)).thenReturn(false);
        AbstractPickup spyPickup = Mockito.spy(pickup);
        spyPickup.update((long) Time.SECOND_NANO);
        verify(spyPickup).handlePlayerCollision();
    }

    /**
     * Test of getPickupImage method, of class AbstractPickup.
     */
    @Test
    public void testGetPickupImage() {
        when(image.getStartY()).thenReturn(0.0);
        IImageViewObject im = pickup.getPickupImage();
        assertEquals(image, im);
    }

    /**
     * Test of destroy method, of class AbstractPickup.
     */
    @Test
    public void testDestroy() {
        pickup.destroy();
        verify(gameObjects).removeObject(pickup);
    }

}
