package com.milo.questionpaper.svg;

import java.io.File;
import java.util.List;
import java.util.ResourceBundle;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.sun.org.apache.bcel.internal.generic.DCMPG;

public class GenerateSVG {
	
	private String docDir;
	private String outSVGDir;
	private SAXReader reader;
	public GenerateSVG() {
		// TODO Auto-generated constructor stub
//	
//
//	ResourceBundle bundle = ResourceBundle.getBundle("itemextractor");
//	docDir = bundle.getString("output.questions.dir");
//	outSVGDir = bundle.getString("output.svg.dir");

	}
	
	
	public void generateSVG(Document doc)throws Exception
	{
		Element svgElement=DocumentHelper.createElement("svg");
		Element svgGroup=DocumentHelper.createElement("g");
		svgGroup.addAttribute("font-size", "12");
		svgElement.add(svgGroup);
		Document outSVGDoc = DocumentHelper.createDocument(svgElement);
        Element root=doc.getRootElement();
        List<Node> nodes = root.selectNodes("question-text");
		for(Node node:nodes)
		{
			Element e = (Element)node;
			
			e.setName("text");
			
			List<Node>fractionNodes=e.selectNodes("fraction");
			for(Node fractionNode:fractionNodes)
			{
				Element fractionElement = (Element)fractionNode;
				Element parentEle = fractionElement.getParent();
				int idx = parentEle.content().indexOf(fractionElement);
				Element tspanNumerator=DocumentHelper.createElement("tspan");
				tspanNumerator.addAttribute("baseline-shift", "super");
				tspanNumerator.addAttribute("font-size", "6");
				
				Element tspanDenominator=DocumentHelper.createElement("tspan");
				tspanDenominator.addAttribute("baseline-shift", "sub");	
				tspanDenominator.addAttribute("font-size", "6");
				
				parentEle.content().add(idx, tspanNumerator);
				
				parentEle.content().add(idx+1, tspanDenominator);
				
				parentEle.content().add(idx+1, DocumentHelper.createText("/"));
				String numerator = fractionElement.selectSingleNode("numerator").getText();
				String denominator = fractionElement.selectSingleNode("denominator").getText();
							
				tspanNumerator.setText(numerator);
				
				tspanDenominator.setText(denominator);
				
				
				fractionElement.detach();
			}
			

			//System.out.println(e.asXML());
			///outSVGDoc.add(e);
			svgGroup.add(e.createCopy());
		}
		

		System.out.println(outSVGDoc.asXML());
		
		outSVGDoc=null;
		
	}
}
