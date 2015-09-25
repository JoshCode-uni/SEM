package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import junit.framework.TestCase;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.Bubble;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BubbleTest extends TestCase {

    private static final BorderPane bp = new BorderPane();
    Bubble bubble;
    Circle circle;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testBubble() {
//        bubble = new Bubble(new Point(200, 200), 50, new Vector(5, 2));
//        Circle circle = (Circle) bubble.getNode();
//        assertEquals(circle.getCenterX(), 200, 0);
//        assertEquals(circle.getCenterY(), 200, 0);
//        assertEquals(circle.getRadius(), 50, 0);
    }

    @Test
    public void testGetNode() {
//        bubble = new Bubble(new Point(200, 200), 50, new Vector(5, 2));
//        assertTrue(bubble.getNode() instanceof Circle);

    }

    @Test
    public void testGetClosestIntersection() {

    }

    @Test
    public void testUpdate() {
        //fail("Not yet implemented");
        // TODO Implement test
    }

    @Test
    public void testCheckCollision() {
        //fail("Not yet implemented");
        // TODO Implement test
    }

    @Test
    public void testPrepareUpdate() {
        //fail("Not yet implemented");
        // TODO Implement test
    }

    @Test
    public void testSplitBubble() {
        //fail("Not yet implemented");
        // TODO Implement test
    }

    @Test
    public void testGetPoint() {
//        bubble = new Bubble(new Point(200, 200), 50, new Vector(5, 2));
//        assertEquals(bubble.getPoint(), new Point(200, 200));
    }

    @Test
    public void testGetRadius() {

//        bubble = new Bubble(new Point(200, 200), 50, new Vector(5, 2));
//        assertEquals(bubble.getRadius(), 50, 0);
    }
}
