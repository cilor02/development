package com.milo.questionpaper.xml.utils;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.TextAttribute;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentHelper;
import org.dom4j.Node;

import com.milo.questionpaper.xml.SVGLayoutDAO;

public class TextBoundingBox implements BoundingBox{

	private String text;
    private float fontSize;
    private String fontFamily;
    private GlyphVector glyphVector;
    private int lineHeight;
	public Rectangle2D getBoundary() {
		// TODO Auto-generated method stub
		return glyphVector.getLogicalBounds();
	}

	public TextBoundingBox(String text) {

		this.text = text;
		AttributedString as= new AttributedString(text);
	    fontSize      = Float.parseFloat(SVGLayoutDAO.getProperty("font.size"));
	    fontFamily    = SVGLayoutDAO.getProperty("font.family");
        BufferedImage im = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D)im.getGraphics();
        lineHeight = g.getFontMetrics().getHeight();
       

        Map<Attribute, Object> attrMap = new HashMap<Attribute,Object>();
        attrMap.put(TextAttribute.FAMILY, fontFamily);
        attrMap.put(TextAttribute.SIZE, fontSize);
        
        as.addAttribute(TextAttribute.FAMILY, fontFamily);
        as.addAttribute(TextAttribute.SIZE, fontSize);
        
        Font font = new Font(attrMap);
        g.setFont(font);
        FontRenderContext frc = g.getFontRenderContext();
//        as.addAttribute(TextAttribute.FAMILY, "Arial", 12,18);
        
        AttributedCharacterIterator aci = as.getIterator();
        
        //store glyphs
        glyphVector = font.createGlyphVector(frc,aci);

	    
        
        
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
		List<Node> nodes=new ArrayList<Node>();
		
	    nodes.add(getAsNode());
		return nodes;
	}
	public String toString()
	{
		return text;
	}


}
