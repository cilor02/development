package com.milo.geom;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D.Double;
import java.util.ArrayList;
import java.util.Arrays;

public class EnclosingRectangle
{
	private int height;
	private int width;
	private int unit;
	private int numberRect;
	private Point2D topLeft;
	
	private Point2D goLeft(Point2D pt, int distance)
	{
		return new Point2D.Double (pt.getX() - distance ,pt.getY());
	}
	
	private Point2D goRight(Point2D pt, int distance)
	{
		return new Point2D.Double (pt.getX() + distance,pt.getY());	
	}
	
	private Point2D goUp(Point2D pt, int distance)
	{
		return new Point2D.Double (pt.getX(),pt.getY() - distance);
	}
	
	private Point2D goDown(Point2D pt, int distance)
	{
		return new Point2D.Double (pt.getX(),pt.getY() + distance);	
	}
	
	public EnclosingRectangle( java.lang.Double unit,Point2D topLeft,int numberRect, int height,int width)
	{
      this.topLeft = topLeft;
      this.numberRect = numberRect;
      this.height = height;
      this.width = width;
      this.unit = unit.intValue();
	}
	
    public void draw(Graphics g)
    {
    	Graphics2D g2D = (Graphics2D)g;
    	Point2D currentPoint = topLeft;
    	for(int i=0; i<numberRect;i++)
    	{
    		g2D.draw(new Rectangle2D.Double(currentPoint.getX(), currentPoint.getY(), width, height));
            currentPoint = goRight(currentPoint, width ); 
    	}
    	//currentPoint = goRight(currentPoint, height );
    	for(int i=0; i<numberRect;i++)
    	{
    		g2D.draw(new Rectangle2D.Double(currentPoint.getX(), currentPoint.getY(), height, width ));
            currentPoint = goDown(currentPoint, width );
    	}
    	
    	//currentPoint = goDown(currentPoint, height ); 
    	currentPoint = goLeft(currentPoint, width - height );
    	
    	for(int i=0; i<numberRect;i++)
    	{
    		g2D.draw(new Rectangle2D.Double(currentPoint.getX(), currentPoint.getY(), width, height));
            
    		currentPoint = goLeft(currentPoint, width ); 
    	}
    	currentPoint = goRight(currentPoint, width - height); 
    	currentPoint = goUp(currentPoint, width - height );
    	for(int i=0; i<numberRect;i++)
    	{
    		g2D.draw(new Rectangle2D.Double(currentPoint.getX(), currentPoint.getY(), height, width ));
            currentPoint = goUp(currentPoint, width ); 
    	}
    }
}
    

