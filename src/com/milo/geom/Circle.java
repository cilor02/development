package com.milo.geom;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Circle {
private Point2D centre;
private double radius;
private Ellipse2D circleShape;

public Circle(Point2D centre, double radius) {
	this.centre = centre;
	this.radius = radius;
	circleShape = this.deriveCircleShape();
	
}
public Point2D getCentre() {
	return centre;
}
public void setCentre(Point2D centre) {
	this.centre = centre;
}
public double getRadius() {
	return radius;
}
public void setRadius(double radius) {
	this.radius = radius;
}

private Rectangle2D getBoundingBox()
{
	double topLeftX = centre.getX() - radius;
	double topLeftY = centre.getY() - radius;
	return new Rectangle2D.Double(topLeftX, topLeftY,2*radius,2*radius);
}

private Ellipse2D deriveCircleShape()
{
	double topLeftX = centre.getX() - radius;
	double topLeftY = centre.getY() - radius;
	return new Ellipse2D.Double(topLeftX, topLeftY,2*radius,2*radius);
}

public void draw(Graphics2D g)
{
 g.draw(circleShape);
}
public void setCircleShape(Ellipse2D circleShape) {
	this.circleShape = circleShape;
}
public Ellipse2D getCircleShape() {
	return circleShape;
}
}
