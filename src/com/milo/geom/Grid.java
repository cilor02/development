package com.milo.geom;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Grid{

	private int unit;
	private int height;
	private int width;
	public Grid( int unit, int height, int width) {
        this.height = height;
        this.width = width;
		this.unit= unit;
	}

	


    public void draw(Graphics2D g)
    {
    	Color oldColor = g.getColor();
    	// vertical lines
        g.setColor(Color.pink);
    	for (int i = 0; i < width;i++)
    	{
    		g.drawLine(i *unit, 0, i*unit, height);
    	}
    	// horizontal lines

    	for (int i = 0; i < height;i++)
    	{
    		g.drawLine(0,i *unit, width,  i*unit);
    	}
    	g.setColor(oldColor);
    }



    }
    

