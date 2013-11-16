package com.milo.draw;

import java.awt.Graphics2D;

import org.apache.batik.gvt.GraphicsNode;

import com.milo.animation.Animatable;

public class DrawableGraphicNode implements Drawable,Animatable{
public void animate() {
		// TODO Auto-generated method stub
		
	}
	public int getFrequency() {
		// TODO Auto-generated method stub
		return 0;
	}
private GraphicsNode gNode;
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		gNode.paint(g);
	}
	public DrawableGraphicNode(GraphicsNode node) {
		gNode = node;
	}

}
