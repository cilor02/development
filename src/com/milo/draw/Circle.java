package com.milo.draw;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Circle implements Drawable{
    public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	private double radius;
    public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	private double x;
    private double y;
    public Circle(Point2D ctr,double rad)
    {
    	x=ctr.getX();
    	y=ctr.getY();
    	radius=rad;
    }
    
    public Circle(double x, double y,double rad)
    {
    	this.x=x;
    	this.y=y;
    	radius=rad;
    }
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		Ellipse2D circle= new Ellipse2D.Double(x-radius,y-radius,2*radius,2*radius);
		g.fill(circle);
	}

}
