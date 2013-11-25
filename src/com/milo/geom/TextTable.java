package com.milo.geom;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class TextTable{

	private int unit;
	private int height;
	private int width;
	
	private Map<String, List<String>> rows;
	private List <String> headings;
	
	public TextTable(Map<String, List<String>> rows, List<String> headings, int unit){
		this.unit = unit;
		this.rows = rows;
		this.headings = headings;
		height = this.rows.size();
		width = this.headings.size();
	}
	
	public TextTable( int unit, int height, int width) {
        this.height = height;
        this.width = width;
		this.unit= unit;
	}

	


    public void draw(Graphics2D g)
    {
    	Color oldColor = g.getColor();
    	// vertical lines
        g.setColor(Color.pink);
    	for (int i = 0; i <= width;i++)
    	{
    		g.drawLine(i *unit, 0, i*unit, height * unit);
    	}
    	// horizontal lines

    	for (int i = 0; i <= height;i++)
    	{
    		g.drawLine(0,i *unit, width * unit,  i*unit);
    	}
    	g.setColor(oldColor);
    }



    }
    

