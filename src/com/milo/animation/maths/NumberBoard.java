package com.milo.animation.maths;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.milo.animation.Animatable;
import com.milo.animation.maths.Card;
import com.milo.draw.Drawable;

public class NumberBoard implements Drawable,Animatable{
	
	private String fontNumberType;
	private int pointSize;
	private int frequency=50; 
	private int interNumberSpace;
	private int numberBreakStartX;
	private int numberBreakStartY;
	private double numberXStart;
	private double numberYStart;
	private double numberMovementDisp;
	private Font font;
	private Graphics2D graphics;
	
public double getNumberXStart() {
		return numberXStart;
	}
	public void setNumberXStart(double numberXStart) {
		this.numberXStart = numberXStart;
	}
	public double getNumberYStart() {
		return numberYStart;
	}
	public List<Card> getComponents() {
		return components;
	}
	public void setNumberYStart(double numberYStart) {
		this.numberYStart = numberYStart;
	}
public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
	if(graphics==null)
	{
		graphics=g;
	}
	double fontHeight=g.getFontMetrics(font).getStringBounds("2",g).getHeight();
	Card card=null;	
	for(int i=components.size();i>0;i--){
			card=components.get(i-1);
			card.draw(g);
			System.out.println("x-coordinates draw "+card.getX());

//			if(card.getX()==numberXStart && card.getY()==numberYStart)
//			{
				// calculate destinations of cards
				double destY=fontHeight* card.getStringValue().length();
				card.setDestination(new Point2D.Double(numberBreakStartX+numberXStart,numberBreakStartY-destY));
//			}

			
			
		}
	}
	public void animate() {
		// TODO Auto-generated method stub
		for(Card card:components)
		{
			card.animate();
			System.out.println("x-coordinates animate "+card.getX());
//			card.draw(graphics);
		}
//		draw(graphics);
	}
	public int getFrequency() {
		// TODO Auto-generated method stub
		return frequency;
	}
private int value;
private List<Card> components;
public NumberBoard(int value,double startX, double startY)
{
	numberXStart=startX;
	numberYStart=startY;
	ResourceBundle props=ResourceBundle.getBundle("project");
	fontNumberType = props.getString("number.font.type");
	pointSize=Integer.valueOf(props.getString("number.font.size"));
	interNumberSpace=Integer.valueOf(props.getString("inter.number.space"));
	numberBreakStartX=Integer.valueOf(props.getString("number.break.start.x"));
	numberBreakStartY=Integer.valueOf(props.getString("number.break.start.y"));	
	numberMovementDisp=Double.valueOf(props.getString("number.movement.disp"));

	
	this.value = value;
	components=new ArrayList<Card>();
	int upby10=1;
	int divisor=1;
	while(upby10 > 0){
	upby10=value/divisor * divisor;
	divisor*=10;
	
	
	Card card=null;
	font=new Font(fontNumberType,Font.BOLD,pointSize);
	if(upby10 >0)
	{
		card=new Card(upby10%divisor);
		card.setFont(font);
		card.setX(numberXStart);
		card.setY(numberYStart);
		card.setDisplacement(numberMovementDisp);
	components.add(card);
	}
	}

	
}

public NumberBoard(int value) {
	//get Properties
	ResourceBundle props=ResourceBundle.getBundle("project");
	fontNumberType = props.getString("number.font.type");
	pointSize=Integer.valueOf(props.getString("number.font.size"));
	interNumberSpace=Integer.valueOf(props.getString("inter.number.space"));
	numberBreakStartX=Integer.valueOf(props.getString("number.break.start.x"));
	numberBreakStartY=Integer.valueOf(props.getString("number.break.start.y"));	
	numberMovementDisp=Double.valueOf(props.getString("number.movement.disp"));

	
	this.value = value;
	this.value = value;
	components=new ArrayList<Card>();
	int upby10=1;
	int divisor=1;
	while(upby10 > 0){
	upby10=value/divisor * divisor;
	divisor*=10;
	
	
	Card card=null;
	font=new Font(fontNumberType,Font.BOLD,pointSize);
	if(upby10 >0)
	{
		card=new Card(upby10%divisor);
		card.setFont(font);
		card.setX(numberXStart);
		card.setY(numberYStart);
		card.setDisplacement(numberMovementDisp);
	components.add(card);
	}
	}

	
}


}

