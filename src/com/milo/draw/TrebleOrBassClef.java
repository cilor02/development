package com.milo.draw;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;

import com.milo.animation.Animatable;

public class TrebleOrBassClef implements Drawable,Animatable,KeyListener {
	public void keyPressed(KeyEvent e) {

	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		synchronized (this) {
			
		if(e.getKeyChar()==clefRange.charAt(noteDisp)){
			this.trueFalseNeutralFlag=1;
		}else
		{
			this.trueFalseNeutralFlag=-1;
		}
		}
	}
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
//		this.imageBassClef=Toolkit.getDefaultToolkit().createImage(this.bassClefImageLocation);
		for(int i = 0;i<5;i++)
		{
//            g.drawString("\uF026",20.0f, 60.0f);
			
			double vert=firstLineY-i*interLineSpacing;
			g.draw(new Line2D.Double(firstLineX,vert,firstLineX+lineLength,vert));
		}
		double topLine=0;
		
		if(this.imageClef==this.imageBassClef)
		{
			topLine=firstLineY-4*interLineSpacing;
		}
		if(this.imageClef==this.imageTrebleClef)
		{
			topLine=firstLineY-5*interLineSpacing;
		}

		g.drawImage(this.imageClef,(int)firstLineX,(int)topLine,this.imageClef.getWidth(null)/4,this.imageClef.getHeight(null)/4,null);
		note.draw(g);
		synchronized (this) {
			
		
		if(trueFalseNeutralFlag>0)
		{
			g.drawImage(this.tick,(int)(note.getX()-note.getRadius()),(int)(note.getY()-note.getRadius()),(int)note.getRadius()*2,(int)note.getRadius()*2,null);
	
		}
		if(trueFalseNeutralFlag<0)
		{
			g.drawImage(this.cross,(int)(note.getX()-note.getRadius()),(int)(note.getY()-note.getRadius()),(int)note.getRadius()*2,(int)note.getRadius()*2,null);
	
		}
		}
			
	}
	public void animate() {
		// TODO Auto-generated method stub
		
		note.setX(note.getX()+noteHorizDisp);
		if(note.getX()-firstLineX>lineLength)
		{
			note.setX(firstLineX);
			//end of line try new value
            noteDisp=(int)(random.nextDouble()*9);
            //choose clef
            int clefRndm =(int)(random.nextDouble()*2);
            this.imageClef = (clefRndm==1)?this.imageBassClef:this.imageTrebleClef;
            this.clefRange = (clefRndm==1)?this.bassRange:this.trebleRange;
            //reset cross tick
            trueFalseNeutralFlag=0;
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
	private Image imageBassClef;
	private String bassClefImageLocation;
	private Image imageTrebleClef;
	private String trebleClefImageLocation;
	private Image imageClef;
	private String tickImageLocation;
	private Image tick;
	private String crossImageLocation;
	private Image cross;	
	private String trebleRange="efgabcdef";	
	private String bassRange="gabcdefga";
	private String clefRange;
	private int trueFalseNeutralFlag=0;
public TrebleOrBassClef()
{
	random= new Random();
	ResourceBundle bundle = ResourceBundle.getBundle("project");
	interLineSpacing=Double.parseDouble(bundle.getString("treble.clef.interline.dist"));
	this.firstLineX=Double.parseDouble(bundle.getString("first.line.x"));
	this.firstLineY=Double.parseDouble(bundle.getString("first.line.y"));	
	double radius=Double.parseDouble(bundle.getString("musical.note.radius"));
	this.noteHorizDisp=Double.parseDouble(bundle.getString("note.horiz.disp"));
	this.lineLength=Double.parseDouble(bundle.getString("treble.clef.line.length"));
	this.bassClefImageLocation = bundle.getString("bass.clef.image.location");
	this.trebleClefImageLocation = bundle.getString("treble.clef.image.location");
	this.tickImageLocation = bundle.getString("tick.image.location");
	this.crossImageLocation = bundle.getString("cross.image.location");
	
	note=new Circle(firstLineX,firstLineY,radius);
//	this.imageBassClef=Toolkit.getDefaultToolkit().createImage(this.bassClefImageLocation);
    this.imageBassClef=new ImageIcon(this.bassClefImageLocation).getImage();
    this.imageTrebleClef=new ImageIcon(this.trebleClefImageLocation).getImage();
    this.tick=new ImageIcon(this.tickImageLocation).getImage();
    this.cross=new ImageIcon(this.crossImageLocation).getImage();
    //start on Treble Clef
    this.imageClef=this.imageTrebleClef;
    this.clefRange=this.trebleRange;
}
}
