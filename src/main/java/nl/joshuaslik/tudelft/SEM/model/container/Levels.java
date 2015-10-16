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
import nl.joshuaslik.tudelft.SEM.control.gameObjects.IPhysicsObject;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.TimeDoor;

/**
 * Contains all of the levels
 *
 * @author faris
 */
public class Levels {

    private final static int AMOUNT_OF_IMPLEMENTED_LEVELS = 4;
    private static int currentLevel = 0;
    private static int unlockedLevel = 0;

    public static int amountOfLevels() {
        return AMOUNT_OF_IMPLEMENTED_LEVELS;
    }

    /**
     * Get the top left spawn point of the bubbles in survival mode.
     *
     * @return top left.
     */
    public static Point getCircleSpawnPointTopLeft() {
        return new Point(100, 350);
    }

    /**
     * Get the bottom right spawn point of the bubbles in survival mode.
     *
     * @return bottom right.
     */
    public static Point getCircleSpawnPointBottomRight() {
        return new Point(1800, 450);
    }

    public static ArrayList<IPhysicsObject> getSurvivalLevelObjects(final IGameObjects gameObjects) {
        return createLevel0(gameObjects);
    }

    /**
     * Get level of [index].
     *
     * @param gameObjects
     * @return the level.
     */
    public static ArrayList<IPhysicsObject> getLevelObjects(final IGameObjects gameObjects) {
        switch (currentLevel) {
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
    private static ArrayList<IPhysicsObject> createLevel0(final IGameObjects gameObjects) {
        ArrayList<IPhysicsObject> level0 = new ArrayList<>();
        level0.add(new Bubble(gameObjects, new Point(200, 500), 20, new Vector(-2, -5)));
        return level0;
    }

    /**
     * Create level 2.
     *
     * @return level 2.
     */
    private static ArrayList<IPhysicsObject> createLevel1(final IGameObjects gameObjects) {
        ArrayList<IPhysicsObject> level1 = new ArrayList<>();
        level1.add(new Bubble(gameObjects, new Point(300, 200), 20, new Vector(-2, -5)));
        level1.add(new Bubble(gameObjects, new Point(1300, 650), 40, new Vector(2, -5)));
        level1.add(new TimeDoor(gameObjects, new Point(1050, 30), new Point(1150, 30),
                new Point(1050, 851), new Point(1150, 851), 0, 5_000_000_000l));
        return level1;
    }

    /**
     * Create level 3.
     *
     * @return level 3.
     */
    private static ArrayList<IPhysicsObject> createLevel2(final IGameObjects gameObjects) {
        ArrayList<IPhysicsObject> level2 = new ArrayList<>();
        level2.add(new Bubble(gameObjects, new Point(300, 700), 40, new Vector(-2, -5)));
        level2.add(new Bubble(gameObjects, new Point(1200, 300), 20, new Vector(2, -5)));
        level2.add(new Bubble(gameObjects, new Point(1400, 620), 40, new Vector(2, -5)));
        level2.add(new BubbleDoor(gameObjects, new Point(1050, 30),
                new Point(1150, 30), new Point(1050, 851), new Point(1150, 851), 2));
        return level2;
    }

    /**
     * Create level 4.
     *
     * @return level 4.
     */
    private static ArrayList<IPhysicsObject> createLevel3(final IGameObjects gameObjects) {
        ArrayList<IPhysicsObject> level3 = new ArrayList<>();
        level3.add(new Bubble(gameObjects, new Point(100, 660), 40, new Vector(-2, -5)));
        level3.add(new Bubble(gameObjects, new Point(500, 620), 80, new Vector(2, -5)));
        level3.add(new Bubble(gameObjects, new Point(1300, 400), 40, new Vector(2, -5)));
        level3.add(new TimeDoor(gameObjects, new Point(250, 30), new Point(350, 30),
                new Point(250, 851), new Point(350, 851), 0, 10_000_000_000l));
        level3.add(new BubbleDoor(gameObjects, new Point(1050, 30), new Point(1150, 30),
                new Point(1050, 851), new Point(1150, 851), 1));
        return level3;
    }

    /**
     * Create level 5.
     *
     * @return level 5.
     */
    private static ArrayList<IPhysicsObject> createLevel4(final IGameObjects gameObjects) {
        ArrayList<IPhysicsObject> level4 = new ArrayList<>();
        level4.add(new Bubble(gameObjects, new Point(100, 600), 80, new Vector(-2, -5)));
        level4.add(new Bubble(gameObjects, new Point(400, 700), 40, new Vector(-2, -5)));
        level4.add(new Bubble(gameObjects, new Point(700, 700), 20, new Vector(2, -5)));
        level4.add(new Bubble(gameObjects, new Point(1300, 600), 40, new Vector(2, -5)));
        level4.add(new Bubble(gameObjects, new Point(1600, 600), 80, new Vector(2, -5)));
        level4.add(new TimeDoor(gameObjects, new Point(200, 30), new Point(300, 30),
                new Point(200, 851), new Point(300, 851), 0, 6_000_000_000l));
        level4.add(new TimeDoor(gameObjects, new Point(500, 30), new Point(600, 30),
                new Point(500, 851), new Point(600, 851), 0, 3_000_000_000l));
        level4.add(new TimeDoor(gameObjects, new Point(1100, 30), new Point(1200, 30),
                new Point(1100, 851), new Point(1200, 851), 0, 3_000_000_000l));
        level4.add(new TimeDoor(gameObjects, new Point(1400, 30), new Point(1500, 30),
                new Point(1400, 851), new Point(1500, 851), 0, 6_000_000_000l));
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

    public static void setUnlockedLevel(int unlocked) {
        unlockedLevel = unlocked;
    }
}
