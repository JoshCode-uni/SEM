package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.Bubble;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ICircleViewObject;
import nl.joshuaslik.tudelft.SEM.model.container.IntersectionPoint;
import nl.joshuaslik.tudelft.SEM.model.container.Point;
import nl.joshuaslik.tudelft.SEM.model.container.Vector;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Matchers.isA;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class BubbleTest{
	
	@Mock
	IGameObjects gameObjects;
	
	@Mock
	ICircleViewObject circle;
	
	@Mock
	ICircleViewObject circle2;
	
	@Mock
	ICircleViewObject circle3;
	
	@Mock
	Bubble bubble2;
    
	
	Bubble bubble;
    
    
    @Before
    public void setUp() {
    	when(gameObjects.makeCircle(250, 100, 50)).thenReturn(circle);
    	
        when(gameObjects.getLeftBorder()).thenReturn(0.0);
        when(gameObjects.getRightBorder()).thenReturn(500.0);
        when(gameObjects.getTopBorder()).thenReturn(0.0);
        when(gameObjects.getBottomBorder()).thenReturn(500.0);
        when(circle.getCenterX()).thenReturn(250.0);
        when(circle.getCenterY()).thenReturn(100.0);
    	when(circle.getRadius()).thenReturn(50.0);
        bubble = new Bubble(gameObjects, new Point(250,100), 50, new Vector(5,-2));
    }

    /**
	 * Test constructor
	 */
    @Test
    public void testBubbleCircle() {
    	verify(circle).setRadius(50);
    	verify(circle).setBounds(0.0, 0.0, 500.0, 500.0);
    }
    
    /**
	 * Test constructor
	 */
    @Test
    public void testBubbleVector() {
    	assertEquals(bubble.getDir(),new Vector(5,-2));
    	assertEquals(bubble.getDir(),bubble.getNewDir());
    }
    
    /**
	 * Test prepareUpdate and update method of class Bubble.
	 */
    @Test
    public void testUpdate() {
    	assertEquals(bubble.getDir(),new Vector(5,-2));
    	bubble.prepareUpdate(5_000_000_000l);
    	assertEquals(900,bubble.getYvelocity(),0);
    	assertEquals(150,bubble.getXvelocity(),0);
    	bubble.update(5_000_000_000l);
    	verify(circle,times(2)).getCenterX();
    	verify(circle,times(3)).getCenterY();
    	verify(circle).setCenterX(1000.0);
    	verify(circle).setCenterY(100.0+900.0*5);
    }

    /**
     * Test if a circle is correctly destroyed after an update.
     */
    @Test
    public void testUpdateDestroy() {
    	when(circle.getRadius()).thenReturn(500.0);
    	bubble.update(500_000_000l);
    	verify(gameObjects).removeObject(bubble);
    	verify(circle).destroy();
    }
    
    /**
     * Test if the circle bounces correctly from the ground.
     */
    @Test
    public void testUpdateBounce() {
    	when(circle.getCenterY()).thenReturn(500.0);
    	bubble.update(0);
    	assertEquals(-900.0,bubble.getYvelocity(),0);
    }
    
    /**
     * Tests if the collision is administered correctly.
     */
    @Test
    public void testCheckSuccesfulBubbleCollision() {
    	when(bubble2.getClosestIntersection(isA(Point.class))).thenReturn(new IntersectionPoint(300,100, new Vector(5,-2),0));
    	bubble.setNextX(275);
    	bubble.setNextY(100);
    	bubble.checkCollision(bubble2, 1_000_000_000l);
    	verify(bubble2).collide(bubble, 1_000_000_000l);
    }
    
    /**
     * Tests if bubbles are split correctly.
     */
    @Test
    public void testSplitBubble() {
    	when(gameObjects.makeCircle(250+1.1*25, 100, 25)).thenReturn(circle2);
    	bubble.splitBubble();
    	verify(gameObjects).handleBubbleSplit(new Point(250,100));
    	verify(circle).setCenterX(250-25);
    	verify(circle).setRadius(25);
    	verify(circle).setCenterY(100);
    	verify(gameObjects).addObject(isA(Bubble.class));
    }
    
    
    /**
     * Tests if the small bubble is destroyed correctly.
     */
    @Test
    public void testDestroySmallBubble() {
    	when(circle.getRadius()).thenReturn(9.0);
    	bubble.splitBubble();
    	verify(circle).destroy();
    }
    
    /**
     * Tests if a bubble with radius r/2<20 always results in a bubble of size 10.
     */
    @Test
    public void testSplitBubble25() {
    	when(circle.getRadius()).thenReturn(25.0);
    	when(gameObjects.makeCircle(250+1.1*10, 100, 10)).thenReturn(circle2);
    	bubble.splitBubble();
    	verify(gameObjects).handleBubbleSplit(new Point(250,100));
    	verify(circle).setCenterX(250-10);
    	verify(circle).setRadius(10);
    	verify(circle).setCenterY(100);
    	verify(gameObjects).addObject(isA(Bubble.class));
    }
}
