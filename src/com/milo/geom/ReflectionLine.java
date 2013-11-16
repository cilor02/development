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

public class ReflectionLine{

	private int unit;
	private int height;
	private int width;
	public ReflectionLine( int unit, int height, int width) {
        this.height = height;
        this.width = width;
		this.unit= unit;
	}

	


    public void draw(Graphics2D g)
    {
    	Color oldColor = g.getColor();
        g.setColor(Color.green);
        g.drawLine( 0,height, width, 0);
    	g.setColor(oldColor);
    }



    }
    

