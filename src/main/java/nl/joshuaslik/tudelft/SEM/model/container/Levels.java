/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.model.container;

import java.util.ArrayList;

import nl.joshuaslik.tudelft.SEM.control.gameObjects.Bubble;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.BubbleDoor;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.IGameObjects;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.PhysicsObject;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.TimeDoor;

/**
 * Contains all of the levels
 *
 * @author faris
 */
public class Levels {

    private final static int AMOUNT_OF_IMPLEMENTED_LEVELS = 4;
    private static       int currentLevel                 = 0;
    private static       int unlockedLevel                = 0;

    public static int amountOfLevels() {
        return AMOUNT_OF_IMPLEMENTED_LEVELS;
    }

    /**
     * Get level of [index].
     *
     * @param index       the index of the level.
     * @param gameObjects
     * @return the level.
     */
    public static ArrayList<PhysicsObject> getLevelObjects(int index, IGameObjects gameObjects) {
        switch (index) {
            case 0:
                return createLevel0(gameObjects);
            case 1:
                return createLevel1(gameObjects);
            case 2:
                return createLevel2(gameObjects);
            case 3:
                return createLevel3(gameObjects);
            case 4:
                return createLevel4(gameObjects);
            default:
                return createLevel4(gameObjects);
        }
    }

    /**
     * Create level 1.
     *
     * @return level 1.
     */
    private static ArrayList<PhysicsObject> createLevel0(IGameObjects gameObjects) {
        ArrayList<PhysicsObject> level0 = new ArrayList<>();

        // create 1 bubble
        Point  bubbleCenter    = new Point(200, 500);
        double bubbleRadius    = 20;
        Vector bubbleDirection = new Vector(-2, -5);
        Bubble bubble          = new Bubble(gameObjects, bubbleCenter, bubbleRadius, bubbleDirection);

        level0.add(bubble);
        return level0;
    }

    /**
     * Create level 2.
     *
     * @return level 2.
     */
    private static ArrayList<PhysicsObject> createLevel1(IGameObjects gameObjects) {
        ArrayList<PhysicsObject> level1 = new ArrayList<>();

        // create 1st bubble
        Point  bubbleCenter    = new Point(300, 200);
        double bubbleRadius    = 20;
        Vector bubbleDirection = new Vector(-2, -5);
        Bubble bubble          = new Bubble(gameObjects, bubbleCenter, bubbleRadius, bubbleDirection);

        // create 2nd bubble
        Point  bubble2Center    = new Point(1300, 650);
        double bubble2Radius    = 40;
        Vector bubble2Direction = new Vector(2, -5);
        Bubble bubble2          = new Bubble(gameObjects, bubble2Center, bubble2Radius, bubble2Direction);

        level1.add(bubble);
        level1.add(bubble2);

        // Create timeDoor
        Point ul = new Point(1050, 30);
        Point ur = new Point(1150, 30);
        Point bl = new Point(1050, 851);
        Point br = new Point(1150, 851);

        TimeDoor timeDoor = new TimeDoor(gameObjects, ul, ur, bl, br, 0, 5_000_000_000l);

        level1.add(timeDoor);
        return level1;
    }

    /**
     * Create level 3.
     *
     * @return level 3.
     */
    private static ArrayList<PhysicsObject> createLevel2(IGameObjects gameObjects) {
        ArrayList<PhysicsObject> level2 = new ArrayList<>();

        // create 1st bubble
        Point  bubbleCenter    = new Point(300, 700);
        double bubbleRadius    = 40;
        Vector bubbleDirection = new Vector(-2, -5);
        Bubble bubble          = new Bubble(gameObjects, bubbleCenter, bubbleRadius, bubbleDirection);

        // create 2nd bubble
        Point  bubble2Center    = new Point(1200, 300);
        double bubble2Radius    = 20;
        Vector bubble2Direction = new Vector(2, -5);
        Bubble bubble2          = new Bubble(gameObjects, bubble2Center, bubble2Radius, bubble2Direction);

        // create 3rd bubble
        Point  bubble3Center    = new Point(1400, 620);
        double bubble3Radius    = 40;
        Vector bubble3Direction = new Vector(2, -5);
        Bubble bubble3          = new Bubble(gameObjects, bubble3Center, bubble3Radius, bubble3Direction);

        level2.add(bubble);
        level2.add(bubble2);
        level2.add(bubble3);

        // Create bubbleDoor
        Point ul = new Point(1050, 30);
        Point ur = new Point(1150, 30);
        Point bl = new Point(1050, 851);
        Point br = new Point(1150, 851);

        BubbleDoor bubbleDoor = new BubbleDoor(gameObjects, ul, ur, bl, br, 2);
        level2.add(bubbleDoor);
        return level2;
    }

