package com.milo.questionpaper.xml.utils;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Branch;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.milo.questionpaper.xml.FractionSVGGenerator;
import com.milo.questionpaper.xml.SVGLayoutDAO;
import com.milo.questionpaper.xml.TextLayoutPrintInfo;

public class FractionBoundingBox implements BoundingBox{

	protected String numerator;
	protected String denominator;
	private String line;
    private ArrayList<GlyphVector> fractionParts;
    private float fontSize;
    private String fontFamily;
    
    
	public FractionBoundingBox( String numerator,String denominator) {

		this.denominator = denominator.trim();
		this.numerator = numerator.trim();
	    fractionParts =  new ArrayList();   
	    fontSize      = Float.parseFloat(SVGLayoutDAO.getProperty("fraction.font.size"));
	    fontFamily    = SVGLayoutDAO.getProperty("font.family");
	    prepareGeometricalInfo();
	}

    public Rectangle2D getBoundary()
    {
		double numWidth=fractionParts.get(0).getLogicalBounds().getWidth();
		double lineWidth=fractionParts.get(1).getLogicalBounds().getWidth();
		double denWidth=fractionParts.get(2).getLogicalBounds().getWidth();
		
		double numHeight=fractionParts.get(0).getLogicalBounds().getHeight();
		double lineHeight=fractionParts.get(1).getLogicalBounds().getHeight();
		double denHeight=fractionParts.get(2).getLogicalBounds().getHeight();
		
		double maxWidth=numWidth>lineWidth?numWidth:lineWidth;
		double width=maxWidth>denWidth?maxWidth:denWidth;
		double height=numHeight+lineHeight+denHeight;
	    
		return new Rectangle2D.Double(0,0,width,height);
	       
    }
	
	
	 private void processString(AttributedString as)
	    {
	        BufferedImage im = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
	        Graphics2D g = (Graphics2D)im.getGraphics();

	       

	        Map<Attribute, Object> attrMap = new HashMap<Attribute,Object>();
	        attrMap.put(TextAttribute.FAMILY, fontFamily);
	        attrMap.put(TextAttribute.SIZE, fontSize);
	        
	        as.addAttribute(TextAttribute.FAMILY, fontFamily);
	        as.addAttribute(TextAttribute.SIZE, fontSize);
	        
	        Font font = new Font(attrMap);
	        g.setFont(font);
	        FontRenderContext frc = g.getFontRenderContext();
//	        as.addAttribute(TextAttribute.FAMILY, "Arial", 12,18);
	        
	        AttributedCharacterIterator aci = as.getIterator();
	        
	        //store glyphs
	        GlyphVector glyphVector = font.createGlyphVector(frc,aci);

	        fractionParts.add(glyphVector);
	        //store text layout
	    }
	
	    private void prepareGeometricalInfo()
	    {
	    	//numerator
	        AttributedString as1 = new AttributedString(numerator);
	        processString(as1);
	        //dividing line
	        
	        //find len of largest of num and den.
	        
	        int lineLen = denominator.length()>numerator.length()?denominator.length():numerator.length();
	        
	        StringBuilder lineBffr = new StringBuilder();
	        
	        for(int i=0;i<lineLen;i++)
	        {
	        	lineBffr.append("_");
	        }
	        line=lineBffr.toString();
	        //denominator
	        
	        AttributedString as2 = new AttributedString(lineBffr.toString());
	        processString(as2);
	        
	        AttributedString as3 = new AttributedString(denominator);
	        processString(as3);
	        
	    }
	    	 
	 
	 
public String toString()
{
	return numerator + "/" + denominator;
}

public Node getAsNode() {
	// TODO Auto-generated method stub
	FractionSVGGenerator fsvgGenerator = new FractionSVGGenerator();
	return fsvgGenerator.generateSVGFragment(numerator, denominator);
}


public List<Node> getAsNodes() {
	// TODO Auto-generated method stub
	FractionSVGGenerator fsvgGenerator = new FractionSVGGenerator();
	List<Element>elements = fsvgGenerator.generateSVGFragment(numerator, denominator).elements();
	//Element e=DocumentHelper.createElement("dummy");
	List<Node>nodes = new ArrayList<Node>();
	for(Element e:elements)
	{
		
	nodes.add(e.createCopy());
	}
	return nodes;
}

public String getNumerator() {
	return numerator;
}

public String getDenominator() {
	return denominator;
}
}
