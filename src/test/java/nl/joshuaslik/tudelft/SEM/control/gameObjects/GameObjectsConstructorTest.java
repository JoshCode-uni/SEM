package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.powermock.api.support.membermodification.MemberMatcher.methods;
import static org.powermock.api.support.membermodification.MemberModifier.suppress;

import java.io.InputStream;
import java.util.ArrayList;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.PickupGenerator;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.bubble.AbstractBubbleDecorator;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.AbstractPlayerDecorator;
import nl.joshuaslik.tudelft.SEM.control.viewController.Keyboard;
import nl.joshuaslik.tudelft.SEM.model.container.Users;
import nl.joshuaslik.tudelft.SEM.model.container.PlayerMode;
import nl.joshuaslik.tudelft.SEM.model.container.Point;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Test the game objects contructor.
 * @author Faris
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(GameObjects.class)
public class GameObjectsConstructorTest {

    private GameObjects go;
    PickupGenerator pg;
    private Line l1;
    private Line l2;
    private Line l3;
    private Line l4;
    private ArrayList<Object> physicsObject = new ArrayList<>();
    private Bubble bubble;
    private Player player;
    Point p = new Point(0, 0);

    @Before
    public void setUp() throws Exception {
        Users.getInstance().setPlayerMode(PlayerMode.MULTI_PLAYER_COOP);
        suppress(methods(GameObjects.class, "initializeLevel"));
        l1 = PowerMockito.mock(Line.class);
        l2 = PowerMockito.mock(Line.class);
        l3 = PowerMockito.mock(Line.class);
        l4 = PowerMockito.mock(Line.class);
        player = PowerMockito.mock(Player.class);
        bubble = PowerMockito.mock(Bubble.class);
        PowerMockito.whenNew(Line.class).withArguments(go, 5.0, 10.0, 20.0, 5.0).thenReturn(l1);
        PowerMockito.whenNew(Line.class).withArguments(go, 5.0, 10.0, 5.0, 0.0).thenReturn(l2);
        PowerMockito.whenNew(Line.class).withArguments(go, 20.0, 10.0, 20.0, 0.0).thenReturn(l3);
        PowerMockito.whenNew(Line.class).withArguments(go, 5.0, 0.0, 20.0, 0.0).thenReturn(l4);
        PowerMockito.whenNew(Player.class).withArguments(Mockito.any(GameObjects.class), 
                Mockito.any(InputStream.class), Mockito.any(Keyboard.class), Mockito.anyBoolean())
                .thenReturn(player);
        physicsObject.add(bubble);
        go = new GameObjects(null, 10.0, 20.0, 0.0, 5.0, null);
    }

    /**
     * Test constructor
     */
    @Test
    public void testConstructor() {

        assertEquals(0, go.getScore());
        assertEquals(0, go.bubblesLeft());
        assertFalse(go.hasProjectile(false));
        assertFalse(go.hasProjectile(true));
    }

    /**
     * Tests if borders are correctly initialized after running the constructor
     */
    @Test
    public void testInitializeBorders() {
        assertEquals(go.getTopBorder(), 10, 0);
        assertEquals(go.getBottomBorder(), 0, 0);
        assertEquals(go.getLeftBorder(), 5, 0);
        assertEquals(go.getRightBorder(), 20, 0);
    }

    /**
     * Tests if the player is correctly initialized after running the constructor
     */
    @Test
    public void testInitializedPlayer() {
        assertEquals(go.getPlayer(), player);
    }

    /**
     * Tests if handleModifierCollision adds the modifier to the correct unit.
     */
    @Test
    public void testhandleModifierCollision() {
        PowerMockito.doNothing().when(player).
                addModifier(Mockito.any(AbstractPlayerDecorator.class));
        go.handleModifierCollision(null, true, false);
        Mockito.verify(player, times(1)).addModifier(Mockito.any(AbstractPlayerDecorator.class));
        Mockito.verify(bubble, times(0)).addModifier(Mockito.any(AbstractBubbleDecorator.class));
    }

    /**
     * Tests if handleModifierCollision adds the modifier to the correct unit.
     */
    @Test
    public void testHandleModifierCollisionBubble() {
        go.addBubbles(bubble);
        PowerMockito.doNothing().when(bubble).
                addModifier(Mockito.any(AbstractBubbleDecorator.class));
        go.handleModifierCollision(null, false, true);
        Mockito.verify(bubble, times(1)).addModifier(Mockito.any(AbstractBubbleDecorator.class));
        Mockito.verify(player, times(0)).addModifier(Mockito.any(AbstractPlayerDecorator.class));
    }

    /**
     * Test the handleModifierCollision method with no collsion.
     */
    @Test
    public void testHandleModifierCollisionNone() {
        PowerMockito.doNothing().when(bubble).
                addModifier(Mockito.any(AbstractBubbleDecorator.class));
        PowerMockito.doNothing().when(player).
                addModifier(Mockito.any(AbstractPlayerDecorator.class));
        go.handleModifierCollision(null, false, false);
        Mockito.verify(bubble, times(0)).addModifier(Mockito.any(AbstractBubbleDecorator.class));
        Mockito.verify(player, times(0)).addModifier(Mockito.any(AbstractPlayerDecorator.class));
    }
}
