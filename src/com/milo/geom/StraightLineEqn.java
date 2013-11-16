package com.milo.geom;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class StraightLineEqn {

	private Double gradient;
	private Double yIntercept;
	
	public StraightLineEqn parallelLine (Double perpDistance)
	{
		Double perpGrad = -1 / gradient;
		Double dx = Math.sqrt((perpDistance * perpDistance)/(1+perpGrad*perpGrad));
		Double dy = dx * perpDistance;
		return null;
	}
	
	static public Line2D parallelLine (Line2D line1, Double distance)
	{
		Double grad1 = (line1.getY1()-line1.getY2())/(line1.getX1()-line1.getX2());
		Double perpGrad = -1 / grad1;
		Double dx = distance * Math.sqrt(1/(1+perpGrad*perpGrad));
		Double dy = dx * perpGrad; 
		return new Line2D.Double(line1.getX1()+dx, line1.getY1()+dy, line1.getX2()+dx, line1.getY2()+dy );
	}
	
	static public Point2D moveFromPointTowardPoint(Point2D p1, Point2D p2, Double distance)
	{
		Double slope = (p1.getY() - p2.getY())/(p1.getX()-p2.getX());
		
		double directionX = (p1.getX()-p2.getX()) > 0 ? -1 : 1;
		double directionY = (p1.getY()-p2.getY()) > 0 ? -1 : 1;
		Double dx = (p1.getX()== p2.getX())?0:Math.sqrt((distance * distance)/(1+slope*slope)) * directionX;
		Double dy = (p1.getX()== p2.getX())?distance:dx * slope;
		Point2D newPt = new Point2D.Double(p1.getX()+dx,p1.getY()+dy);
		return newPt;
	}
	
	public StraightLineEqn(Line2D line)
	{
      this(line.getP1(), line.getP2());
	}
	public StraightLineEqn(Point2D p1, Point2D p2)
	{
		gradient = (p1.getY() - p2.getY())/(p1.getX() - p2.getX());
		yIntercept = p1.getY()-(gradient * p1.getX());
	}
	
	public StraightLineEqn(Point2D p1, Double slope)
	{
		gradient = slope;
		yIntercept = p1.getY()-(gradient * p1.getX());
	}
	
	public Double calculateY( Double xCoordinate )
	{
		return gradient * xCoordinate + yIntercept; 
	}

	public Double calculateX( Double yCoordinate )
	{
		return (yCoordinate - yIntercept)/gradient; 
	}
	
	public Point2D interception(StraightLineEqn line2)
	{
		if(gradient == line2.getGradient())
		{
			return null;
		}
		Double interceptionX = (yIntercept - line2.getYIntercept())/(line2.getGradient() - gradient); 
		Double interceptionY = gradient * interceptionX + yIntercept;
		return new Point2D.Double(interceptionX, interceptionY);
	}

	public Double getGradient() {
		return gradient;
	}

	public void setGradient(Double gradient) {
		this.gradient = gradient;
	}

	public Double getYIntercept() {
		return yIntercept;
	}

	public void setYIntercept(Double intercept) {
		yIntercept = intercept;
	}
}
