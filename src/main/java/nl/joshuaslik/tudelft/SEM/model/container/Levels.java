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

    /**
     * Get the survival level objects.
     * @param gameObjects the game object storage class.
     * @return the survival game objects.
     */
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
    }

    /**
     * Create level 2.
     *
     * @return level 2.
     */
    private static ArrayList<IPhysicsObject> createLevel1(final IGameObjects gameObjects) {
        return createLevel(gameObjects, 1);
    }

    /**
     * Create level 3.
     *
     * @return level 3.
     */
    private static ArrayList<IPhysicsObject> createLevel2(final IGameObjects gameObjects) {
        return createLevel(gameObjects, 2);
    }

    /**
     * Create level 4.
     *
     * @return level 4.
     */
    private static ArrayList<IPhysicsObject> createLevel3(final IGameObjects gameObjects) {
        return createLevel(gameObjects, 3);
    }

    /**
     * Create level 5.
     *
     * @return level 5.
     */
    private static ArrayList<IPhysicsObject> createLevel4(final IGameObjects gameObjects) {
        return createLevel(gameObjects, 4);
    }
    
    /**
     * Create a level.
     * @param gameObjects the gameobjects.
     * @param lvl the level to create.
     * @return an array list containing all objects of the level.
     */
    private static ArrayList<IPhysicsObject> createLevel(final IGameObjects gameObjects, int lvl) {
        ArrayList<IPhysicsObject> result = new ArrayList<>();
    
        XMLFile file = SAXParser.parseFile("/data/levels/levels.xml");
        XMLTag level = file.getElement("levels").getElementByAttribute("id", "l" + lvl);
        
        result.addAll(createBubbles(gameObjects, level));
        result.addAll(createDoors(gameObjects, level));
        
        return result;
    }
    
    /**
     * Create the bubbles of a level.
     * @param gameObjects the game objects.
     * @param level the level.
     * @return an array list containing all bubbles of the level.
     */
    private static ArrayList<IPhysicsObject> createBubbles(final IGameObjects gameObjects, XMLTag level) {
        ArrayList<IPhysicsObject> result = new ArrayList<>();
        
        XMLTag bubbles = level.getElement("bubbles");
        
        for(XMLTag bubble : bubbles.getElements()) {
            result.add(createBubble(gameObjects, bubble));
        }
        
        return result;
    }
    
    /**
     * Create all doors of the level.
     * @param gameObjects the gameobjects.
     * @param level the level
     * @return an array list containing all doors of the level.
     */
    private static ArrayList<IPhysicsObject> createDoors(final IGameObjects gameObjects, XMLTag level) {
        ArrayList<IPhysicsObject> result = new ArrayList<>();
    
        XMLTag doors = level.getElement("doors");
        
        for(XMLTag door : doors.getElements()) {
            result.add(createDoor(gameObjects, door));
        }
    
        return result;
    }
    
    /**
     * Create a bubble of a level.
     * @param gameObjects the gameobjects.
     * @param bubble the xml bubble to parse.
     * @return the bubble.
     */
    private static Bubble createBubble(final IGameObjects gameObjects, XMLTag bubble) {
        Point p = new Point(Double.parseDouble(bubble.getAttribute("x")), Double.parseDouble(bubble.getAttribute("y")));
        Vector dir = new Vector(Double.parseDouble(bubble.getElement("direction").getAttribute("x")),
                                       Double.parseDouble(bubble.getElement("direction").getAttribute("y")));
        return new Bubble(gameObjects, p, Double.parseDouble(bubble.getElement("radius").getContent()), dir);
    }
    
    /**
     * Create a door of a level.
     * @param gameObjects the gameobjects.
     * @param door the xml door to parse.
     * @return the door.
     */
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
    
    /**
     * Get the current level.
     * @return the current level.
     */
    public static int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Go to the next level.
     */
    public static void nextLevel() {
        if (currentLevel < AMOUNT_OF_IMPLEMENTED_LEVELS) {
            currentLevel++;
        }
        if (currentLevel > unlockedLevel) {
            unlockedLevel = currentLevel;
        }
    }

    /**
     * Set the current level.
     * @param level the current level index.
     */
    public static void setCurrentLevel(int level) {
        if (level < unlockedLevel) {
            Levels.currentLevel = level;
        } else {
            Levels.currentLevel = unlockedLevel;
        }
    }

    /**
     * Get the highest unlocked level.
     * @return the highest unlocked level index.
     */
    public static int getUnlockedLevel() {
        return unlockedLevel;
    }

    /**
     * Set the unlocked level index.
     * @param unlocked the highest unlocked level index.
     */
    public static void setUnlockedLevel(int unlocked) {
        unlockedLevel = unlocked;
    }
}
