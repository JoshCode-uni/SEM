package nl.joshuaslik.tudelft.SEM.control.viewController;

import static org.junit.Assert.*;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import nl.joshuaslik.tudelft.SEM.model.container.GameInfo;
import nl.joshuaslik.tudelft.SEM.model.container.PlayerMode;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

import java.util.BitSet;

import org.mockito.runners.MockitoJUnitRunner;
import javafx.scene.Scene;

/**
 * Keyboard test class.
 * @author Faris
 */
@RunWith(MockitoJUnitRunner.class)
public class KeyboardTest {

    @Mock
    EventHandler<KeyEvent> keyPressedEventHandler;

    @Mock
    EventHandler<KeyEvent> keyReleasedEventHandler;

    @Mock
    Scene scene;

    @Mock
    BitSet keyboardbitset;

    Keyboard keyboard, spyKeyboard;

    @Mock
    BitSet kb;

    /**
     * setup Tests.
     */
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        keyboard = new Keyboard(scene);
        keyboard.setBitSet(kb);
        GameInfo.getInstance().setPlayerMode(PlayerMode.SINGLE_PLAYER);
    }

    /**
     * Tests if keyboard is correctly initialized
     */
    @Test
    public void testKeyboard() {
        assertEquals(scene, keyboard.getScene());
    }

    /**
     * Tests if ismoveleft is correctly handled
     */
    @Test
    public void testIsMoveLeft() {
        keyboard.isMoveLeft(false);
        verify(kb).get(Mockito.anyInt());
    }

    /**
     * Tests if ismoveright is correctly handled
     */
    @Test
    public void testIsMoveRight() {
        keyboard.isMoveRight(false);
        verify(kb).get(Mockito.anyInt());
    }

    /**
     * Tests if shooting is correctly handled
     */
    @Test
    public void testIsShoot() {
        keyboard.isShoot(false);
        verify(kb).get(Mockito.anyInt());
    }
}
