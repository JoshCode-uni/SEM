package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.joshuaslik.tudelft.SEM.control.viewController.IKeyboard;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ICircleViewObject;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ILineViewObject;
import nl.joshuaslik.tudelft.SEM.model.container.IntersectionPoint;
import nl.joshuaslik.tudelft.SEM.model.container.Point;
import nl.joshuaslik.tudelft.SEM.model.container.Vector;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Bastijn
 */
@RunWith(MockitoJUnitRunner.class)
public class ProjectileTest {

    @Mock
    IGameObjects gameObjects;

    @Mock
    ILineViewObject line;

    @Mock
    IKeyboard keyboard;

    @Mock
    Vector vector;

    @Mock
    Point p;

    private Projectile projectile;
    Projectile p2;

    /**
     * Set up mocks.
     */
    @Before
    public void setup() {
        when(gameObjects.makeLine(0, -1, 0, -2)).thenReturn(line);
        // the view will be a 10x10 square
        when(gameObjects.getLeftBorder()).thenReturn(0.0);
        when(gameObjects.getRightBorder()).thenReturn(10.0);
        when(gameObjects.getTopBorder()).thenReturn(0.0);
        when(gameObjects.getBottomBorder()).thenReturn(10.0);
        when(keyboard.isMoveLeft()).thenReturn(false);
        when(keyboard.isMoveRight()).thenReturn(false);
        when(keyboard.isShoot()).thenReturn(false);
        when(line.getStartX()).thenReturn(0.0);
        when(line.getStartY()).thenReturn(1.0);
        when(line.getEndX()).thenReturn(0.0);
        when(line.getEndY()).thenReturn(0.0);

        projectile = new Projectile(gameObjects, 0, 1, 20, 0);
    }

    /**
     * Tests initialization.
     */
    @Test
    public void testPointsInitialized() {
        assertEquals(projectile.getPoint1().getxPos(), 0, 0);
        assertEquals(projectile.getPoint1().getyPos(), -1, 0);
        assertEquals(projectile.getPoint2().getxPos(), 0, 0);
        assertEquals(projectile.getPoint2().getyPos(), -2, 0);
	}
	
	/**
	 * Tests initialization.
	 */
	@Test
	public void testVectorInitialized() {
		assertEquals(projectile.getVector(), new Vector(0, -1));
	}
	
	/**
	 * Tests initialization.
	 */
	@Test
	public void testVectorProperties() {
		verify(line).setStrokeWidth(7);
		verify(line).setColor(0.2, 0.1, 0.1);
		verify(line).setOpacity(0.8);
		assertEquals(projectile.getGrowSpeed(), 20_000, 0);
		assertEquals(projectile.getDelay(), 0, 0);
	}
	
	/**
	 * Tests if the projectile is correctly updated.
	 */
	@Test
	public void testUpdate() {
		projectile.update(1_000_000_000);
		//UpdateLinePoints calls setEndY twice
		verify(line, times(4)).setEndY(line.getEndY() - 750 * (1_000_000_000 / 1_000_000_000.0));
	}
	
	/**
	 * Tests if a projectile is correctly destroyed.
	 */
	@Test
	public void testUpdateDestroyed() {
		when(line.getEndY()).thenReturn(-1.0);
		projectile.update(10_000);
		verify(line).destroy();
	}
	
	/**
	 * Tests for closest instersection
	 */
	@Test
	public void testGetClosestIntersectionLargeY() {
		when(p.getxPos()).thenReturn(50d);
		when(p.getyPos()).thenReturn(25d);
		assertEquals(projectile.getClosestIntersection(p), new IntersectionPoint(0.0, -1.0, projectile.getVector().normal(), Double.MAX_VALUE));
	}
	
	/**
	 * Test method looking for the closest intersection with a point
	 */
	@Test
	public void testGetClosestIntersectionSmallY() {
		when(p.getxPos()).thenReturn(0d);
		when(p.getyPos()).thenReturn(0d);
		assertEquals(new IntersectionPoint(0, -1, projectile.getVector().normal(), 
                        Double.MAX_VALUE), projectile.getClosestIntersection(p));
	}
	
	/**
	 * Tests if linepoints are correctly updated
	 */
	@Test
	public void testUpdateLinePoints() {
		projectile.updateLinePoints();
		verify(line, times(1)).getStartX();
		verify(line, times(1)).getEndX();
		verify(line, times(1)).getStartY();
		verify(line, times(1)).getEndY();
	}
	
	/**
	 * Tests bubble collision.
	 */
	@Test
	public void testCollideBubble() {
		Bubble bubble = Mockito.mock(Bubble.class);
		ICircleViewObject cvo = Mockito.mock(ICircleViewObject.class);
		projectile.collide(bubble, 10);
		verify(bubble, times(1)).splitBubble();
		verify(line, times(1)).destroy();
	}
	
	/**
	 * Tests player collision.
	 */
	@Test
	public void testCollidePlayer() {
		Player player = Mockito.mock(Player.class);
		projectile.collide(player, 10);
		verify(line, times(0)).destroy();
	}
	
}
