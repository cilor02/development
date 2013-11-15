package com.milo.geom;

import java.awt.geom.Point2D;

public class RelativePositioner {

	

	static Point2D getPoint(Point2D point1, double angleDisp,double distance)
	{
		double dispY = Math.sin(angleDisp)* distance;
		double dispX = Math.cos(angleDisp)* distance;
		return new Point2D.Double(point1.getX()+dispX ,point1.getY()+dispY);
	}
	
	static Point2D getPoint(Point2D point1, AngularDisplacement angDisp)
	{
		double dispY = Math.sin(angDisp.getAngle())* angDisp.getDistance();
		double dispX = Math.cos(angDisp.getAngle())* angDisp.getDistance();
		return new Point2D.Double(point1.getX()+dispX ,point1.getY()+dispY);
	}
	
	static Point2D rotateClockwise(Point2D fulcrum, Point2D orbiter, Double angle)
	{

		AngularDisplacement angDisp =getAngularDisplacement(fulcrum, orbiter);
		angDisp.setAngle(angDisp.getAngle()+angle);
		Point2D newPos = getPoint(fulcrum,angDisp );
		
     return newPos;
	}
	
	static Point2D rotateAntiClockwise(Point2D fulcrum, Point2D orbiter, Double angle)
	{
     return rotateClockwise(fulcrum, orbiter, -angle);
     
	}
	
	static AngularDisplacement getAngularDisplacement(Point2D p1, Point2D p2)
	{
		double distance = p1.distance(p2);
		double dispY = p2.getY() - p1.getY();
		double dispX = p2.getX() - p1.getX();
		double theta = Math.asin (Math.abs(dispY/distance));
		if(dispY < 0)
		{
			if(dispX < 0)
			{
				theta += Math.PI;
			}else
			{
				theta = 2* Math.PI - theta ;
			}
		}else
		{
			if(dispX < 0)
			{
				theta = Math.PI - theta ;
			}
		}
		
		
		
		return  new AngularDisplacement(theta,distance);
	}
	public  static void main(String[] args){
		Point2D p1 = new Point2D.Double(100,-100);
		double angleIncr = Math.PI /4;
		for (int i=0;i<6;i++){
			Point2D p2 = getPoint(p1, angleIncr * i,50);
		}
	}
}
