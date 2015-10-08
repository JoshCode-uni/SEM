/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.joshuaslik.tudelft.SEM.control.viewController.viewObjects;

/**
 * This interface defines the methods which should be implemented by the line
 * object class.
 *
 * @author faris
 */
public interface ILineViewObject extends IViewObject {
	
	/**
	 * Set the x coordinate of the start position of the line.
	 *
	 * @param xCoordinate x coordinate.
	 */
	public void setStartX(double xCoordinate);
	
	/**
	 * Set the y coordinate of the start position of the line.
	 *
	 * @param yCoordinate y coordinate.
	 */
	public void setStartY(double yCoordinate);
	
	/**
	 * Set the x coordinate of the end position of the line.
	 *
	 * @param xCoordinate x coordinate.
	 */
	public void setEndX(double xCoordinate);
	
	/**
	 * Set the y coordinate of the end position of the line.
	 *
	 * @param yCoordinate y coordinate.
	 */
	public void setEndY(double yCoordinate);
	
	/**
	 * @return the x coordinate of the start point.
	 */
	public double getStartX();
	
	/**
	 * @return the y coordinate of the start point.
	 */
	public double getStartY();
	
	/**
	 * @return the x coordinate of the end point.
	 */
	public double getEndX();
	
	/**
	 * @return the y coordinate of the end point.
	 */
	public double getEndY();
	
	/**
	 * Set the width of the line.
	 *
	 * @param width width of the line.
	 */
	public void setStrokeWidth(double width);
	
	/**
	 * Set the color of the line/
	 *
	 * @param red   value between 0 and 1.
	 * @param green value between 0 and 1.
	 * @param blue  value between 0 and 1.
	 */
	public void setColor(double red, double green, double blue);
	
	/**
	 * Set the opacity.
	 *
	 * @param opacity value between 0 and 1.
	 */
	public void setOpacity(double opacity);
}
