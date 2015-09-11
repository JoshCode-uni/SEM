/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.model.container;

import java.util.ArrayList;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.Bubble;
import nl.joshuaslik.tudelft.SEM.control.gameObjects.Player;

/**
 * Contains all of the levels
 *
 * @author faris
 */
public class Levels {

	private final static int AMOUNT_OF_IMPLEMENTED_LEVELS = 5;
	
	public static int amountOfLevels() {
		return AMOUNT_OF_IMPLEMENTED_LEVELS;
	}

	/**
	 * Get level of [index].
	 * @param index the index of the level.
	 * @return the level.
	 */
	public static ArrayList<Bubble> getLevel(int index) {
		switch (index) {
			case 0:
				return createLevel0();
			case 1:
				return createLevel1();
			case 2:
				return createLevel2();
			case 3:
				return createLevel3();
			case 4:
				return createLevel4();
			default:
				throw new IllegalArgumentException();
		}
	}

	/**
	 * Create level 1.
	 * @return level 1.
	 */
	private static ArrayList<Bubble> createLevel0() {
		ArrayList<Bubble> level0 = new ArrayList<>();

		// create 1 bubble
		Point bubbleCenter = new Point(200, 300);
		double bubbleRadius = 20;
		Vector bubbleDirection = new Vector(-2, -5);
		Bubble bubble = new Bubble(bubbleCenter, bubbleRadius, bubbleDirection);

		level0.add(bubble);
		return level0;
	}

	/**
	 * Create level 2.
	 * @return level 2.
	 */
	private static ArrayList<Bubble> createLevel1() {
		ArrayList<Bubble> level1 = new ArrayList<>();

		// create 1st bubble
		Point bubbleCenter = new Point(300, 300);
		double bubbleRadius = 20;
		Vector bubbleDirection = new Vector(-2, -5);
		Bubble bubble = new Bubble(bubbleCenter, bubbleRadius, bubbleDirection);

		// create 2nd bubble
		Point bubble2Center = new Point(900, 400);
		double bubble2Radius = 40;
		Vector bubble2Direction = new Vector(2, -5);
		Bubble bubble2 = new Bubble(bubble2Center, bubble2Radius, bubble2Direction);

		level1.add(bubble);
		level1.add(bubble2);
		return level1;
	}

	/**
	 * Create level 3.
	 * @return level 3.
	 */
	private static ArrayList<Bubble> createLevel2() {
		ArrayList<Bubble> level2 = new ArrayList<>();

		// create 1st bubble
		Point bubbleCenter = new Point(300, 200);
		double bubbleRadius = 40;
		Vector bubbleDirection = new Vector(-2, -5);
		Bubble bubble = new Bubble(bubbleCenter, bubbleRadius, bubbleDirection);

		// create 2nd bubble
		Point bubble2Center = new Point(800, 500);
		double bubble2Radius = 20;
		Vector bubble2Direction = new Vector(2, -5);
		Bubble bubble2 = new Bubble(bubble2Center, bubble2Radius, bubble2Direction);

		// create 3rd bubble
		Point bubble3Center = new Point(900, 300);
		double bubble3Radius = 40;
		Vector bubble3Direction = new Vector(2, -5);
		Bubble bubble3 = new Bubble(bubble3Center, bubble3Radius, bubble3Direction);

		level2.add(bubble);
		level2.add(bubble2);
		level2.add(bubble3);
		return level2;
	}

	/**
	 * Create level 4.
	 * @return level 4.
	 */
	private static ArrayList<Bubble> createLevel3() {
		ArrayList<Bubble> level3 = new ArrayList<>();

		// create 1st bubble
		Point bubbleCenter = new Point(300, 400);
		double bubbleRadius = 40;
		Vector bubbleDirection = new Vector(-2, -5);
		Bubble bubble = new Bubble(bubbleCenter, bubbleRadius, bubbleDirection);

		// create 2nd bubble
		Point bubble2Center = new Point(100, 500);
		double bubble2Radius = 70;
		Vector bubble2Direction = new Vector(2, -5);
		Bubble bubble2 = new Bubble(bubble2Center, bubble2Radius, bubble2Direction);

		// create 3rd bubble
		Point bubble3Center = new Point(900, 400);
		double bubble3Radius = 40;
		Vector bubble3Direction = new Vector(2, -5);
		Bubble bubble3 = new Bubble(bubble3Center, bubble3Radius, bubble3Direction);

		level3.add(bubble);
		level3.add(bubble2);
		level3.add(bubble3);
		return level3;
	}

	/**
	 * Create level 5.
	 * @return level 5.
	 */
	private static ArrayList<Bubble> createLevel4() {
		ArrayList<Bubble> level4 = new ArrayList<>();

		// create 1st bubble
		Point bubbleCenter = new Point(300, 600);
		double bubbleRadius = 70;
		Vector bubbleDirection = new Vector(-2, -5);
		Bubble bubble = new Bubble(bubbleCenter, bubbleRadius, bubbleDirection);

		// create 2nd bubble
		Point bubble2Center = new Point(100, 600);
		double bubble2Radius = 20;
		Vector bubble2Direction = new Vector(-2, -5);
		Bubble bubble2 = new Bubble(bubble2Center, bubble2Radius, bubble2Direction);

		// create 3rd bubble
		Point bubble3Center = new Point(900, 600);
		double bubble3Radius = 20;
		Vector bubble3Direction = new Vector(2, -5);
		Bubble bubble3 = new Bubble(bubble3Center, bubble3Radius, bubble3Direction);

		// create 4rd bubble
		Point bubble4Center = new Point(800, 600);
		double bubble4Radius = 80;
		Vector bubble4Direction = new Vector(2, -5);
		Bubble bubble4 = new Bubble(bubble4Center, bubble4Radius, bubble4Direction);

		level4.add(bubble);
		level4.add(bubble2);
		level4.add(bubble3);
		level4.add(bubble4);
		return level4;
	}
}
