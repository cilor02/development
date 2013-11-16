package com.milo.questionpaper.xml;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.milo.questionpaper.xml.utils.BoundingBox;

public class FractionSVGGenerator implements SpecialTextSVGGenerator{

    private ArrayList<TextLayout> layouts;
    private ArrayList<GlyphVector> fractionParts;
    private ArrayList<TextLayoutPrintInfo>layoutInfo;
    private float fontSize;
    private String fontFamily;
    
    private String numerator;
    private String denominator;
    private String line;
    
    private SVGFractionTextSpan txtspn;
    
    public FractionSVGGenerator()
    {
    layouts       =  new ArrayList();
    fractionParts =  new ArrayList();   
    layoutInfo    =  new ArrayList();
    fontSize      = Float.parseFloat(SVGLayoutDAO.getProperty("fraction.font.size"));
    fontFamily    = SVGLayoutDAO.getProperty("font.family");

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
        
        placeComponentsRelatively();
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
//        as.addAttribute(TextAttribute.FAMILY, "Arial", 12,18);
        
        AttributedCharacterIterator aci = as.getIterator();
        
        //store glyphs
        GlyphVector glyphVector = font.createGlyphVector(frc,aci);

        fractionParts.add(glyphVector);
        //store text layout
        TextLayout txtLyout = new TextLayout(as.getIterator(),frc);
        layouts.add(txtLyout);
    }
    
    
    private void placeComponentsRelatively()
    {
        FractionUI fractionUI = new FractionUI(fractionParts);
        layoutInfo.add(new TextLayoutPrintInfo(layouts.get(0),fractionUI.getNumeratorOffset()));
        layoutInfo.add(new TextLayoutPrintInfo(layouts.get(1),fractionUI.getLineOffset()));
        layoutInfo.add(new TextLayoutPrintInfo(layouts.get(2),fractionUI.getDenominatorOffset()));
        
        txtspn = new SVGFractionTextSpan(layoutInfo,fractionParts);
        
       
    }
    

/**
 * 
 * {@inheritDoc}
 */
	public Element generateSVGFragment(Element fractionElement)
	{
		numerator   = fractionElement.element("numerator").getText();
		denominator = fractionElement.element("denominator").getText();
		
	       this.prepareGeometricalInfo();
		
// parent of fraction content
		Element tspanHolder = DocumentHelper.createElement("tspanHolder");
		
		Element [] svgFragments = new Element[3];
		
		//numerator 
    	svgFragments[0]= createSVGTspan(numerator, txtspn.getDxNum(), txtspn.getDyNum());
        //line
    	svgFragments[1]= createSVGTspan(line, txtspn.getDxLine(), txtspn.getDyLine());
    	//denominator
    	svgFragments[2]= createSVGTspan(denominator, txtspn.getDxDen(), txtspn.getDyDen());
    	for(int i=0;i<svgFragments.length;i++)
    	{
    	tspanHolder.add(svgFragments[i]);
    	}
    	return tspanHolder;
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 */
		public Element generateSVGFragment(String numerator,String denominator)
		{
			this.numerator   = numerator;
			this.denominator = denominator;
			
		       this.prepareGeometricalInfo();
			
	// parent of fraction content
			Element tspanHolder = DocumentHelper.createElement("tspanHolder");
			
			Element [] svgFragments = new Element[3];
			
			//numerator 
	    	svgFragments[0]= createSVGTspan(numerator, txtspn.getDxNum(), txtspn.getDyNum());
	        //line
	    	svgFragments[1]= createSVGTspan(line, txtspn.getDxLine(), txtspn.getDyLine());
	    	//denominator
	    	svgFragments[2]= createSVGTspan(denominator, txtspn.getDxDen(), txtspn.getDyDen());
	    	for(int i=0;i<svgFragments.length;i++)
	    	{
	    	tspanHolder.add(svgFragments[i]);
	    	}
	    	return tspanHolder;
		}
	
	public Element createSVGTspan(String val, double textdx,double textdy)
	{
		Element tspan = DocumentHelper.createElement("tspan");
		tspan.addAttribute("dx",Double.toString(textdx));
		tspan.addAttribute("dy",Double.toString(textdy));
		tspan.addAttribute("font-size", String.valueOf(fontSize));
		tspan.addAttribute("font-family", fontFamily);		
		tspan.addText(val);
		return tspan;
	}
	
}
