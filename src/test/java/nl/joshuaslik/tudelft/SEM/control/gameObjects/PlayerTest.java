/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.AbstractPlayerDecorator;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.pickup.powerup.player.IPlayerBaseModifier;
import nl.joshuaslik.tudelft.SEM.control.viewController.IKeyboard;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ICircleViewObject;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.IImageViewObject;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ILineViewObject;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.LineViewObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author faris
 */
@RunWith(MockitoJUnitRunner.class)
public class PlayerTest {
	
	@Mock
	IGameObjects gameObjects;
	
	@Mock
	IKeyboard keyboard;
	
	@Mock
	ILineViewObject line;
	
	@Mock
	IImageViewObject image;
	
	@Mock
	Projectile projectile;
	
	@Mock
	AbstractPlayerDecorator mod;
	
	@Mock
	ArrayList<Double> leftDoor, rightDoor;
	
	Player player;
	
	/**
	 * Setup the mocks.
	 */
	@Before
	public void setup() {
		
		// MOCK ALL DEPENDENCIES
		when(gameObjects.makeImage(null, 100, 100)).thenReturn(image);
		when(gameObjects.hasProjectile()).thenReturn(false);
		
		// the view will be a 10x10 square
		when(gameObjects.getLeftBorder()).thenReturn(0.0);
		when(gameObjects.getRightBorder()).thenReturn(10.0);
		when(gameObjects.getTopBorder()).thenReturn(0.0);
		when(gameObjects.getBottomBorder()).thenReturn(10.0);
		
		when(keyboard.isMoveLeft()).thenReturn(false);
		when(keyboard.isMoveRight()).thenReturn(false);
		when(keyboard.isShoot()).thenReturn(false);
		
		when(image.getStartX()).thenReturn(5.0);
		when(image.getStartY()).thenReturn(9.0);
		when(image.getEndX()).thenReturn(6.0);
		when(image.getEndY()).thenReturn(10.0);
		when(image.getHeight()).thenReturn(1.0);
		
		player = new Player(gameObjects, null, keyboard);
	}
	
	/**
	 * Test the constructor.
	 */
	@Test
	public void testPlayerSetImageX() {
		// player is initialize in setup
		
		// check if image x coordinate has been set to 5 exactly 1 time.
		verify(image).setX(5);
	}
	
	/**
	 * Test the constructor.
	 */
	@Test
	public void testPlayerSetImageY() {
		// player is initialize in setup
		
		// check if image x coordinate has been set to 9 exactly 1 time.
		verify(image).setY(9);
	}
	
	/**
	 * Test of checkCollision method, of class Player.
	 */
	@Test
	public void testCheckCollision() {
		// mock 2 additional objects
		Bubble bubble = Mockito.mock(Bubble.class);
		ICircleViewObject cvo = Mockito.mock(ICircleViewObject.class);
		
		when(bubble.getCircleViewObject()).thenReturn(cvo);
		when(image.intersects(cvo)).thenReturn(true);
		
		// die twice so player loses all of his lives
		player.checkCollision(bubble, 1);
		
		// check if player died method is called
		verify(gameObjects).playerDied();
	}
	
	/**
	 * Test of update method, of class Player.
	 */
	@Test
	public void testUpdateMoveLeft() {
		when(keyboard.isMoveLeft()).thenReturn(true);
		player.update(10_000_000); // 10ms in ns
		
		// verify that the correct new X coordinate and X scale are set.
		verify(image).setX(5 - Player.getMAX_SPEED() / 100.0);
		verify(image).setScaleX(1);
	}
	
	/**
	 * Test of update method of class Player.
	 */
	@Test
	public void testUpdateMoveLeftWall() {
		when(keyboard.isMoveLeft()).thenReturn(true);
		
		player.update(100_000_000);
		verify(image).setX(5);
		verify(image).setScaleX(1);
	}
	
	/**
	 * Test of update method of class Player.
	 */
	@Test
	public void testUpdateMoveRight() {
		when(keyboard.isMoveRight()).thenReturn(true);
		when(image.getStartX()).thenReturn(-190.0);
		player.update(0l);
		verify(image).setX(5.0);
		verify(image).setX(-190.0);
	}
	
	/**
	 * Test of update method, of class Player.
	 */
	@Test
	public void testUpdateMoveRightWall() {
		when(keyboard.isMoveRight()).thenReturn(true);
		player.update(100_000_000); // 10ms in ns
		
		// verify that the correct new X coordinate and X scale are set.
		verify(image).setX(5);
		verify(image).setScaleX(-1);
	}
	
