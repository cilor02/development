package com.milo.geom;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;


import com.milo.geom.AngularDisplacement;;

public class Cube{

	protected Double length;
	protected Double width;
	protected Double height;
	protected Point2D pL;
	protected Point2D pH;	
	protected Point2D pW;
	protected Point2D pLH;
	protected Point2D pWH;
	protected Point2D pWHLH;
	protected Point2D point;

	Cube(Double l, Double w, Double h, Point2D pt )
	{
		this.length = l;
		this.width  = w;
		this.height = h;
		point = pt;
		pL = RelativePositioner.getPoint(pt, new com.milo.geom.AngularDisplacement(Math.PI * 7 /6,l));
		pW = RelativePositioner.getPoint(pt, new com.milo.geom.AngularDisplacement(Math.PI * 11 /6,w));
		pH = RelativePositioner.getPoint(pt, new com.milo.geom.AngularDisplacement(Math.PI * 3 /2,h));
		pLH = RelativePositioner.getPoint(pL, new com.milo.geom.AngularDisplacement(Math.PI * 3 /2,h));
		pWH = RelativePositioner.getPoint(pW, new com.milo.geom.AngularDisplacement(Math.PI * 3 /2,h));
		pWHLH = RelativePositioner.getPoint(pWH, new com.milo.geom.AngularDisplacement(Math.PI * 7 /6,l));

	}
	
    public void draw(Graphics2D g)
    {
    	g.draw(new Line2D.Double(point,pL));
    	g.draw(new Line2D.Double(pL,pLH));
    	g.draw(new Line2D.Double(point,pW));
    	g.draw(new Line2D.Double(pW,pWH));
    	g.draw(new Line2D.Double(pWH,pWHLH));
    	g.draw(new Line2D.Double(pLH,pWHLH));
    	g.draw(new Line2D.Double(point,pH));
    	g.draw(new Line2D.Double(pWH,pH));
    	g.draw(new Line2D.Double(pLH,pH));
    	
    	
    }


    }
    

