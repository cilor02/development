package com.milo.misc;

import java.awt.Frame;

import com.milo.animation.Animatable;
import com.milo.animation.maths.NumberBoard;
import com.milo.animation.misc.MovingBox;
import com.milo.draw.Fabric;
import com.milo.draw.TrebleOrBassClef;

public class TestAnimation {

	public TestAnimation()
	{
		
	}
	public static void main(String[] args) throws Exception
	{
		Frame f = new Frame();	
		f.setSize(500,300);
	    Fabric fabric = new Fabric();
	    fabric.setSize(500, 300);
	 
//	    BassClef bassclef = new BassClef();
//	    fabric.add(bassclef);
	    
//	    MovingBox box = new MovingBox();
	    
	    NumberBoard box = new NumberBoard(12,0,0);
	    
	    fabric.add(box);
	    
	    fabric.getAnimationMgr().addAnimitable(box);
	    fabric.getAnimationMgr().startAnimation();
//	    f.addKeyListener(trebleclef);
		f.add(fabric);
		f.setVisible(true);
	    
	}
}
