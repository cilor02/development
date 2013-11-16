package com.milo.geom;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Text {

	private String text;
	private Font font;
	private Point2D centre=new Point2D.Double(0,0);
	
	public Text (Font font , String text )
	{
		this.font=font;
		this.text = text;
		
	}
	
	public Text ( String text )
	{
		this.text = text;
	}
	
	public Point2D getCentre() {
		return centre;
	}


	public void setCentre(Point2D centre) {
		this.centre = centre;
	}


	public void draw(Graphics g)
	{
		Graphics2D g2D = (Graphics2D)g;
		Point2D topLeft = null;
		
		FontMetrics fMetrics = g.getFontMetrics(font);
//		FontMetrics fMetrics = g.getFontMetrics();
        Rectangle2D rect2D =fMetrics.getStringBounds(text, g2D);
        g2D.draw ( new Rectangle2D.Double( centre.getX() - rect2D.getWidth()/2,centre.getY() - rect2D.getHeight()/2,rect2D.getWidth(),rect2D.getHeight()));
	g2D.drawString(text, (float)(centre.getX() - rect2D.getWidth()/2),  (float)(rect2D.getHeight()/2 - centre.getY()));
	}
	
}
