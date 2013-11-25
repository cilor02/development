package com.milo.geom;

import java.awt.Canvas;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/*
import org.niffty.Clef;
import org.niffty.Clef.OctaveNumber;
import org.niffty.Clef.Shape;
*/
import com.milo.geom.StraightLineEqn;;

public class Angles {

	static public class GenCanvas extends Canvas {

		@Override
		public void paint(Graphics g) {
			
            int points[] =
            {-2,8, 0,8, 0,9, -3,9, -3,10, -1,10, -3,10, -3,11, -2,12, 1,12, 3,10,
                3,5, -1,-12, -1,-19, 0,-18, 0,-21, 1,-21, 1,-23, 2,-23, 2,-21, 3,-21,
                3,-16, 2,-15, 2,-12, 1,-15, 1,-11, 0,-11, 0,-13, -1,-12, -1,-10,
                -2,-11, -2,-9, -3,-10, -3,-8, -4,-9, -4,-7, -5,-7, -5,-5, -6,-5,
                -6,0, -5,0, -5,2, -4,2, -4,3, -3,3, -2,4, 2,4, 5,1, 5,-3, 4,-2, 4,-4,
            -2,-4, 3,-3, -3,-3, -2,-2, -3,-2, -3,0, -1,2, -3,0};

			
			// TODO Auto-generated method stub
			double dispX = 0;
			double dispY = 0;	
			double parts=12;
	    	Point2D pt1= new Point2D.Double(120,50);
	    	Point2D pt2= new Point2D.Double(200,200);
	    	Point2D pt3= new Point2D.Double(120,400);
	    	//Triangle t = new Triangle(pt1,pt2,pt3);
	    	
	    	Cube c1 = new DimensionedCube(20.0,30.0,60.0, new Point2D.Double(50,250));
	    	Cube c2 = new DimensionedCube(100.0,50.0,30.0,new Point2D.Double(550,550));
	    	Cube c3 = new DimensionedCube(80.0,40.0,60.0, new Point2D.Double(680,450));
	    	Cube c4 = new DimensionedCube(30.0,20.0,60.0, new Point2D.Double(50,550));
	    	Cube c5 = new DimensionedCube(30.0,20.0,60.0, new Point2D.Double(115,250));
	    	Cube c6 = new DimensionedCube(30.0,20.0,60.0, new Point2D.Double(100,150));	    	
	    	
	    	
	    	//Triangle t = new Triangle(pt2,Math.toRadians(60),Math.toRadians(60),400.0);
	        
//			Text txt = new Text(new Font("monospaced",Font.ITALIC,12),"30");
//			txt.setCentre(pt3);
			
			
			Line2D line1 = new Line2D.Double(180,600,220,300);
			Line2D line2 = new Line2D.Double(280,500,120,250);
			Line2D parallelLine1 = StraightLineEqn.parallelLine(line1, 5.);
			Line2D parallelLine2 = StraightLineEqn.parallelLine(line2, 5.);
			StraightLineEqn lineEqn1 = new StraightLineEqn(line1);
			StraightLineEqn lineEqn2 = new StraightLineEqn(line2);
			Point2D pInterception = lineEqn1.interception(lineEqn2);
			
			Line2D line3 = new Line2D.Double(115,250,115,310);
			
//			DimensionedArrowHeadedline dArrow = new DimensionedArrowHeadedline(line3,12);
//			ArrowHeadedline arrow = new ArrowHeadedline(line3);
			
//			for(int i=0;i<parts-7;i++)
//			{
//				dispX = 200 * Math.cos(2*i*Math.PI/parts);
//				dispY = 200 * Math.sin(2*i*Math.PI/parts);	
//				g.drawLine(200, 300, (int)(200+dispX), (int)(300 + dispY));
//				g.drawLine(200, 300, 400, 300);
//			}
			Point2D firstPoint = new Point2D.Double(300,300);	
			for(int j=6;j<12;j++)
			{
				Polygon p3 = new Polygon(j,200,firstPoint);
				p3.draw((Graphics2D)g);
			}

//			Circle circle = new Circle(firstPoint,100);
//			circle.draw((Graphics2D)g);
//			
//			ArcD arc = new ArcD(firstPoint,50,350,70);
//			arc.draw((Graphics2D)g);
//			
			
			
//		Point2D p1 = new Point2D.Double(50,100);
//		Point2D p2 = drawLine(g, p1,Math.PI/6.0,300);
//		Point2D p3 = drawLine(g, p2,Math.PI/2,100);
//		Point2D p4 = drawLine(g, p3,7.0/6.0*Math.PI,300);
//		Point2D p5 = drawLine(g, p4,3.0/2.0*Math.PI,100);
//		Point2D p6 = drawLine(g, p5,11.0/6.0*Math.PI,50);
//		Point2D p7 = drawLine(g, p6,Math.PI/6.0,300);
//		Point2D p8 = drawLine(g, p7,Math.PI/2.0,100);		
//		Point2D p9 = drawLine(g, p8,5*Math.PI/6.0,50);
//		drawLine(g, p7,5*Math.PI/6.0,50);
		
//			Point2D point1 = new Point2D.Double(100,600);
//			int n = 10;
//			double angDisp = 2*Math.PI/n;
//			Font font = new Font("monospaced",Font.ITALIC,8);
//			 for(int i = 0; i < 10; i++)
//			 {
//				 Point2D point2 = RelativePositioner.getPoint(point1, angDisp*i,70); 
//				 ((Graphics2D)g).draw(new Line2D.Double(point1, point2));
//				 AngularDisplacement angDisplacement = RelativePositioner.getAngularDisplacement(point1, point2);
//				 double rads = angDisplacement.getAngle();
//				 double degrees = 180.0 * rads /Math.PI; 
//				DrawnText txtP1 = new DrawnText(String.valueOf(degrees),point2,font);
//			     txtP1.draw(g);
//			 }
//			
			
			
			
//			t.draw((Graphics2D)g);
//			//  create arc for angle
//			Point2D tP1 = t.getP1();
//			Point2D tP2 = t.getP2();
//			Point2D tP3 = t.getP3();
			


			
			//p1 p2
//			Point2D  arcStart = StraightLineEqn.moveFromPointTowardPoint(tP1, tP2, 50.);
			

			//p1 p3
//			Point2D  endStart = StraightLineEqn.moveFromPointTowardPoint(tP1, tP3, 50.);

//			ArcD arcP1 = ArcD.createArcD(tP1, arcStart, endStart);
						
			//p2 p3
//			Point2D  arcStart2 = StraightLineEqn.moveFromPointTowardPoint(tP2, tP3, 50.);
			
			//p2 p1
//			Point2D  endStart2 = StraightLineEqn.moveFromPointTowardPoint(tP2, tP1, 50.);
//			ArcD arcP2 = ArcD.createArcD(tP2, arcStart2, endStart2);
		
			//p3 p2
//			Point2D  arcStart3 = StraightLineEqn.moveFromPointTowardPoint(tP3, tP2, 50.);
			
			//p3 p1
//			Point2D  endStart3 = StraightLineEqn.moveFromPointTowardPoint(tP3, tP1, 50.);
//			ArcD arcP3 = ArcD.createArcD(tP3, arcStart3, endStart3);
			
//			arcP1.draw((Graphics2D)g);
//			arcP2.draw((Graphics2D)g);
//			arcP3.draw((Graphics2D)g);
	        int points1[] = 
	            {-3,1, -5,1, -5,0, -3,0, -3,-1, -6,-1, -6,-2, -4,-4, 0,-4, 0,-3, 2,-3,
	                1,-2, 3,-2, 3,2, 2,-2, 2,4, 1,3, 1,5, 0,6, -1,6, -1,7, -2,7, -4,9,
	            -5,9, -4,9};

	        int points2[] = {6,-3, 7,-3, 7,-2, 6,-2, 6,-3};
	        int points3[] = {6,2, 7,2, 7,3, 6,3, 6,2};

		
			drawPoints(g, points, 500, 200);
            
			drawPoints(g, points1, 500, 300);
			drawPoints(g, points2, 500, 300);
			drawPoints(g, points3, 500, 300);
	        
	        
	        
//			txt.draw(g);
			Graphics2D g2D = (Graphics2D)g;
			
//			arrow.draw(g2D);
//			dArrow.draw(g2D);
			
//			g2D.draw(line2);
//			g2D.draw(line1);
//			g2D.draw(parallelLine1);
//			g2D.draw(parallelLine2);
//			Circle circle = new Circle(pInterception,10);
//			circle.draw(g2D);
			c1.draw(g2D);
			c2.draw(g2D);
			c3.draw(g2D);
			c4.draw(g2D);
			c5.draw(g2D);
			c6.draw(g2D);			
//			DottedSquare dottedSquare = new DottedSquare(new Point2D.Double(50,50),30,3);
//			dottedSquare.draw(g2D);
			
			
		}

		private void drawPoints(Graphics g, int[] points, int x, int y) {
			int count = points.length / 2;
	        int [] _xPoints = new int[count];
	        int [] _yPoints = new int[count];
	        
	        for (int i = 0; i < count; ++i) {
	            _xPoints[i] = 2 * points[2*i] + x;
	            _yPoints[i] = 2 * points[2*i + 1] + y;
	        }
	        g.drawPolyline (_xPoints, _yPoints, _xPoints.length);
		}

		private Point2D drawLine(Graphics g, Point2D p2,double angle, double distance) {
			Point2D p3 = RelativePositioner.getPoint(p2, angle, distance);
			Line2D l3 = new Line2D.Double(p2,p3);
			((Graphics2D)g).draw(l3);
			return p3;
		}
   
	}

	public static void main(String [] args)
	{
		Frame f = new Frame("test");
		f.addWindowListener(new WindowAdapter() {

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}

		});
		f.add(new GenCanvas());
		f.setSize(800, 800);
		f.setVisible(true);
	}
}
