package com.milo.geom;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Polygon {

	private int numberSides;
	private double sideLength;
	private Point2D startPoint;
	
public void draw(Graphics2D g)
{
	double incrAngle =  (Math.PI*2/numberSides);
	Point2D p1 = startPoint;
	Point2D p2 = startPoint;
	for(int i=0;i<numberSides;i++)
	{
		p2 = RelativePositioner.getPoint(p1, incrAngle * i,sideLength);
	    g.draw(new Line2D.Double(p1,p2));
	    p1=p2;
	}
}

Polygon(int sides, double length, Point2D point)
{
	numberSides=sides;
	sideLength=length;
	startPoint = point;
}

}
