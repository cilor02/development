package com.milo.draw;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestFontSizes implements Drawable{
	private Font font1;
	private Font font2;
	private Font font3;
	private ArrayList<Font> fonts = new ArrayList<Font>();
	
public TestFontSizes()
{

	for(int i=0;i<50;i++)
	{
	Map<TextAttribute, Object> attrMap = new HashMap<TextAttribute, Object>();
	attrMap.put(TextAttribute.SIZE,5.0f+i*.5f);
	attrMap.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
	attrMap.put(TextAttribute.FAMILY, "Monospaced");
	
	fonts.add( new Font(attrMap));
	
	}
	}
	public void draw(Graphics2D g) {
		
		// TODO Auto-generated method stub
		Font font = g.getFont();
		
		int startPoint=20;
		for (Font f:fonts)
		{
			startPoint+=10;
			g.setFont(f);
			g.drawString("E", startPoint, 20);
			g.drawString((f.getAttributes().get(TextAttribute.SIZE)).toString(),startPoint,120);
		}
		g.setFont(font);
	}

}
