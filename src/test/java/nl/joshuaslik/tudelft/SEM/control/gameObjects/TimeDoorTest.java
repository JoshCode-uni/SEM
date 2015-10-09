package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import static org.junit.Assert.*;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ILineViewObject;
import nl.joshuaslik.tudelft.SEM.model.container.Point;

@RunWith(MockitoJUnitRunner.class)
public class TimeDoorTest {

	@Mock
	IGameObjects gameObjects;
	
	@Mock
	Point p1,p2,p3,p4;
	
	@Mock
	ILineViewObject l1,l2,l3,l4;
	
	@Mock
	Player player;
	
	
	Door door;
	
	
	Door spyDoor;
	
	/**
	 * setup mocks
	 */
	@Before
	public void setUp() {
		when(p1.getxPos()).thenReturn(0.0);
		when(p1.getyPos()).thenReturn(10.0);
		when(p2.getxPos()).thenReturn(5.0);
		when(p2.getyPos()).thenReturn(10.0);
		when(p3.getxPos()).thenReturn(0.0);
		when(p3.getyPos()).thenReturn(0.0);
		when(p4.getxPos()).thenReturn(5.0);
		when(p4.getyPos()).thenReturn(0.0);
		when(gameObjects.makeLine(0.0, 10.0, 5.0, 10.0)).thenReturn(l1);
		when(gameObjects.makeLine(0.0, 10.0, 0.0, 0.0)).thenReturn(l2);
		when(gameObjects.makeLine(5.0, 10.0, 5.0, 0.0)).thenReturn(l3);
		when(gameObjects.makeLine(0.0, 0.0, 5.0, 0.0)).thenReturn(l4);
		when(gameObjects.getPlayer()).thenReturn(player);
		door = new TimeDoor(gameObjects,p1,p2,p3,p4,0,10);
	}
	
	/**
	 * Test constructor
	 */
	@Test
	public void testTimeDoor() {
		verify(gameObjects,times(4)).addObject(isA(Line.class));
		verify(player).setDoor(0.0);
		verify(player).setDoor(5.0);
	}
	
	/**
	 * Tests if conditions for isOpen are correctly called.
	 */
	@Test
	public void testIsOpen() {
		assertFalse(door.isOpen());
		door.update(10);
		assertTrue(door.isOpen());
	}
	
	/**
	 * Tests if updating the door works correct.
	 */
	@Test
	public void testUpdateOpen() {
		spyDoor = Mockito.spy(door);
		spyDoor.update(10l);
		verify(spyDoor).update(10l);
		verify(spyDoor).isOpen();
		verify(spyDoor).destroy();
	}
	
	/**
	 * Tests if updating the door works correct.
	 */
	@Test
	public void testUpdateClosed() {
		spyDoor = Mockito.spy(door);
		spyDoor.update(0l);
		verify(spyDoor).update(0l);
		verify(spyDoor).isOpen();
		verify(spyDoor,never()).destroy();
	}

	/**
	 * Tests if everything is correctly removed when the door is destroyed.
	 */
	@Test
	public void testDestroy() {
		door.destroy();
		player.removeDoor(0.0);
		player.removeDoor(5.0);
		verify(l1).destroy();
		verify(l2).destroy();
		verify(l3).destroy();
		verify(l4).destroy();
		verify(gameObjects).removeObject(door);
	}
}
