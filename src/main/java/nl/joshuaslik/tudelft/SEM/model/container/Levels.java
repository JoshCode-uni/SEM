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
import nl.joshuaslik.tudelft.SEM.utility.xml.SAXParser;
import nl.joshuaslik.tudelft.SEM.utility.xml.XMLFile;
import nl.joshuaslik.tudelft.SEM.utility.xml.XMLTag;

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
        return createLevel(gameObjects, 0);
        //ArrayList<IPhysicsObject> level0 = new ArrayList<>();
        //level0.add(new Bubble(gameObjects, new Point(200, 500), 20, new Vector(-2, -5)));
        //return level0;
    }

    /**
     * Create level 2.
     *
     * @return level 2.
     */
    private static ArrayList<IPhysicsObject> createLevel1(final IGameObjects gameObjects) {
        return createLevel(gameObjects, 1);
        //ArrayList<IPhysicsObject> level1 = new ArrayList<>();
        //level1.add(new Bubble(gameObjects, new Point(300, 200), 20, new Vector(-2, -5)));
        //level1.add(new Bubble(gameObjects, new Point(1300, 650), 40, new Vector(2, -5)));
        //level1.add(new TimeDoor(gameObjects, new Point(1050, 30), new Point(1150, 30),
        //        new Point(1050, 851), new Point(1150, 851), 0, 5_000_000_000l));
        //return level1;
    }

    /**
     * Create level 3.
     *
     * @return level 3.
     */
    private static ArrayList<IPhysicsObject> createLevel2(final IGameObjects gameObjects) {
        return createLevel(gameObjects, 2);
        //ArrayList<IPhysicsObject> level2 = new ArrayList<>();
        //level2.add(new Bubble(gameObjects, new Point(300, 700), 40, new Vector(-2, -5)));
        //level2.add(new Bubble(gameObjects, new Point(1200, 300), 20, new Vector(2, -5)));
        //level2.add(new Bubble(gameObjects, new Point(1400, 620), 40, new Vector(2, -5)));
        //level2.add(new BubbleDoor(gameObjects, new Point(1050, 30),
        //        new Point(1150, 30), new Point(1050, 851), new Point(1150, 851), 2));
        //return level2;
    }

    /**
     * Create level 4.
     *
     * @return level 4.
     */
    private static ArrayList<IPhysicsObject> createLevel3(final IGameObjects gameObjects) {
        return createLevel(gameObjects, 3);
        //ArrayList<IPhysicsObject> level3 = new ArrayList<>();
        //level3.add(new Bubble(gameObjects, new Point(100, 660), 40, new Vector(-2, -5)));
        //level3.add(new Bubble(gameObjects, new Point(500, 620), 80, new Vector(2, -5)));
        //level3.add(new Bubble(gameObjects, new Point(1300, 400), 40, new Vector(2, -5)));
        //level3.add(new TimeDoor(gameObjects, new Point(250, 30), new Point(350, 30),
        //        new Point(250, 851), new Point(350, 851), 0, 10_000_000_000l));
        //level3.add(new BubbleDoor(gameObjects, new Point(1050, 30), new Point(1150, 30),
        //        new Point(1050, 851), new Point(1150, 851), 1));
        //return level3;
    }

    /**
     * Create level 5.
     *
     * @return level 5.
     */
    private static ArrayList<IPhysicsObject> createLevel4(final IGameObjects gameObjects) {
        return createLevel(gameObjects, 4);
        //ArrayList<IPhysicsObject> level4 = new ArrayList<>();
        //level4.add(new Bubble(gameObjects, new Point(100, 600), 80, new Vector(-2, -5)));
        //level4.add(new Bubble(gameObjects, new Point(400, 700), 40, new Vector(-2, -5)));
        //level4.add(new Bubble(gameObjects, new Point(700, 700), 20, new Vector(2, -5)));
        //level4.add(new Bubble(gameObjects, new Point(1300, 600), 40, new Vector(2, -5)));
        //level4.add(new Bubble(gameObjects, new Point(1600, 600), 80, new Vector(2, -5)));
        //level4.add(new TimeDoor(gameObjects, new Point(200, 30), new Point(300, 30),
        //        new Point(200, 851), new Point(300, 851), 0, 6_000_000_000l));
        //level4.add(new TimeDoor(gameObjects, new Point(500, 30), new Point(600, 30),
        //        new Point(500, 851), new Point(600, 851), 0, 3_000_000_000l));
        //level4.add(new TimeDoor(gameObjects, new Point(1100, 30), new Point(1200, 30), new Point(1100, 851), new Point(1200, 851), 0, 3_000_000_000l));
        //level4.add(new TimeDoor(gameObjects, new Point(1400, 30), new Point(1500, 30), new Point(1400, 851), new Point(1500, 851), 0, 6_000_000_000l));
        //return level4;
    }
    
    private static ArrayList<IPhysicsObject> createLevel(final IGameObjects gameObjects, int lvl) {
        ArrayList<IPhysicsObject> result = new ArrayList<>();
    
        XMLFile file = SAXParser.parseFile("/data/levels/levels.xml");
        XMLTag level = file.getElement("levels").getElementByAttribute("id", "l" + lvl);
        
        result.addAll(createBubbles(gameObjects, level));
        result.addAll(createDoors(gameObjects, level));
        
        return result;
    }
    
    private static ArrayList<IPhysicsObject> createBubbles(final IGameObjects gameObjects, XMLTag level) {
        ArrayList<IPhysicsObject> result = new ArrayList<>();
        
        XMLTag bubbles = level.getElement("bubbles");
        
        for(XMLTag bubble : bubbles.getElements()) {
            result.add(createBubble(gameObjects, bubble));
        }
        
        return result;
    }
    
    private static ArrayList<IPhysicsObject> createDoors(final IGameObjects gameObjects, XMLTag level) {
        ArrayList<IPhysicsObject> result = new ArrayList<>();
    
        XMLTag doors = level.getElement("doors");
        
        for(XMLTag door : doors.getElements()) {
            result.add(createDoor(gameObjects, door));
        }
    
        return result;
    }
    
    private static Bubble createBubble(final IGameObjects gameObjects, XMLTag bubble) {
        Point p = new Point(Double.parseDouble(bubble.getAttribute("x")), Double.parseDouble(bubble.getAttribute("y")));
        Vector dir = new Vector(Double.parseDouble(bubble.getElement("direction").getAttribute("x")),
                                       Double.parseDouble(bubble.getElement("direction").getAttribute("y")));
        return new Bubble(gameObjects, p, Double.parseDouble(bubble.getElement("radius").getContent()), dir);
    }
    
    private static IPhysicsObject createDoor(final IGameObjects gameObjects, XMLTag door) {
        XMLTag position = door.getElement("position");
        
        Double x = Double.parseDouble(position.getAttribute("x"));
        Double y = Double.parseDouble(position.getAttribute("y"));
        Double width = Double.parseDouble(position.getElement("width").getContent());
        Double height = Double.parseDouble(position.getElement("height").getContent());
        
        Point p1 = new Point(x, y);
        Point p2 = new Point(x + width, y);
        Point p3 = new Point(x, y + height);
        Point p4 = new Point(x + width, y + height);
        
        if (door.getAttribute("type").equals("time")) {
            Long time = Long.parseLong(door.getElement("activate").getElement("time_ns").getContent());
            return new TimeDoor(gameObjects, p1, p2, p3, p4, 0, time);
        } else {
            Integer remaining = Integer.parseInt(door.getElement("activate").getElement("remaining").getContent());
            return new BubbleDoor(gameObjects, p1, p2, p3, p4, remaining);
        }
        
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
