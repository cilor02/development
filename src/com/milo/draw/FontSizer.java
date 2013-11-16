package com.milo.draw;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class FontSizer{


static public int findPointSize(Graphics g,double textHeight){
    String fontName=g.getFont().getFontName();
    int fontStyle=g.getFont().getStyle();
	int point=0;
	Font font=null;
	FontMetrics fMetrics=null;
    do{
		font=new Font(fontName,fontStyle,point);
		fMetrics = g.getFontMetrics(font);
		point++;
	}while(fMetrics.getAscent()+fMetrics.getDescent()<textHeight);
	return point-1;
}

}
