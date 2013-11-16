package com.milo.geom;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Axis{

	private int unit;
	private int height;
	private int width;
	public Axis( int unit, int height, int width) {
        this.height = height;
        this.width = width;
		this.unit= unit;
	}

	


    public void draw(Graphics2D g)
    {
    	Color oldColor = g.getColor();
    	// vertical lines
        g.setColor(Color.green);

    	for (int i = 0; i < width;i++)
    	{
    		g.drawLine(width/2, 0, width/2, height);
    	}
    	// horizontal lines

    	for (int i = 0; i < height;i++)
    	{
    		g.drawLine(0,height/2, width,  height/2);
    	}
    	g.setColor(oldColor);
    }



    }
    

