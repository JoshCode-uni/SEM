/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model.container;

/**
 *
 * @author faris
 */
public class Point {
	
	private final double xPos; // stores x position
    private final double yPos; // stores y position

	public Point(double xPos, double yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public double distanceTo(Point p2) {
		return Math.sqrt((xPos - p2.xPos) * (xPos - p2.xPos) + (yPos - p2.yPos) * (yPos - p2.yPos));
	}
	
	public Point translate(double deltaX, double deltaY) {
		return new Point(xPos + deltaX, yPos + deltaY);
	}
	
	public Point translate(Point p) {
		return translate(p.xPos, p.yPos);
	}

	public double getxPos() {
		return xPos;
	}

	public double getyPos() {
		return yPos;
	}
	
	@Override
    public String toString() {
        return "Position: (x: " + xPos + ", y: " + yPos + ")";
    }

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Point other = (Point) obj;
		if (Double.doubleToLongBits(this.xPos) != Double.doubleToLongBits(other.xPos)) {
			return false;
		}
		if (Double.doubleToLongBits(this.yPos) != Double.doubleToLongBits(other.yPos)) {
			return false;
		}
		return true;
	}
	
	
}
