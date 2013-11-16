package com.milo.geom;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D.Double;
import java.util.ArrayList;
import java.util.Arrays;

public class BallChain
{
	private int radius;
	private int numberRed;
	private int numberBlack;
	private Point2D topLeft;
	

	private Point2D goRight(Point2D pt, int distance)
	{
		return new Point2D.Double (pt.getX() + distance,pt.getY());	
	}
	
	public BallChain( Point2D topLeft,int numberBlack,int numberRed, int radius)
	{
      this.topLeft = topLeft;
      this.numberBlack = numberBlack;
      this.numberRed = numberRed;
      this.radius = radius;

	}
	
    public void draw(Graphics g)
    {
    	Graphics2D g2D = (Graphics2D)g;
    	Point2D currentPoint = topLeft;
    	Color oldColor = g.getColor();
    	for(int j=0;j<2;j++)
    	{
    		
    		g.setColor(Color.black);
    	for(int i=0; i<numberBlack;i++)
    	{
    		Circle circle = new Circle(currentPoint,radius);
    		circle.draw(g2D);
            currentPoint = goRight(currentPoint, 2*radius ); 
    	}
    	g.setColor(Color.GREEN);
    	for(int i=0; i<numberRed;i++)
    	{
    		Circle circle = new Circle(currentPoint,radius);
    		circle.draw(g2D);
            currentPoint = goRight(currentPoint, 2*radius ); 
    	}
    	}
    g.setColor(oldColor);
    }
}
    

