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
import org.dom4j.Element;
import org.dom4j.Node;

import com.milo.questionpaper.xml.SVGLayoutDAO;

public class SuperscriptTextBoundingBox extends TextBoundingBox{

	public SuperscriptTextBoundingBox(String text) {

      super(text);
  
	}

	public List<Node> getAsNodes()
	{
		List<Node> nodes=new ArrayList<Node>();
		Node node = getAsNode();
		 Element superScriptTspan = DocumentHelper.createElement("tspan");
		 superScriptTspan.addAttribute("baseline-shift", "super");
		 superScriptTspan.add(node);
	    nodes.add(superScriptTspan);
		return nodes;
	}

}
