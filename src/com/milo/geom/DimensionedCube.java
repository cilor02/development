package com.milo.geom;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class DimensionedCube extends Cube{


	DimensionedCube(Double l, Double w, Double h, Point2D pt )
	{
		super(l,w,h,pt);

	}
	
    public void draw(Graphics2D g)
    {  	super.draw(g);
    	//g.draw(  );
    	new DimensionedArrowHeadedline( StraightLineEqn.parallelLine(new Line2D.Double(point,pL), -5.),super.length.intValue()).draw(g);
    	new DimensionedArrowHeadedline( StraightLineEqn.parallelLine(new Line2D.Double(point,pW), 5.),super.width.intValue()).draw(g);
    	new DimensionedArrowHeadedline( StraightLineEqn.parallelLine(new Line2D.Double(pW,pWH), 5.),super.height.intValue()).draw(g);

    }


    }
    

