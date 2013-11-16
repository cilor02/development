package com.milo.draw;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;



public class WordBlob implements Drawable{


protected String text;
protected double textHeight;
protected double textWidth;
protected double blobHeight;
protected double enclosingRectHeight;
protected double enclosingRectWidth;
private FontMetrics fMetrix;
private Font font;
private double doubleX;
private double doubleY;
private double x;
private double y;
public WordBlob(String word,double x,double y){
	text=word;
    this.x=x;
    this.y=y;	
    font= new Font(null,Font.BOLD,56);
}



public double getTextHeight() {
	return textHeight;
}

public void setTextHeight(double textHeight) {
	this.textHeight = textHeight;
}




public void draw(Graphics2D g) {
	// TODO Auto-generated method stub
	g.setFont(font);
	fMetrix=g.getFontMetrics();
	textHeight=fMetrix.getAscent() + fMetrix.getDescent();
	int points = FontSizer.findPointSize(g,textHeight);
	font=new Font(null,Font.BOLD,points);
	g.setFont(font);
	

	
	
	enclosingRectHeight=textHeight*Math.sqrt(2.0);	
	textWidth=fMetrix.stringWidth(text);
	enclosingRectWidth = enclosingRectHeight/textHeight*textWidth;
	double baselineX =(float) (x - textWidth/2.0);
	double baselineY = (float)(y + textHeight/2.0);
	Graphics2D g2D = (Graphics2D)g;
	
	g2D.drawString(text,(float)baselineX,(float)baselineY);

	double encloseY=(baselineY+fMetrix.getDescent()-textHeight-(this.enclosingRectHeight - textHeight)/2);
	double encloseX=(int)(baselineX-(this.enclosingRectWidth - textWidth)/2);
	Ellipse2D oval = new Ellipse2D.Double(encloseX,encloseY,this.enclosingRectWidth,this.enclosingRectHeight);
	g.draw(oval);
	}


}
