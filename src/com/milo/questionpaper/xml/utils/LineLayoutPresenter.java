package com.milo.questionpaper.xml.utils;

import java.util.List;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.w3c.dom.Node;

import com.milo.questionpaper.xml.SVGLayoutDAO;

public class LineLayoutPresenter {
	private int lineGap;
	private int x;
	private int y;
	private Element textContainer;
	private int lineHeight;
public LineLayoutPresenter(Element e)
{
	e.remove(e.getNamespace());
    this.textContainer=e;
	String distanceBetweenLines= SVGLayoutDAO.getProperty("inter.line.distance");
	lineGap=Integer.parseInt(distanceBetweenLines);
	lineHeight=new TextBoundingBox("H").getLineHeight();
}

public LineLayoutPresenter(Element e,int x, int y)
{
this(e);
	this.x=x;
	this.y=y;
}


public Element layoutTextLines()
{
	System.out.println(textContainer.asXML());
	List<Element> textLines = textContainer.elements("text");
	int loopCount=0;
for(Element textLine:textLines)
{
	//add line positional attributes
	textLine.addAttribute("y", String.valueOf(y+loopCount*(lineGap+lineHeight)));
	textLine.addAttribute("x", String.valueOf(x));

	loopCount++;
	
}
return textContainer;
}

public int getY() {
	return y;
}

public void setY(int y) {
	this.y = y;
}

public int getX() {
	return x;
}

public void setX(int x) {
	this.x = x;
}

public int getLineHeight() {
	return lineHeight;
}

public void setLineHeight(int lineHeight) {
	this.lineHeight = lineHeight;
}

}

