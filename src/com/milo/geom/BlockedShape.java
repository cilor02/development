package com.milo.geom;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class BlockedShape{

	private int unit;
	private int height;
	private int width;
	private List<Point2D> points;
	private Random rand = new Random();
	private Point2D startPoint;
	private Shape upperTriangle;
	public BlockedShape( int unit, int height, int width, double startX, double startY) {
        this.height = height;
        this.width = width;
		this.unit= unit;
		startPoint = new Point2D.Double(startX,startY);
		int[]xints = {0,width,0};
		int[]yints = {0,0,height};
		points = new ArrayList<Point2D>();
		upperTriangle = new java.awt.Polygon(xints,yints,3);
		derivePoints();
	}

	private void derivePoints ()
	{
		Point2D currentPoint = startPoint;
		int moves = (int)(rand.nextDouble() * 6);
		for(int i = 0 ; i < (moves + 4);i++)
		{
			do {
			int distance = (int)(rand.nextDouble() * 4) + 1;
			int direction = (int)(rand.nextDouble() * 4) + 1;
            
			switch(direction)
			{
			case 1:
				currentPoint = goLeft(currentPoint,distance);
              break;
			case 2:
				currentPoint = goRight(currentPoint,distance);
	              break;
			case 3:
				currentPoint = goUp(currentPoint,distance);
	              break;
			case 4:
				currentPoint = goDown(currentPoint,distance);
	              break;  
			}
			}while (!upperTriangle.contains(currentPoint));
			points.add(new Point2D.Double( currentPoint.getX(),currentPoint.getY()));
		}
	}
	
	private Point2D goLeft(Point2D pt, int distance)
	{
		return new Point2D.Double (pt.getX() - this.unit * distance ,pt.getY());
	}
	
	private Point2D goRight(Point2D pt, int distance)
	{
		return new Point2D.Double (pt.getX() + this.unit * distance,pt.getY());	}
	
	private Point2D goUp(Point2D pt, int distance)
	{
		return new Point2D.Double (pt.getX(),pt.getY() - this.unit * distance);
	}
	
	private Point2D goDown(Point2D pt, int distance)
	{
		return new Point2D.Double (pt.getX(),pt.getY() + this.unit * distance);	
	}
	


    public void draw(Graphics2D g)
    {
    	Color oldColor = g.getColor();
    	// vertical lines
        g.setColor(Color.green);

    	for (int i = 0; i < points.size()-1;i++)
    	{
    	 g.draw(new Line2D.Double(points.get(i),points.get(i +1)));
    	}
    	// horizontal lines

    	g.setColor(oldColor);
    }



    }
    

