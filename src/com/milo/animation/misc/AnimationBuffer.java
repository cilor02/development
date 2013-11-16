package com.milo.animation.misc;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;

public class AnimationBuffer {
	private Image image;
	private Graphics2D graphics2D;
	private Component component;
	
	public AnimationBuffer(Component c )
	{
		component=c;
		image = c.createImage(c.getWidth(), c.getHeight());

		graphics2D = (Graphics2D)image.getGraphics();
		
	}

	public Image getImage() {
		image = component.createImage(component.getWidth(), component.getHeight());
		return image;
	}

	public Graphics2D getGraphics2D() {
		return (Graphics2D) image.getGraphics() ;
	}

}
