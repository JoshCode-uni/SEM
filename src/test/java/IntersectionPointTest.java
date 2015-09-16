
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import nl.joshuaslik.tudelft.SEM.model.container.IntersectionPoint;
import nl.joshuaslik.tudelft.SEM.model.container.Point;
import nl.joshuaslik.tudelft.SEM.model.container.Vector;
import org.junit.Before;
import org.junit.Test;

public class IntersectionPointTest {

    IntersectionPoint point1, point2, point3, point4;
    Point point5;

    @Before
    public void beforeTest() {
        point1 = new IntersectionPoint(20, 20, new Vector(5, 2), 50);
        point2 = new IntersectionPoint(20, 20, new Vector(5, 2), 50);
        point3 = new IntersectionPoint(20, 20, new Vector(5, 2), 60);
        point4 = new IntersectionPoint(20, 20, null, 60);
        point5 = new Point(20, 20);
    }

    @Test
    public void testIntersectionPoint() {
        assertEquals(point1, point2);
        assertNotEquals(point1, null);
        assertNotEquals(point1, point5);
    }

    @Test
    public void testGetNormal() {
        assertEquals(point3.getNormal(), new Vector(5, 2));
        assertNotEquals(point3.getNormal(), new Vector(5, 3));
    }

    @Test
    public void testGetDistance() {
        assertEquals(point1.getDistance(), 50, 0);
        assertNotEquals(point1.getDistance(), 25, 0);
    }

    @Test
    public void testHasSpeedVec() {
        assertFalse(point4.hasSpeedVec());
    }

    @Test
    public void testGetSpeedVec() {
        point4.setSpeedVec(new Vector(50, 2));
        assertEquals(new Vector(50, 2), point4.getSpeedVec());
    }

    @Test
    public void testSetSpeedVec() {
        assertEquals(null, point1.getSpeedVec());
        point1.setSpeedVec(new Vector(7, 2));
        assertEquals(new Vector(7, 2), point1.getSpeedVec());
    }

}
