package com.milo.draw;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.Random;
import java.util.ResourceBundle;

import com.milo.animation.Animatable;

public class Clef implements Drawable,Animatable {
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		for(int i = 0;i<5;i++)
		{
            g.drawString("\uF026",20.0f, 60.0f);
			double vert=firstLineY-i*interLineSpacing;
			g.draw(new Line2D.Double(firstLineX,vert,firstLineX+lineLength,vert));
		}
		note.draw(g);
	}
	public void animate() {
		// TODO Auto-generated method stub
		
		note.setX(note.getX()+noteHorizDisp);
		if(note.getX()-firstLineX>lineLength)
		{
			note.setX(firstLineX);
			//end of line try new value
            noteDisp=(int)(random.nextDouble()*9);
		}
		note.setY(firstLineY-interLineSpacing/2*noteDisp);
		
	}
	public int getFrequency() {
		// TODO Auto-generated method stub
		return frequency;
	}
	private int frequency=50;
	private double interLineSpacing;
	private Circle note;
	private double lineLength;
	private double firstLineX;
	private double firstLineY;
	private double noteHorizDisp;
	private Random random;
	private int noteDisp;
	
public Clef()
{
	random= new Random();
	ResourceBundle bundle = ResourceBundle.getBundle("project");
	interLineSpacing=Double.parseDouble(bundle.getString("treble.clef.interline.dist"));
	this.firstLineX=Double.parseDouble(bundle.getString("first.line.x"));
	this.firstLineY=Double.parseDouble(bundle.getString("first.line.y"));	
	double radius=Double.parseDouble(bundle.getString("musical.note.radius"));
	this.noteHorizDisp=Double.parseDouble(bundle.getString("note.horiz.disp"));
	this.lineLength=Double.parseDouble(bundle.getString("treble.clef.line.length"));
	note=new Circle(firstLineX,firstLineY,radius);

}
}
