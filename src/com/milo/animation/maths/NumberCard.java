package com.milo.animation.maths;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import com.milo.animation.Animatable;
import com.milo.draw.Drawable;

public class NumberCard implements Animatable,Drawable {
private int value;
private List<Integer> components;
	public NumberCard(int value) {
	this.value = value;
	components=new ArrayList<Integer>();
	int upby10=1;
	int divisor=1;
	while(upby10 > 0){
	upby10=value/divisor * divisor;
	divisor*=10;
	if(upby10 >0)
	{
	components.add(upby10%divisor);
	}
	}

	
}

	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	public void animate() {
		// TODO Auto-generated method stub
		
	}

	public int getFrequency() {
		// TODO Auto-generated method stub
		return 0;
	}

}
