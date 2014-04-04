package uk.ac.cam.acr31.turtle;

import java.awt.Color;

public class Turtle implements Cloneable {

	private double x;
	private double y;
	private double angle;
	private Color color;
	
	public Turtle(double x, double y, double angle) {
		super();
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.color = Color.getHSBColor((float)Math.random(),1.0f,1.0f);
	}

	public Turtle(Turtle t) {
		this(t.x,t.y,t.angle);
	}

	public void turn(double degrees) {
		angle += degrees;
		angle %= 360;
	}
	
	public void forward(double distance) {
		x += distance * Math.cos(toRadians(angle));
		y += distance * Math.sin(toRadians(angle));
	}
	
	private static double toRadians(double degrees) {
		return (double)degrees / 180.0 * Math.PI;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public Color getColor() {
		return color;
	}
	
}
