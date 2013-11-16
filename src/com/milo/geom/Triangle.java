package com.milo.geom;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class Triangle{

	public Point2D getP1() {
		return p1;
	}

	public Point2D getP2() {
		return p2;
	}

	public Point2D getP3() {
		return p3;
	}

	private ArrayList <Point2D> tips;
	protected ArrayList <Double> angles;
	protected ArrayList <Double> midAngles= new ArrayList <Double>();
	
	private Point2D p1;
	private Point2D p2;
	private Point2D p3;
	
	private DrawnText drawnText1;
	private DrawnText drawnText2;	
	private DrawnText drawnText3;
	
	Triangle(ArrayList<Point2D>tips)
	{
		this.tips = tips;
		p1 = tips.get(0);
		p2 = tips.get(1);
		p3 = tips.get(2);
		angles=calculateAngles();
	} 
	
	public Triangle(Point2D pt ,Double angleDeg1, Double angleDeg2, String unknownAngle, Double biggestSide)
	{
		
		Double angle2 = Math.toRadians(angleDeg2);
		Double angle1 = Math.toRadians(angleDeg1);
		
		Double angle3 = Math.PI - angle2 - angle1;
		
		
		
		Double [] angles1 = new Double[] {angle1,angle2,angle3};
		Arrays.sort(angles1);
        Point2D pt2 = new Point2D.Double(pt.getX()+biggestSide,pt.getY());
        Double vertSplitFromApex = biggestSide/(1 + (Math.tan(angles1[0])/Math.tan(angles1[1]))); 
        Double secondSideLength = vertSplitFromApex/Math.cos(angles1[0]);
        Point2D p3Temp = new Point2D.Double(pt.getX()+secondSideLength,pt.getY());
        Point2D pt3 = RelativePositioner.rotateAntiClockwise(pt,p3Temp, angles1[0]);
		
        // angles 
        Double angleDist = 30.0;
        Point2D p2AngleTemp = new Point2D.Double(pt.getX()+angleDist,pt.getY());
        Point2D p2Angle = RelativePositioner.rotateAntiClockwise(pt,p2AngleTemp, angles1[0]/2);

        Point2D p3AngleTemp = new Point2D.Double(pt2.getX()- angleDist,pt2.getY());
        Point2D p3Angle = RelativePositioner.rotateClockwise(pt2,p3AngleTemp, angles1[1]/2);

        Font font = new Font("Times New Roman",Font.PLAIN,6);
       
        drawnText1 = new DrawnText(String.valueOf(angleDeg1),p2Angle,font);
        drawnText2 = new DrawnText(String.valueOf(angleDeg2),p3Angle,font);

        this.p1 = pt;
		this.p2 = pt2;
		this.p3 = pt3;
		this.angles=calculateAngles();
		
	}
	
	public Triangle(Point2D pt ,Double angle1, Double angle2,Double biggestSide)
	{
		Double angle3 = Math.PI - angle2 - angle1;
		Double [] angles1 = new Double[] {angle1,angle2,angle3};
		Arrays.sort(angles1);
        Point2D pt2 = new Point2D.Double(pt.getX()+biggestSide,pt.getY());
        Double vertSplitFromApex = biggestSide/(1 + (Math.tan(angles1[0])/Math.tan(angles1[1]))); 
        Double secondSideLength = vertSplitFromApex/Math.cos(angles1[0]);
        Point2D p3Temp = new Point2D.Double(pt.getX()+secondSideLength,pt.getY());
        Point2D pt3 = RelativePositioner.rotateAntiClockwise(pt,p3Temp, angles1[0]);
		
        // angles 
        Double angleDist = 30.0;
        Point2D p2AngleTemp = new Point2D.Double(pt.getX()+angleDist,pt.getY());
        Point2D p2Angle = RelativePositioner.rotateAntiClockwise(pt,p2AngleTemp, angles1[0]/2);

        Point2D p3AngleTemp = new Point2D.Double(pt2.getX()- angleDist,pt2.getY());
        Point2D p3Angle = RelativePositioner.rotateClockwise(pt2,p3AngleTemp, angles1[1]/2);

        Font font = new Font("Times New Roman",Font.PLAIN,8);
        int intAngle1 = (int)Double.parseDouble( String.valueOf(Math.round(angles1[0]*180/Math.PI)));
        int intAngle2 = (int)Double.parseDouble( String.valueOf(Math.round(angles1[1]*180/Math.PI)));
       
        drawnText1 = new DrawnText(String.valueOf(intAngle1),p2Angle,font);
        drawnText2 = new DrawnText(String.valueOf(intAngle2),p3Angle,font);

        this.p1 = pt;
		this.p2 = pt2;
		this.p3 = pt3;
		this.angles=calculateAngles();
		
	}
	
	Triangle(Point2D p1,Point2D p2,Point2D p3)
	{
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		angles=calculateAngles();
		
	}

    public void draw(Graphics2D g)
    {
    	g.draw(new Line2D.Double(p1,p2));
    	g.draw(new Line2D.Double(p2,p3));
    	g.draw(new Line2D.Double(p3,p1));

    	drawnText1.draw(g);
    	drawnText2.draw(g);
    }

	private void drawMidAngles(Graphics2D g,Point2D pt, int index) {
		g.draw(new Line2D.Double(pt, RelativePositioner.getPoint(pt, new com.milo.geom.AngularDisplacement(midAngles.get(index),400))));
	}
   
    private Double midAngles(Double theta1, Double theta2)
    {
        System.out.println(" angle 1 start " + theta1 + " angle 1 end " + theta2  + " mid angle " + (theta1 +theta2) /2.0);
        System.out.println(" angle 1 start " + radToDeg(theta1) + " angle 1 end " + radToDeg(theta2)  + " mid angle " + radToDeg((theta1 +theta2) /2.0));
     
    	return (theta1 + theta2 )/2.0;
    }
    private ArrayList <Double> calculateAngles()
    {
    Double angle1start = RelativePositioner.getAngularDisplacement(p1, p2).getAngle();
    Double angle1end = RelativePositioner.getAngularDisplacement(p1, p3).getAngle();
        
    midAngles.add(midAngles(angle1start,angle1end));
    
    Double angle2start = RelativePositioner.getAngularDisplacement(p2, p3).getAngle();
    Double angle2end = RelativePositioner.getAngularDisplacement(p2, p1).getAngle();
    
    midAngles.add(midAngles(angle2start,angle2end));
    
    Double angle3start = RelativePositioner.getAngularDisplacement(p3, p1).getAngle();
    Double angle3end = RelativePositioner.getAngularDisplacement(p3, p2).getAngle();
    
    midAngles.add(midAngles(angle3start,angle3end));
    
    ArrayList<Double> returnedAngles = new ArrayList <Double>();
    returnedAngles.add( angle1end - angle1start);
    returnedAngles.add( angle2end - angle2start);
    returnedAngles.add( angle3end - angle3start);
    
    return returnedAngles;
    }
    
    private Double radToDeg(Double rad)
    {
    	return rad *180/Math.PI;
    }
    
    public static void main(String[] args)
    {
    	Point2D pt1= new Point2D.Double(120,50);
    	Point2D pt2= new Point2D.Double(400,50);
    	Point2D pt3= new Point2D.Double(120,400);
    	Triangle t = new Triangle(pt1,pt2,pt3);
    	
    	for(Double angle: t.angles)
    	{
    		System.out.println(angle*180/Math.PI);
    	}
    	
    	
    }
    }
    