	/**
	 * Test of update method, of class Player.
	 */
	@Test
	public void testUpdateShootBulletCreation() {
		when(keyboard.isShoot()).thenReturn(true);
		
		// mock the makeProjectile class of the player
		Player spyPlayer = Mockito.spy(player);
		doReturn(projectile).when(spyPlayer).
													makeProjectile(Mockito.any(IGameObjects.class), Mockito.anyDouble(), Mockito.anyDouble()); //5.5, 9.0
		
		// let the player shoot
		spyPlayer.update(10_000_000); // 10ms in ns
		
		// verify that the bullet has been added to the game.
		verify(gameObjects).addProjectile(projectile);
	}
	
	/**
	 * Test of update method, of class Player.
	 */
	@Test
	public void testUpdateShootBulletLocation() {
		when(keyboard.isShoot()).thenReturn(true);
		
		// mock the makeProjectile class of the player
		Player spyPlayer = Mockito.spy(player);
		doReturn(projectile).when(spyPlayer).
													makeProjectile(Mockito.any(IGameObjects.class), Mockito.anyDouble(), Mockito.anyDouble());
		
		// let the player shoot
		spyPlayer.update(10_000_000); // 10ms in ns
		
		// verify that the bullet has been created at the right location
		verify(spyPlayer).makeProjectile(gameObjects, 5.5, 10.0);
	}
	
	/**
	 * Tests if the modifier is correctly added.
	 */
	@Test
	public void testAddModifier() {
		player.addModifier(mod);
		verify(mod).decorate(isA(IPlayerBaseModifier.class));
	}
	
	/**
	 * Tests if the doors are correctly put into the arrays
	 */
	@Test
	public void testSetDoor() {
		player.setLeftDoor(leftDoor);
		player.setRightDoor(rightDoor);
		when(image.getStartX()).thenReturn(10.0);
		player.setDoor(30.0);
		verify(leftDoor, never()).add(Mockito.anyDouble());
		verify(rightDoor, times(1)).add(30.0);
	}
	
	/**
	 * Tests if the right door is set in the right way.
	 */
	@Test
	public void testSetRightDoor() {
		player.setLeftDoor(leftDoor);
		player.setRightDoor(rightDoor);
		when(image.getStartX()).thenReturn(10.0);
		player.setDoor(1.0);
		verify(leftDoor, times(1)).add(1.0);
		verify(rightDoor, never()).add(Mockito.anyDouble());
	}
	
	/**
	 * Tests if the right methods are called when trying to remove
	 * objects from the array
	 */
	@Test
	public void testRemoveDoor() {
		player.setLeftDoor(leftDoor);
		player.setRightDoor(rightDoor);
		player.removeDoor(50.0);
		verify(leftDoor, times(1)).remove(50.0);
		verify(rightDoor, times(1)).remove(50.0);
	}
	
	/**
	 * Tests if the doors are interacting in the correct way with the
	 * image when called by Update.
	 */
	@Test
	public void testUpdateMoveDoorsLeft() {
		Iterator<Double> doorIterator = Mockito.mock(Iterator.class);
		when(doorIterator.hasNext()).thenReturn(true, true, false);
		when(doorIterator.next()).thenReturn(3.0).thenReturn(12.0);
		when(keyboard.isMoveLeft()).thenReturn(true);
		player.setLeftDoor(leftDoor);
		when(image.getStartX()).thenReturn(10.0);
		System.out.println(image.getStartX());
		when(leftDoor.iterator()).thenReturn(doorIterator);
		player.update(100_000_000l);
		verify(image).setX(5.0);
		verify(image).setX(3.0);
	}
	
	/**
	 * Tests if the doors are interacting in the correct way with the
	 * image when called by Update.
	 */
	@Test
	public void testUpdateMoveDoorsRight() {
		Iterator<Double> doorIterator = Mockito.mock(Iterator.class);
		when(doorIterator.hasNext()).thenReturn(true, true, false);
		when(doorIterator.next()).thenReturn(17.0).thenReturn(20.0);
		when(keyboard.isMoveRight()).thenReturn(true);
		player.setRightDoor(rightDoor);
		when(gameObjects.getRightBorder()).thenReturn(20.0);
		when(image.getStartX()).thenReturn(30.0);
		System.out.println(image.getStartX());
		when(rightDoor.iterator()).thenReturn(doorIterator);
		player.update(1_000_000l);
		verify(image).setX(5.0);
		verify(image).setX(-83.0);
	}
	
	/**
	 * Tests the makeProjectile Method (which is used for testing)
	 */
	@Test
	public void testMakeProjectile() {
		LineViewObject lv = Mockito.mock(LineViewObject.class);
		
		doNothing().when(lv).setStrokeWidth(7);
		doNothing().when(lv).setColor(0.2, 0.1, 0.1);
		doNothing().when(lv).setOpacity(0.8);
		when(gameObjects.makeLine(0.0, -2.0, 0.0, -3.0)).thenReturn(lv);
		player.makeProjectile(gameObjects, 0.0, 0.0);
		
	}
	
	/**
	 * Tests if the correct image is returned by the getter
	 */
	@Test
	public void testGetImage() {
		assertEquals(image, player.getImage());
		
	}
}
