package com.milo.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class ColorCircle extends Circle {
public ColorCircle(double x,double y, double rad, Color color) {
		super(x,y, rad);
		this.color = color;
	}
private Color color;
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
	    Color oldColor =  g.getColor();
	    g.setColor(color);
		super.draw(g);
		g.setColor(oldColor);
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}

}
