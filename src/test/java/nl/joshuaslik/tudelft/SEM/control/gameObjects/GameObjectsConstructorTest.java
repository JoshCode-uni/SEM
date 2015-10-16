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
import nl.joshuaslik.tudelft.SEM.model.container.GameInfo;
import nl.joshuaslik.tudelft.SEM.model.container.PlayerMode;
import nl.joshuaslik.tudelft.SEM.model.container.Point;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(GameObjects.class)
public class GameObjectsConstructorTest {

    private GameObjects gameObjects;
    PickupGenerator pg;
    private Line l1;
    private Line l2;
    private Line l3;
    private Line l4;
    private ArrayList<IPhysicsObject> physicsObject = new ArrayList<>();
    private Bubble bubble;
    //	Keyboard kb;
    private Player player;
    Point p = new Point(0, 0);

    @Before
    public void setUp() throws Exception {
        GameInfo.getInstance().setPlayerMode(PlayerMode.MULTI_PLAYER_COOP);
        suppress(methods(GameObjects.class, "initializeLevel"));
        l1 = PowerMockito.mock(Line.class);
        l2 = PowerMockito.mock(Line.class);
        l3 = PowerMockito.mock(Line.class);
        l4 = PowerMockito.mock(Line.class);

        /*		TODO: Fix TestInitializedLevel
 
         PowerMockito.mockStatic(Levels.class);		
         PowerMockito.when(Levels.getLevelObjects(Mockito.anyInt(), Mockito.any(GameObjects.class))).thenReturn(physicsObject);
         */
        player = PowerMockito.mock(Player.class);
        bubble = PowerMockito.mock(Bubble.class);
        PowerMockito.whenNew(Line.class).withArguments(gameObjects, 5.0, 10.0, 20.0, 5.0).thenReturn(l1);
        PowerMockito.whenNew(Line.class).withArguments(gameObjects, 5.0, 10.0, 5.0, 0.0).thenReturn(l2);
        PowerMockito.whenNew(Line.class).withArguments(gameObjects, 20.0, 10.0, 20.0, 0.0).thenReturn(l3);
        PowerMockito.whenNew(Line.class).withArguments(gameObjects, 5.0, 0.0, 20.0, 0.0).thenReturn(l4);
        PowerMockito.whenNew(Player.class)
                .withArguments(Mockito.any(GameObjects.class), Mockito.any(InputStream.class), Mockito.any(Keyboard.class), Mockito.anyBoolean())
                .thenReturn(player);
        physicsObject.add(bubble);
        gameObjects = new GameObjects(null, 10.0, 20.0, 0.0, 5.0, null);
    }

    /**
     * Test constructor
     */
    @Test
    public void testConstructor() {

        assertEquals(0, gameObjects.getScore());
        assertEquals(0, gameObjects.bubblesLeft());
        assertFalse(gameObjects.hasProjectile(false));
        assertFalse(gameObjects.hasProjectile(true));
    }

    /**
     * Tests if borders are correctly initialized after running the constructor
     */
    @Test
    public void testInitializeBorders() {
        assertEquals(gameObjects.getTopBorder(), 10, 0);
        assertEquals(gameObjects.getBottomBorder(), 0, 0);
        assertEquals(gameObjects.getLeftBorder(), 5, 0);
        assertEquals(gameObjects.getRightBorder(), 20, 0);
    }

    /**
     * Tests if the player is correctly initialized after running the constructor
     */
    @Test
    public void testInitializedPlayer() {
        assertEquals(gameObjects.getPlayer(), player);
    }

    /**
     * Tests if handleModifierCollision adds the modifier to the correct unit.
     */
    @Test
    public void testhandleModifierCollision() {
        PowerMockito.doNothing().when(player).addModifier(Mockito.any(AbstractPlayerDecorator.class));
        gameObjects.handleModifierCollision(null, true, false);
        Mockito.verify(player, times(1)).addModifier(Mockito.any(AbstractPlayerDecorator.class));
        Mockito.verify(bubble, times(0)).addModifier(Mockito.any(AbstractBubbleDecorator.class));
    }

    /**
     * Tests if handleModifierCollision adds the modifier to the correct unit.
     */
    @Test
    public void testHandleModifierCollisionBubble() {
        gameObjects.addBubbles(bubble);
        PowerMockito.doNothing().when(bubble).addModifier(Mockito.any(AbstractBubbleDecorator.class));
        gameObjects.handleModifierCollision(null, false, true);
        Mockito.verify(bubble, times(1)).addModifier(Mockito.any(AbstractBubbleDecorator.class));
        Mockito.verify(player, times(0)).addModifier(Mockito.any(AbstractPlayerDecorator.class));
    }

    @Test
    public void testHandleModifierCollisionNone() {
        PowerMockito.doNothing().when(bubble).addModifier(Mockito.any(AbstractBubbleDecorator.class));
        PowerMockito.doNothing().when(player).addModifier(Mockito.any(AbstractPlayerDecorator.class));
        gameObjects.handleModifierCollision(null, false, false);
        Mockito.verify(bubble, times(0)).addModifier(Mockito.any(AbstractBubbleDecorator.class));
        Mockito.verify(player, times(0)).addModifier(Mockito.any(AbstractPlayerDecorator.class));

    }

    /**
     *
     */
    @Test
    public void testInitializedLevel() {
        //TODO: Fix test.
        //gameObjects.getAddObjectBuffer().contains(bubble);
    }
}
