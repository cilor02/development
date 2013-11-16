package com.milo.geom;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DottedSquare{

	private Point2D topLeftCorner;
	private int interDotSpace;
	private int sideLength;
	
	public DottedSquare(Point2D topLeftCorner, int interDotSpace, int sideLength) {
		super();
		this.topLeftCorner = topLeftCorner;
		this.interDotSpace = interDotSpace;
		this.sideLength = sideLength;
	}

	
	private Point2D goLeft(Point2D pt)
	{
		return new Point2D.Double (pt.getX() - this.interDotSpace,pt.getY());
	}
	
	private Point2D goRight(Point2D pt)
	{
		return new Point2D.Double (pt.getX() + this.interDotSpace,pt.getY());	}
	
	private Point2D goUp(Point2D pt)
	{
		return new Point2D.Double (pt.getX(),pt.getY() - this.interDotSpace);
	}
	
	private Point2D goDown(Point2D pt)
	{
		return new Point2D.Double (pt.getX(),pt.getY() + this.interDotSpace);	
	}
	

    public void draw(Graphics2D g)
    {
    	Point2D nextPoint = this.topLeftCorner;
    	new Circle(nextPoint,10).draw(g);
     for(int i=0;i<this.sideLength;i++)
     {
    	 nextPoint = goRight(nextPoint); 
    	 new Circle(nextPoint,10).draw(g);
    	   	 
     }
     
     for(int i=0;i<this.sideLength;i++)
     {
    	 nextPoint = goDown(nextPoint);  
    	 new Circle(nextPoint,10).draw(g);   	  	 
     }
     

     for(int i=0;i<this.sideLength;i++)
     {
    	 nextPoint = goLeft(nextPoint); 
    	 new Circle(nextPoint,10).draw(g);
    	   	 
     }
     for(int i=0;i<this.sideLength;i++)
     {
    	 nextPoint = goUp(nextPoint); 
    	 new Circle(nextPoint,10).draw(g);
    	   	 
     }
    }


    public static void main(String[] args)
    {


     Pattern pattern =  Pattern.compile("[\\d]+");
     String s = "gut";
     String[] tokens = pattern.split(s);

     System.out.println("size" + " " +tokens.length);
     for (int i = 0; i < tokens.length; i++) {
         System.out.println(""+ i + " " +tokens[i]);
       }
    	
    	
    	
    }
    }
    

