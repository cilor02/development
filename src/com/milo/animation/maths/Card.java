package com.milo.animation.maths;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ResourceBundle;

import com.milo.animation.Animatable;
import com.milo.draw.Drawable;
//import com.milo.position.DirectionandDistance;

public class Card implements Drawable,Animatable{
private int value;
private String stringValue;
private int frequency=50;
private double digitCell;
private double x;
private double y;
private Point2D destination;
private double angleOfMovement;
private Font font;
private Rectangle2D boundBox;
private double displacement;
private double vertDisplacement;
private double horizDisplacement;

	public double getDisplacement() {
	return displacement;
}

public void setDisplacement(double displacement) {
	this.displacement = displacement;
}

	public Font getFont() {
	return font;
}

public void setFont(Font font) {
	this.font = font;
}

	public Point2D getDestination() {
	return destination;
}

public void setDestination(Point2D destination) {
	this.destination = destination;
	Point2D.Double point = new Point2D.Double(x,y);
//	DirectionandDistance dandD=new DirectionandDistance(point);
	angleOfMovement=Rotation.angleofRotationClockwiseFromNorth(point, destination);
	double distance = point.distance(destination);
	System.out.println("distance "+distance);

}

	public Card(int value) {
	this.value = value;
	stringValue=String.valueOf(value);
	ResourceBundle bundle = ResourceBundle.getBundle("project");
	
}

synchronized	public void animate() {
		// TODO Auto-generated method stub
	if(destination!=null)
	{
		System.out.println(" card animate "+destination);
		double distance = destination.distance(x, y);
		System.out.println("distance = "+distance +"x = "+x+" y ="+y+" destX "+destination.getX()+" destY "+destination.getY());
		
		if(distance> displacement){
		//DirectionandDistance animateDandD = new  DirectionandDistance(x,y);
		//Point2D newPosition = animateDandD.getEndPoint(angleOfMovement, displacement);
		//Point2D newPosition=animateDandD.getNewPoint(new Point2D.Double(x,y), displacement, angleOfMovement);
		Point2D newPosition = Rotation.rotateClockwiseFromNorth(new Point2D.Double(x,y), displacement, angleOfMovement);
		x=newPosition.getX();
		y=newPosition.getY();
		}
	}
	}

	public String getStringValue() {
		return stringValue;
	}

	public Rectangle2D getBoundBox() {
		return boundBox;
	}

	public void setBoundBox(Rectangle2D boundBox) {
		this.boundBox = boundBox;
	}

	public int getFrequency() {
		// TODO Auto-generated method stub
		return frequency;
	}

	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		//Font oldFont = g.getFont();
		g.setFont(font);
		int charWidth=g.getFontMetrics().charWidth('2');
		int charHeight=g.getFontMetrics().getMaxAscent();

////	g.drawRect(newX, y, charWidth, charHeight);
//		double newX = 0;
		Shape rect=null;
		 boundBox=g.getFontMetrics().getStringBounds(stringValue, g);
		Rectangle2D rectNew = new Rectangle2D.Double(x-boundBox.getWidth(),y-charHeight,boundBox.getWidth(),boundBox.getHeight());
		g.draw(rectNew);
		Color oldColor = g.getColor();
		g.setColor(Color.YELLOW);
		g.fill(rectNew);
		g.setColor(oldColor);
		g.drawString(stringValue, (float)(x-boundBox.getWidth()), (float)y);
//		for(int i=0;i<stringValue.length();i++)
//		{
//			newX=x+charWidth*i;
//			g.drawString(stringValue.substring(i,i+1), (float)newX, (float)y);
////			g.drawRect(newX, y, charWidth, charHeight);
//			//rect=new Rectangle2D.Double(newX,y-charHeight,charWidth,charHeight);
//			//g.draw(rect);
//			Rectangle2D boundBox=g.getFontMetrics().getStringBounds(stringValue.substring(i,i+1), g);
//			Rectangle2D rectNew = new Rectangle2D.Double(newX,y-charHeight,boundBox.getWidth(),boundBox.getHeight());
//			g.draw(rectNew);
//		}
		//g.setFont(oldFont);
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}



	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

}
