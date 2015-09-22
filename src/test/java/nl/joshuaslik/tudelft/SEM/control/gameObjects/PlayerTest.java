/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.gameObjects;

import nl.joshuaslik.tudelft.SEM.control.viewController.IKeyboard;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.ICircleViewObject;
import nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects.IImageViewObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author faris
 */
@RunWith(MockitoJUnitRunner.class)
public class PlayerTest {

    @Mock
    IGameObjects gameObjects;

    @Mock
    IKeyboard keyboard;

    @Mock
    IImageViewObject image;

    @Mock
    Projectile projectile;
    
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
     * Test of prepareUpdate method, of class Player.
     */
    @Test
    public void testPrepareUpdate() {
        player.prepareUpdate(0);
        // empty method, can't test it.
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
        player.checkCollision(bubble, 1);
        player.checkCollision(bubble, 1);
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
     * Test of update method, of class Player.
     */
    @Test
    public void testUpdateMoveRight() {
        when(keyboard.isMoveRight()).thenReturn(true);
        player.update(10_000_000); // 10ms in ns

        // verify that the correct new X coordinate and X scale are set.
        verify(image).setX(5 + Player.getMAX_SPEED() / 100.0);
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
                makeProjectile(Mockito.any(IGameObjects.class), 
                        Mockito.anyDouble(), Mockito.anyDouble()); //5.5, 9.0
                
        
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
                makeProjectile(Mockito.any(IGameObjects.class), 
                        Mockito.anyDouble(), Mockito.anyDouble());
                
        
        // let the player shoot
        spyPlayer.update(10_000_000); // 10ms in ns

        // verify that the bullet has been created at the right location
        verify(spyPlayer).makeProjectile(gameObjects, 5.5, 10.0);
    }

    /**
     * Test of getClosestIntersection method, of class Player.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testGetClosestIntersection() {
        player.getClosestIntersection(null);
    }

    /**
     * Test of collide method, of class Player.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testCollide() {
        player.collide(null, 0);
    }

}
