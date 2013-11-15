package com.milo.misc;

import java.awt.Frame;

import com.milo.draw.Fabric;
import com.milo.draw.TrebleOrBassClef;

public class TestClef {

	public TestClef()
	{
		
	}
	public static void main(String[] args) throws Exception
	{
		Frame f = new Frame();	
		f.setSize(8*72,12*72);
	    Fabric fabric = new Fabric();
	    fabric.setSize(8*72, 12*72);
	 
//	    BassClef bassclef = new BassClef();
//	    fabric.add(bassclef);
	    
	    TrebleOrBassClef trebleclef = new TrebleOrBassClef();
	    fabric.add(trebleclef);
	    
	    fabric.getAnimationMgr().addAnimitable(trebleclef);
	    fabric.getAnimationMgr().startAnimation();
	    f.addKeyListener(trebleclef);
		f.add(fabric);
		f.setVisible(true);
	    
	}
}
