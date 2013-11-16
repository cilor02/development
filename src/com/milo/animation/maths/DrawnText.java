package com.milo.animation.maths;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class DrawnText {
 private String text;
 private Point2D centre;
 private Point2D bottomLeftPt;
 private Font font;
 public DrawnText (String text, Point2D centre, Font font)
 {
	 this.text=text;
	 this.centre = centre;
	 this.font = font;
	 FontMetrics fMetrix;
	 Graphics g;
		BufferedImage bffrImg =  new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
		g=(Graphics2D)bffrImg.getGraphics();
		//f =new Font(familyName,Font.PLAIN,fractionFontSize);
	 fMetrix = g.getFontMetrics(font);
	 Rectangle2D rect = fMetrix.getStringBounds(text,g);
	  Double bottomLeftPtX = centre.getX() - rect.getWidth()/2;
	  Double bottomLeftPtY = centre.getY() + (rect.getHeight())/2 ;
	  bottomLeftPt = new Point2D.Double(bottomLeftPtX,bottomLeftPtY);
	  
      
 }
 
 public void draw(Graphics g)
 {
	 Graphics2D g2D = (Graphics2D)g;
	 g2D.drawString(text, (float)bottomLeftPt.getX(), (float)bottomLeftPt.getY());
 }
 
}