    /**
     * Create level 4.
     *
     * @return level 4.
     */
    private static ArrayList<PhysicsObject> createLevel3(IGameObjects gameObjects) {
        ArrayList<PhysicsObject> level3 = new ArrayList<>();

        // create 1st bubble
        Point  bubbleCenter    = new Point(100, 660);
        double bubbleRadius    = 40;
        Vector bubbleDirection = new Vector(-2, -5);
        Bubble bubble          = new Bubble(gameObjects, bubbleCenter, bubbleRadius, bubbleDirection);

        // create 2nd bubble
        Point  bubble2Center    = new Point(500, 620);
        double bubble2Radius    = 80;
        Vector bubble2Direction = new Vector(2, -5);
        Bubble bubble2          = new Bubble(gameObjects, bubble2Center, bubble2Radius, bubble2Direction);

        // create 3rd bubble
        Point  bubble3Center    = new Point(1300, 400);
        double bubble3Radius    = 40;
        Vector bubble3Direction = new Vector(2, -5);
        Bubble bubble3          = new Bubble(gameObjects, bubble3Center, bubble3Radius, bubble3Direction);

        level3.add(bubble);
        level3.add(bubble2);
        level3.add(bubble3);

        // Create timeDoor
        Point ul = new Point(250, 30);
        Point ur = new Point(350, 30);
        Point bl = new Point(250, 851);
        Point br = new Point(350, 851);

        TimeDoor timeDoor = new TimeDoor(gameObjects, ul, ur, bl, br, 0, 10_000_000_000l);

        // Create bubbleDoor
        Point ul2 = new Point(1050, 30);
        Point ur2 = new Point(1150, 30);
        Point bl2 = new Point(1050, 851);
        Point br2 = new Point(1150, 851);

        BubbleDoor bubbleDoor = new BubbleDoor(gameObjects, ul2, ur2, bl2, br2, 1);

        level3.add(timeDoor);
        level3.add(bubbleDoor);

        return level3;
    }

    /**
     * Create level 5.
     *
     * @return level 5.
     */
    private static ArrayList<PhysicsObject> createLevel4(IGameObjects gameObjects) {
        ArrayList<PhysicsObject> level4 = new ArrayList<>();

        // create 1st bubble
        Point  bubbleCenter    = new Point(100, 600);
        double bubbleRadius    = 80;
        Vector bubbleDirection = new Vector(-2, -5);
        Bubble bubble          = new Bubble(gameObjects, bubbleCenter, bubbleRadius, bubbleDirection);

        // create 2nd bubble
        Point  bubble2Center    = new Point(400, 700);
        double bubble2Radius    = 40;
        Vector bubble2Direction = new Vector(-2, -5);
        Bubble bubble2          = new Bubble(gameObjects, bubble2Center, bubble2Radius, bubble2Direction);

        // create 3rd bubble
        Point  bubble3Center    = new Point(700, 700);
        double bubble3Radius    = 20;
        Vector bubble3Direction = new Vector(2, -5);
        Bubble bubble3          = new Bubble(gameObjects, bubble3Center, bubble3Radius, bubble3Direction);

        // create 4rd bubble
        Point  bubble4Center    = new Point(1300, 600);
        double bubble4Radius    = 40;
        Vector bubble4Direction = new Vector(2, -5);
        Bubble bubble4          = new Bubble(gameObjects, bubble4Center, bubble4Radius, bubble4Direction);

        // create 5th bubble
        Point  bubble5Center    = new Point(1600, 600);
        double bubble5Radius    = 80;
        Vector bubble5Direction = new Vector(2, -5);
        Bubble bubble5          = new Bubble(gameObjects, bubble5Center, bubble5Radius, bubble5Direction);

        level4.add(bubble);
        level4.add(bubble2);
        level4.add(bubble3);
        level4.add(bubble4);
        level4.add(bubble5);

        // Create timeDoor
        Point ul = new Point(200, 30);
        Point ur = new Point(300, 30);
        Point bl = new Point(200, 851);
        Point br = new Point(300, 851);

        TimeDoor timeDoor = new TimeDoor(gameObjects, ul, ur, bl, br, 0, 6_000_000_000l);

        // Create timeDoor
        Point ul2 = new Point(500, 30);
        Point ur2 = new Point(600, 30);
        Point bl2 = new Point(500, 851);
        Point br2 = new Point(600, 851);

        TimeDoor timeDoor2 = new TimeDoor(gameObjects, ul2, ur2, bl2, br2, 0, 3_000_000_000l);

        // Create timeDoor
        Point ul3 = new Point(1100, 30);
        Point ur3 = new Point(1200, 30);
        Point bl3 = new Point(1100, 851);
        Point br3 = new Point(1200, 851);

        TimeDoor timeDoor3 = new TimeDoor(gameObjects, ul3, ur3, bl3, br3, 0, 3_000_000_000l);

        // Create timeDoor
        Point ul4 = new Point(1400, 30);
        Point ur4 = new Point(1500, 30);
        Point bl4 = new Point(1400, 851);
        Point br4 = new Point(1500, 851);

        TimeDoor timeDoor4 = new TimeDoor(gameObjects, ul4, ur4, bl4, br4, 0, 6_000_000_000l);

        level4.add(timeDoor);
        level4.add(timeDoor2);
        level4.add(timeDoor3);
        level4.add(timeDoor4);

        return level4;
    }

    public static int getCurrentLevel() {
        return currentLevel;
    }

    public static void nextLevel() {
        if (currentLevel < AMOUNT_OF_IMPLEMENTED_LEVELS) {
            currentLevel++;
        }
        if (currentLevel > unlockedLevel) {
            unlockedLevel = currentLevel;
        }
    }

    public static void setCurrentLevel(int level) {
        if (level < unlockedLevel) {
            Levels.currentLevel = level;
        } else {
            Levels.currentLevel = unlockedLevel;
        }
    }

    public static int getUnlockedLevel() {
        return unlockedLevel;
    }
}
