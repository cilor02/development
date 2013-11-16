package com.milo.questionpaper.xml.utils;

import java.util.List;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.milo.questionpaper.xml.SVGLayoutDAO;

public class LineMaker {
	private int lineWidth;
public LineMaker()
{
	String lineWidthStr= SVGLayoutDAO.getProperty("question.width");
	lineWidth=Integer.parseInt(lineWidthStr);
}
public Element wrapLines(List<BoundingBox> words)
{
	double accWidth=0.0;
	StringBuffer text=new StringBuffer();
	Element eleText = DocumentHelper.createElement("text");
	//add font-size attribute
	String fontSize=SVGLayoutDAO.getProperty("font.size");
	String fontFamily=SVGLayoutDAO.getProperty("font.family");
	
	eleText.addAttribute("font-size", fontSize);
	eleText.addAttribute("font-family", fontFamily);
	Element eleSVG  = DocumentHelper.createElement("g");

	StringBuffer textContent=null;
for(BoundingBox word: words)
{

	accWidth+=word.getBoundary().getWidth();

	if(accWidth>lineWidth)
	{
		//store accumulated line
		eleSVG.add(eleText);
		//create new line set word width to length offirst word
		eleText = DocumentHelper.createElement("text");
		eleText.addAttribute("font-size", fontSize);
		eleText.addAttribute("font-family", fontFamily);
//		eleText.addAttribute("xml:space", "preserve");
		accWidth=word.getBoundary().getWidth();
		
	}
	
        addContentForLine(eleText, word);
		
	}
//check if there is preceding text

eleSVG.add(eleText);
return eleSVG;
}
private void addContentForLine(Element eleText, BoundingBox word) {
	for(Node n:word.getAsNodes()) 
	{
	Node newNode = n.detach();
	eleText.add(newNode);	
	}
}

}

