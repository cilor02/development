package com.milo.geom;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class DimensionedArrowHeadedline {
private Point2D arrowTail1Point1;
private Point2D arrowTail2Point1;
private Point2D arrowTail1Point2;
private Point2D arrowTail2Point2;
private Point2D point1;
private Point2D point2;
private double size;



    public double getSize() {
	return size;
}

public void setSize(int size) {
	this.size = size;
}

	public DimensionedArrowHeadedline(Line2D line,int size)
    {
	 this(line.getP1(),line.getP2(),line.getP1().distance(line.getP2()));
    }
    
	public DimensionedArrowHeadedline(Point2D p1,Point2D p2,double size)
	{
		double distance = 3;
	    this.size = size;
		Point2D pt = StraightLineEqn.moveFromPointTowardPoint(p1, p2, distance);
		arrowTail1Point1 = RelativePositioner.rotateClockwise(p1, pt, Math.PI/12);
		arrowTail2Point1 = RelativePositioner.rotateAntiClockwise(p1, pt, Math.PI/12);
		
		Point2D pt2 = StraightLineEqn.moveFromPointTowardPoint(p2, p1, distance);
		arrowTail1Point2 = RelativePositioner.rotateClockwise(p2, pt2, Math.PI/12);
		arrowTail2Point2 = RelativePositioner.rotateAntiClockwise(p2, pt2, Math.PI/12);
		
		point1 = p1;
		point2 = p2;		
	}
	
	public void draw(Graphics2D g)
	{
	  g.draw(new Line2D.Double(arrowTail1Point1, point1));
	  g.draw(new Line2D.Double(arrowTail2Point1, point1));
	  g.draw(new Line2D.Double(arrowTail1Point2, point2));
	  g.draw(new Line2D.Double(arrowTail2Point2, point2));
	  g.draw(new Line2D.Double(point1, point2));
	  Line2D line = StraightLineEqn.parallelLine(new Line2D.Double(point1, point2), 5.);
	  StraightLineEqn eqn = new StraightLineEqn(line);
	  double x = (line.getX1() + line.getX2())/2;
	  double y = (line.getY1() + line.getY2())/2;
	  DrawnText txt = new DrawnText(String.valueOf((int)this.size), new Point2D.Double(x,y), new Font("monospaced",Font.ITALIC,8));	  
	  txt.draw(g);
	}
	
	
}
