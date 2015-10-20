package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ILineViewObject;
import nl.joshuaslik.tudelft.SEM.model.container.IntersectionPoint;
import nl.joshuaslik.tudelft.SEM.model.container.Point;
import nl.joshuaslik.tudelft.SEM.model.container.Vector;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LineTest {

    @Mock
    IGameObjects gameObjects;

    @Mock
    ILineViewObject line;

    @Mock
    Point p;

    private Line L;
    private Line L2;

    /**
     * Setup mocks
     */
    @Before
    public void setUp() {
        when(gameObjects.makeLine(0.0, 5.0, 15.0, 25.0)).thenReturn(line);
        L = new Line(gameObjects, 0.0, 5.0, 15.0, 25.0);
    }

    /**
     * Test constructor
     */
    @Test
    public void testSetStrokeWidth() {
        verify(line).setStrokeWidth(10);
    }

    /**
     * Test constructor
     */
    @Test
    public void testPoint1Set() {
        assertEquals(L.getPoint1().getxPos(), 0.0, 0);
        assertEquals(L.getPoint1().getyPos(), 5.0, 0);
    }

    /**
     * Test constructor
     */
    @Test
    public void testPoint2Set() {
        assertEquals(L.getPoint2().getxPos(), 15.0, 0);
        assertEquals(L.getPoint2().getyPos(), 25.0, 0);
    }

    /**
     * Test constructor
     */
    @Test
    public void testVectorSet() {
        assertEquals(L.getVector().getX(), 15, 0);
        assertEquals(L.getVector().getY(), 20, 0);
    }

    /**
     * Test method looking for the closest intersection with a point
     */
    @Test
    public void testGetClosestIntersectionLargeX() {
        when(p.getxPos()).thenReturn(50d);
        when(p.getyPos()).thenReturn(25d);
        assertEquals(L.getClosestIntersection(p), new IntersectionPoint(15.0, 25.0, L.getVector().normal(), Double.MAX_VALUE));
    }

    /**
     * Test method looking for the closest intersection with a point
     */
    @Test
    public void testGetClosestIntersectionSmallX() {
        when(p.getxPos()).thenReturn(0d);
        when(p.getyPos()).thenReturn(0d);
        assertEquals(L.getClosestIntersection(p), new IntersectionPoint(0, 5, L.getVector().normal(), Double.MAX_VALUE));
    }

    /**
     * Test method looking for the closest intersection with a point
     */
    @Test
    public void testGetClosestIntersectionNull() {
        when(gameObjects.makeLine(0.0, 5.0, 0.0, 5.0)).thenReturn(line);
        L2 = new Line(gameObjects, 0.0, 5.0, 0.0, 5.0);
        when(p.getxPos()).thenReturn(0.0);
        when(p.getyPos()).thenReturn(0.0);
        assertEquals(new IntersectionPoint(Double.MAX_VALUE, Double.MAX_VALUE, new Vector(1, 1), Double.MAX_VALUE), L2.getClosestIntersection(p));
    }

    /**
     * Test method looking for the closest intersection with a point
     */
    @Test
    public void testGetClosestIntersectionExact() {
        when(p.getxPos()).thenReturn(15d);
        when(p.getyPos()).thenReturn(25d);
        assertEquals(L.getClosestIntersection(p), new IntersectionPoint(15.0, 25.0, L.getVector().normal(), Double.MAX_VALUE));
    }

    /**
     * Test removing the line from the gameObjects
     */
    @Test
    public void testDestroyLine() {
        L.destroy();
        verify(gameObjects).removeObject(L);
        verify(line).destroy();
    }

}
