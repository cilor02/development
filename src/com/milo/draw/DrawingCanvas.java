package com.milo.draw;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.milo.animation.Animatable;
import com.milo.animation.AnimationManager;
import com.milo.animation.misc.AnimationBuffer;
import com.milo.animation.time.Pulse;
import com.milo.animation.time.PulseImpl;

public class DrawingCanvas extends Canvas implements DrawableContainer {

	
	private Graphics2D gBuffer; 
	private Graphics2D graphics2D;
	private Image imgOffScreen;
	private int frequency = 50; 

		

	public int getFrequency() {
		// TODO Auto-generated method stub
		return frequency;
	}

	private AnimationManager animationMgr;
	
	private Collection<Drawable> drawables;
	
	public DrawingCanvas()
	{
		drawables = new ArrayList<Drawable>();
		//create buffer for double buffering
		;
	}
	

	public void add(Drawable d) {
		// TODO Auto-generated method stub
		drawables.clear();
		drawables.add(d);
	}

	@Override
	public void update(Graphics g) {
      paint(g);
	}


	@Override
	public void paint(Graphics g) {

		this.imgOffScreen = this.createImage(this.getWidth(), this.getHeight());
		if(graphics2D==null)
		{
			graphics2D = (Graphics2D)g;
		}else
		{
			
			gBuffer=(Graphics2D)imgOffScreen.getGraphics();
			graphics2D = gBuffer;
		}
		RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.setRenderingHints(hints);
		
		graphics2D.clearRect(0, 0,this.getWidth(), this.getHeight());
		for(Drawable d:drawables)
		{
			d.draw(graphics2D);
		}

        g.drawImage(this.imgOffScreen, 0, 0, this);
	}

}
