package com.milo.animation.misc;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ResourceBundle;

import com.milo.animation.Animatable;
import com.milo.draw.Drawable;

public class MovingBox implements Drawable,Animatable{

	private double boxWidth;
	private double boxHeight;
	private Point2D mainPoint;
	private double restrictionWidth;
	private double restrictionHeight;
	private Point2D restrictionMainPoint;
	private int frequency=50;
	private double dispX;
	private double dispY;
	private double maxVertPos;
	private double minHorizpos;
	public MovingBox()
	{

		ResourceBundle bundle = ResourceBundle.getBundle("project");
		this.boxWidth=Double.valueOf(bundle.getString("moving.box.width"));
		this.boxHeight=Double.valueOf(bundle.getString("moving.box.height"));
		double mainPointX=Double.valueOf(bundle.getString("moving.box.main.pt.x"));
		double mainPointY=Double.valueOf(bundle.getString("moving.box.main.pt.y"));
		
		this.restrictionWidth=Double.valueOf(bundle.getString("moving.box.restrict.width"));
		this.restrictionHeight=Double.valueOf(bundle.getString("moving.box.restrict.height"));
		

		this.dispY=Double.valueOf(bundle.getString("moving.box.vert.displacement"));
		this.dispX=Double.valueOf(bundle.getString("moving.box.horiz.displacement"));
   
		mainPoint=new Point2D.Double(mainPointX,mainPointY);
		
        restrictionMainPoint = new Point2D.Double(mainPoint.getX() + restrictionWidth,mainPoint.getY() + restrictionHeight);
		minHorizpos=restrictionMainPoint.getX();
		maxVertPos=restrictionMainPoint.getY()+restrictionHeight;
	}
	public void animate() {
		// TODO Auto-generated method stub
		//update main point for animation
		mainPoint.setLocation(mainPoint.getX()+dispX, mainPoint.getY()+dispY);
		//check for collision with boundaries
		
		if(mainPoint.getY()<=0)
		{
			mainPoint.setLocation(mainPoint.getX(), 0);
			dispY*=-1.0;
		}
		if (mainPoint.getX()<=0)
		{
			mainPoint.setLocation(0, mainPoint.getY());
			dispX*=-1.0;
		}
		
		if(mainPoint.getY()  >= restrictionHeight - boxHeight)
		{
			mainPoint.setLocation(mainPoint.getX(), restrictionHeight - boxHeight);
			dispY*=-1.0;
		}
		if (mainPoint.getX()>=restrictionWidth - boxWidth)
		{
			mainPoint.setLocation(restrictionWidth - boxWidth, mainPoint.getY());
			dispX*=-1.0;
		}
	}

	public int getFrequency() {
		// TODO Auto-generated method stub
		return frequency;
	}

	public void draw(Graphics2D g2D) {
		// TODO Auto-generated method stub
		g2D.draw(new Rectangle2D.Double(mainPoint.getX(),mainPoint.getY(),boxWidth,boxHeight));
		
		}
	}


