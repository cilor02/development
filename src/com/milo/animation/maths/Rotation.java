package com.milo.animation.maths;

import java.awt.geom.Point2D;

public class Rotation {
static public Point2D rotateClockwiseFromNorth(Point2D point,double distance,double angle)
{
	double p2Y = point.getY()-distance*Math.cos(angle);
	double p2X = distance*Math.sin(angle) + point.getX();
	return new Point2D.Double(p2X,p2Y);
	
}

static public double angleofRotationClockwiseFromNorth(Point2D p1,Point2D p2)
{
	double oppSide = p2.getX()-p1.getX();
	double adjSide = p1.getY()-p2.getY();
	return Math.atan(oppSide/adjSide);
}
}
