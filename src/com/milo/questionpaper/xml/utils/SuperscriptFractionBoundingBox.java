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

public class SuperscriptFractionBoundingBox extends FractionBoundingBox{
    
	public SuperscriptFractionBoundingBox( String numerator,String denominator) {
        super(numerator,denominator);
	}

public List<Node> getAsNodes() {
	// TODO Auto-generated method stub
	FractionSVGGenerator fsvgGenerator = new FractionSVGGenerator();
	List<Element>elements = fsvgGenerator.generateSVGFragment(numerator, denominator).elements();
	//Element e=DocumentHelper.createElement("dummy");
	List<Node>nodes = new ArrayList<Node>();
	for(Element e:elements)
	{
    e.addAttribute("baseline-shift", "super");
	nodes.add(e.createCopy());
	}
	return nodes;
}

}
