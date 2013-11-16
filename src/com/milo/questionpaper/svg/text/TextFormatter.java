package com.milo.questionpaper.svg.text;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.LineMetrics;
import java.awt.image.BufferedImage;
import java.util.ResourceBundle;

import org.dom4j.Element;


public abstract class TextFormatter {
	
	protected int fontSize;
	protected String familyName;
	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	protected int fractionFontSize;
	protected FontMetrics fractionFontMetrix;
	protected int lineHeight;
	public FontMetrics getFractionFontMetrix() {
		return fractionFontMetrix;
	}

	public void setFractionFontMetrix(FontMetrics fractionFontMetrix) {
		this.fractionFontMetrix = fractionFontMetrix;
	}

	protected Graphics2D g;
	protected Font f;
public Graphics2D getG() {
		return g;
	}

	public void setG(Graphics2D g) {
		this.g = g;
	}

public Font getF() {
		return f;
	}

	public void setF(Font f) {
		this.f = f;
	}

public TextFormatter()
{
	ResourceBundle bundle = ResourceBundle.getBundle("presentation");
	fontSize = Integer.valueOf(bundle.getString("font.size"));
	fractionFontSize=Integer.valueOf(bundle.getString("fraction.font.size"));
	familyName=bundle.getString("font.family");
	storeFontMetrics();
}

private void storeFontMetrics()
{
	BufferedImage bffrImg =  new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
	g=(Graphics2D)bffrImg.getGraphics();
	f =new Font(familyName,Font.PLAIN,fractionFontSize);
	
	g.setFont(f);
	FontMetrics fm = g.getFontMetrics();
	fractionFontMetrix=fm;
	lineHeight=fm.getHeight();
	
}

public double getLength(String str )
{
	return fractionFontMetrix.stringWidth(str);
}


public int getFontSize() {
	return fontSize;
}
public void setFontSize(int fontSize) {
	this.fontSize = fontSize;
}
public int getFractionFontSize() {
	return fractionFontSize;
}
public void setFractionFontSize(int fractionFontSize) {
	this.fractionFontSize = fractionFontSize;
}

abstract public Element convertToSVG(Element e);

}
