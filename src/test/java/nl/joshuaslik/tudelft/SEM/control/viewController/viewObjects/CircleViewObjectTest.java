package nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects;

import static org.junit.Assert.*;

import nl.joshuaslik.tudelft.SEM.control.viewController.GameController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

import javafx.scene.shape.Circle;

@RunWith(MockitoJUnitRunner.class)
public class CircleViewObjectTest {

    @Mock
    GameController gc;

    CircleViewObject cvo;

    /**
     * Initialize test.
     */
    @Before
    public void setup() {
        cvo = new CircleViewObject(2.0, 3.0, 4.0, gc);
    }

    /**
     * Tests if the constructor is initialized correct.
     */
    @Test
    public void testConstructor() {
        assertEquals(2.0, cvo.getCenterX(), 0);
        assertEquals(3.0, cvo.getCenterY(), 0);
        assertEquals(4.0, cvo.getRadius(), 0);
        verify(gc).drawNode(Mockito.any(Circle.class));
    }

    /**
     * Tests getter
     */
    @Test
    public void testGetNode() {
        assertEquals(((Circle) cvo.getNode()).getCenterX(), 2.0, 0.0);
        assertEquals(((Circle) cvo.getNode()).getCenterY(), 3.0, 0.0);
        assertEquals(((Circle) cvo.getNode()).getRadius(), 4.0, 0.0);
    }

    /**
     * Tests getter
     */
    @Test
    public void testGetCenterX() {
        assertEquals(2.0, cvo.getCenterX(), 0);
    }

    /**
     * Tests getter
     */
    @Test
    public void testGetCenterY() {
        assertEquals(3.0, cvo.getCenterY(), 0);
    }

    /**
     * Tests getter
     */
    @Test
    public void testGetRadius() {
        assertEquals(4.0, cvo.getRadius(), 0);
    }

    /**
     * Tests setter
     */
    @Test
    public void testSetCenterX() {
        cvo.setCenterX(5.0);
        assertEquals(5.0, cvo.getCenterX(), 0);
    }

    /**
     * Tests setter
     */
    @Test
    public void testSetCenterY() {
        cvo.setCenterY(4.0);
        assertEquals(4.0, cvo.getCenterY(), 0);
    }

    /**
     * Tests setter
     */
    @Test
    public void testSetRadius() {
        cvo.setRadius(1.0);
        assertEquals(1.0, cvo.getRadius(), 0);
    }

    /**
     * Tests boundaries
     */
    @Test
    public void testCheckXYOutOfBounds() {
        cvo.setBounds(0.0, 0.0, 10.0, 10.0);
        cvo.setCenterX(15);
        assertEquals(10.0, cvo.getCenterX(), 0.0);
        cvo.setCenterX(-0.5);
        assertEquals(0.0, cvo.getCenterX(), 0.0);
        cvo.setCenterY(15);
        assertEquals(10.0, cvo.getCenterY(), 0.0);
        cvo.setCenterY(-5);
        assertEquals(0.0, cvo.getCenterY(), 0.0);
    }

    /**
     * Tests boundaries
     */
    @Test
    public void testCheckXYInBounds() {
        cvo.setBounds(0.0, 0.0, 100.0, 100.0);
        cvo.setCenterX(15);
        assertEquals(15.0, cvo.getCenterX(), 0.0);
        cvo.setCenterY(75);
        assertEquals(75.0, cvo.getCenterY(), 0.0);
    }
}
