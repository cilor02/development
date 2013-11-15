package com.milo.questionpaper.xml.utils;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.milo.questionpaper.xml.SVGLayoutDAO;

public class TableRowBoundingBox implements BoundingBox{

	private String text;
    private float fontSize;
    private String fontFamily;
    private int lineHeight;
    //width will be set to maximum to force new line
    private int lineWidth;
    private Element rowElement;
    private List<Attribute> rowTextAttributes;
	public Rectangle2D getBoundary() {
		// TODO Auto-generated method stub 	
		return new Rectangle(lineWidth, lineHeight);
	}

	public TableRowBoundingBox(Element rowofTspans) {
		rowElement= rowofTspans;
	    fontSize      = Float.parseFloat(SVGLayoutDAO.getProperty("font.size"));
	    fontFamily    = SVGLayoutDAO.getProperty("font.family");
        BufferedImage im = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D)im.getGraphics();
        lineHeight = g.getFontMetrics().getHeight();
        lineWidth  = Integer.parseInt(SVGLayoutDAO.getProperty("question.width")); 
        rowTextAttributes = rowofTspans.attributes();
	}

	public Element getRowElement() {
		return rowElement;
	}

	public void setRowElement(Element rowElement) {
		this.rowElement = rowElement;
	}

	public List<Attribute> getRowTextAttributes() {
		return rowTextAttributes;
	}

	public void setRowTextAttributes(List<Attribute> rowTextAttributes) {
		this.rowTextAttributes = rowTextAttributes;
	}

	public int getLineHeight() {
		return lineHeight;
	}

	public Node getAsNode()
	{
		return DocumentHelper.createText(text);
	}
	
	public List<Node> getAsNodes()
	{
		List<Node> nodes = rowElement.selectNodes("./node()");
		nodes.addAll(this.rowTextAttributes);
		return nodes;
	}
	
	public String toString()
	{
		return text;
	}


}
