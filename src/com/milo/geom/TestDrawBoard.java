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

public class TestDrawBoard {

	static public class GenCanvas extends Canvas {

		@Override
		public void paint (Graphics g) {
			
            Grid grid = new Grid (100,5,5);
            grid.draw((Graphics2D) g);
			
			
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
