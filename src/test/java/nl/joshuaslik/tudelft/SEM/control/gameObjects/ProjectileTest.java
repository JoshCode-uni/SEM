package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.Assert.assertTrue;
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
    
    
    Projectile projectile;
	
	@Before
	public void setup() {
		when(gameObjects.makeLine(0,0,0,1)).thenReturn(line);
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
        
        projectile = new Projectile(gameObjects, 0, 1);
        
        
        
	}
	
	@Test
	public void testVectorProperties() {
		verify(line).setStrokeWidth(7);
		verify(line).setColor(0.7, 0.5, 0.9);
		verify(line).setOpacity(0.3);
	}
	
	@Test
	public void testUpdate() {
		projectile.update(1_000_000_000);
		//UpdateLinePoints calls setEndY twice
		verify(line,times(3)).setEndY(line.getEndY() - 750 * ( 1_000_000_000/ 1_000_000_000.0));
	}
	
	@Test
	public void testUpdateDestroyed() {
		when(line.getEndY()).thenReturn(-1.0);
		projectile.update(10_000);
		verify(line).destroy();
	}
	
	@Test
	public void testGetClosestIntersectionOutOfBounds(){
		Point point = Mockito.mock(Point.class);
		when(point.getxPos()).thenReturn(5.0);
		when(point.getyPos()).thenReturn(5.0);
		assertTrue(projectile.getClosestIntersection(point).equals(new IntersectionPoint(Double.MAX_VALUE, Double.MAX_VALUE, new Vector(1, 1), Double.MAX_VALUE)));
		
	}
	
	@Test
	public void testUpdateLinePoints() {
		projectile.updateLinePoints();
		verify(line,times(1)).getStartX();
		verify(line,times(1)).getEndX();
		verify(line,times(1)).getStartY();
		verify(line,times(1)).getEndY();
	}
	
	@Test
	public void testCollideBubble(){
		 Bubble bubble = Mockito.mock(Bubble.class);
	     ICircleViewObject cvo = Mockito.mock(ICircleViewObject.class);
	     projectile.collide(bubble,10);
	     verify(bubble,times(1)).splitBubble();
	     verify(line,times(1)).destroy();
	}
	
	@Test
	public void testCollidePlayer() {
		Player player = Mockito.mock(Player.class);
		projectile.collide(player, 10);
		verify(line, times(0)).destroy();
	}

}
