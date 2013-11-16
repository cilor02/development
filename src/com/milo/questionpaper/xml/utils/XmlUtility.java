package com.milo.questionpaper.xml.utils;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.milo.questionpaper.svg.text.TableFormatter;
import com.milo.questionpaper.xml.FractionSVGGenerator;
import com.sun.org.apache.bcel.internal.generic.Type;

public class XmlUtility {
	/**
	 * replaces content of e1 with e2s content
	 * 
	 * @param e1
	 * @param e2
	 * @return
	 */
	public static Element replaceWithContent(Element e1, Element e2) {
		Element parent = e1.getParent();
		List<Node> siblings = parent.content();

		siblings.addAll(getPosition(e1), e2.content());
		e1.detach();
		return e1;
	}

	/**
	 * replaces content of e1 with nodes list
	 * 
	 * @param e1
	 * @param e2
	 * @return
	 */
	public static Element replaceWithContent(Element e1, List<Node> nodes) {
		Element parent = e1.getParent();
		List<Node> siblings = parent.content();

		siblings.addAll(getPosition(e1), nodes);
		e1.detach();
		return e1;
	}

	public static List<BoundingBox> split(Element e) {
		List<BoundingBox> wordBoxes = new ArrayList<BoundingBox>();
		List<Node> nodes = e.content();
		StringBuffer text = new StringBuffer();
		for (Node n : nodes) {

			if (n.getNodeType() == Node.TEXT_NODE) {
				if(text==null)
				{
					text = new StringBuffer();
				}
				text.append(n.getText());
			} else {
				// store accumulated text
				String textNodes = text.toString();
				String[] wordsandSpaces = textNodes.split(" ");
				for (String word : wordsandSpaces) {
					if(word.length()==0)
					{
						word=new String(" ");
					}
					if(wordBoxes.size()>0)
					{
						wordBoxes.add(new TextBoundingBox(" "));
					}
					wordBoxes.add(new TextBoundingBox(word));
				}
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) n;
					if (element.getName().equals("fraction")) {
						String numerator = element.element("numerator").getText();
						String denominator = element.element("denominator").getText();
						wordBoxes.add(new FractionBoundingBox(numerator,
								denominator));
					}
					
					if (element.getName().equals("table")) {
						TableFormatter tableFormatter = new TableFormatter();
						Element svgTable = tableFormatter.convertToSVG(element);
						System.out.println(svgTable.asXML());
						List<Element> textElements = svgTable.elements("text");
						for(Element textElement : textElements){
						;
						wordBoxes.add(new TableRowBoundingBox(textElement));
						}
					}
					
					if (element.getName().equals("superscript")) {
						List<BoundingBox> superscripts = XmlUtility.split(element);
						for(final BoundingBox superscriptBox : superscripts)							
						{
							if(superscriptBox instanceof TextBoundingBox ){
								SuperscriptTextBoundingBox superScrptTextBox = new SuperscriptTextBoundingBox(superscriptBox.toString());
								wordBoxes.add(superScrptTextBox);
							}
							if(superscriptBox instanceof FractionBoundingBox )
							{
								FractionBoundingBox fractionBoundingBox = (FractionBoundingBox)superscriptBox;
								SuperscriptFractionBoundingBox superScrptfractionBox = new SuperscriptFractionBoundingBox(fractionBoundingBox.getNumerator(),fractionBoundingBox.getDenominator());
								wordBoxes.add(superScrptfractionBox);					
							}
						}
					}
					

					
				}
				text = null;
			}

		}
		if(text!=null)
		{
			String textNodes = text.toString();
			String[] wordsandSpaces = textNodes.split(" ");
			for (String word : wordsandSpaces) {
				if(word.length()==0)
				{
					word=new String(" ");
				}
				
				if(wordBoxes.size()>0)
				{
					wordBoxes.add(new TextBoundingBox(" "));
				}
				wordBoxes.add(new TextBoundingBox(word));
			}
		}
		return wordBoxes;
	}

	private static int getPosition(Element e) {
		Element parent = e.getParent();
		List<Node> siblings = parent.content();
		// find our element from allits parents children
		int pos = siblings.indexOf(e);
		return pos;

	}
}
