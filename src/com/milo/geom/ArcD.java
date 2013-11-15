package com.milo.geom;

import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;

public class ArcD {

	private Point2D pivot;
	private Point2D startPoint;
	private Point2D endPoint;
	
	private double radius;
	private double startAngle;
	private double endAngle;
	
	private double startAngleRads;
	private double endAngleRads;
	private Arc2D.Double arc;
	
	
	public ArcD(Point2D pivot, double radius, double startAngle, double endAngle) {
		this.pivot = pivot;
		this.radius = radius;
		this.startAngle = startAngle;
		this.endAngle = endAngle;
		
		startAngleRads = Math.PI * 2 * startAngle /360.0; 
		endAngleRads = Math.PI * 2 * endAngle /360.0;
		startPoint = RelativePositioner.getPoint(pivot, new AngularDisplacement(startAngle,radius));
		endPoint = RelativePositioner.getPoint(pivot, new AngularDisplacement(endAngle,radius));
		
		
	}
	
	public static ArcD  createArcD(Point2D pivot, Point2D startPoint,Point2D endPoint) {
 
		StraightLineEqn sLine1 = new StraightLineEqn(pivot, startPoint);
		StraightLineEqn sLine2 = new StraightLineEqn(pivot, endPoint);
		
		Double grad1 = sLine1.getGradient();
		Double grad2 = sLine2.getGradient();

		Double radiusArc = pivot.distance(endPoint);
		Double angle1  = RelativePositioner.getAngularDisplacement(pivot, startPoint).getAngle() * 180. /Math.PI;
		Double angle2  = RelativePositioner.getAngularDisplacement(pivot, endPoint).getAngle() * 180. /Math.PI;
		printAngles(angle1, angle2);
		
		double startAngle1 = angle1 ==0? angle1: 360 - angle1;
		double endAngle2 = angle2 ==0? angle2: 360 - angle2;
		printAngles(startAngle1, endAngle2);
		return new ArcD(pivot,radiusArc,startAngle1,endAngle2);
	}

	private static void printAngles(Double angle1, Double angle2) {
		System.out.print(angle1 +" ");
		System.out.print(angle2);
		System.out.println();
	}
	
	public void draw( Graphics2D g)
   {
		arc = new Arc2D.Double(pivot.getX()-radius, pivot.getY()-radius,2*radius,2*radius,startAngle,endAngle-startAngle,Arc2D.PIE);
		g.draw(arc);
   }
	
	
}
