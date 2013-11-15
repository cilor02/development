package com.milo.questionpaper.xml;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.dom4j.Branch;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.Text;

import com.milo.draw.Diagram;

public class QuestionTemplateSubstitutionManager {
	private Element questionElement;
	private String xpath;
	private String textXPath;
	public Element getQuestionElement() {
		return questionElement;
	}
	public void setQuestionElement(Element questionElement) {
		this.questionElement = questionElement;
	}

	public QuestionTemplateSubstitutionManager() {
		ResourceBundle bundle = ResourceBundle.getBundle("xpath");
		xpath=bundle.getString("replaceable.elements.xpath");
		textXPath=bundle.getString("replaceable.text.xpath");

		// TODO Auto-generated constructor stub
	}

	private QuestionTemplateProcessor qtp; 
public void storeQuestion(Element questionElement)
{
	Element clonedElement = questionElement.createCopy();
	clonedElement.content();
	
}
public void substituteValues(QuestionTemplateProcessor qtp)
{
	this.qtp=qtp;
	questionElement=qtp.getEleQuestion().createCopy();
}

public void substitute()
{
	//List<Element>elements = questionElement.elements();
	List<Node>nodes = questionElement.selectNodes(xpath);
	String replacmentTxt=null;
	for(Node node:nodes)
	{
		Element element=(Element)node;
		replacmentTxt=getReplacement(element);
		this.replaceTagwithText(element,replacmentTxt );
	}
}

public void substituteDiagram()throws Exception
{
	//List<Element>elements = questionElement.elements();
	Element diagram = qtp.getEleDiagram();
	if(diagram != null)
	{
    Diagram dia = new Diagram (qtp);
    Element svgDiagram = dia.parse(diagram);
    
	Element eleDrawing = DocumentHelper.createElement("drawing");
	eleDrawing.addAttribute("height", String.valueOf(dia.getDiaHeight()));
	eleDrawing.addAttribute("width", String.valueOf(dia.getDiaWidth()));
	eleDrawing.add(svgDiagram.element("g").detach());

    questionElement.content().add(0, eleDrawing);
	}
	
}

public void substituteTable()throws Exception
{
	Element table = qtp.getEleTable();
	
}

public void substituteTextSet()
{
	//List<Element>elements = questionElement.elements();
	List<Node>nodes = questionElement.selectNodes(textXPath);
	String replacmentTxt=null;
	for(Node node:nodes)
	{
		Element element=(Element)node;
		replacmentTxt=getReplacement(element);
		this.replaceTagwithText(element,replacmentTxt );
	}
}


public void replaceTagwithText(Element tag, String replaceString)
{
	Element parentTag = tag.getParent();
	List<Node> nodes = parentTag.content();
	int index=nodes.indexOf(tag);
	// insert new substitute text
	nodes.add(index, DocumentHelper.createText(replaceString));
	//remove tag
	tag.detach();
}

public String getReplacement(Element e)
{
	//decimal tag
	if(e.getName().equals("derived-var"))
	{
		Map<String, String> derivedVarsMap = qtp.getDerivedVarMap();
		return (String)derivedVarsMap.get(e.attributeValue("ref"));
	}
	
	//decimal tag
	if(e.getName().equals("decimal"))
	{
		Element eleIntegral  = e.element("integral");
		Element varIntegral = null;
		String varIntegralValue = "0";
		if(eleIntegral != null)
		{
		 varIntegral  = eleIntegral.element("var");
		 varIntegralValue = (String) qtp.getVarMap().get(varIntegral.attributeValue("ref"));
		}
		Element eleFractional = e.element("fractional");
		
		Element varFractional  = eleFractional.element("var");
		String varFractionalValue = (String) qtp.getVarMap().get(varFractional.attributeValue("ref"));
		
		String fractionPrecision = varFractional.attributeValue("high");
		int pow10 = fractionPrecision.length();

		long tenPower = 1;
		for (int i=0; i<pow10; i++)
		{
			tenPower *=10;
		}
		BigDecimal big = BigDecimal.valueOf( Long.valueOf(varIntegralValue) * tenPower + Long.valueOf(varFractionalValue));
		
		return big.movePointLeft(pow10).toPlainString(); 	
	}
	//Name tag
	if(e.getName().equals("name"))
	{
		Element eleName = (Element)qtp.getNameMap().get(e.attributeValue("ref"));
		return eleName.attributeValue("value");	
	}
	//Noun tag
	if(e.getName().equals("noun"))
	{
		Element eleNoun = (Element)qtp.getNounMap().get(e.attributeValue("ref"));
		
		// if noun  not linked to a variable for quantity or size
		if(e.attribute("varref")==null)
		{
		if(e.attribute("qty")!=null&&e.attributeValue("qty").equals("plural"))
		{
			//plural version
			return eleNoun.attributeValue("plural") ;
		}
		// singular version
		return eleNoun.attributeValue("value"); 
		}
		
		//find associated quantity of this noun to decide if to pluralise or not
		// reference to qty variable  		
		
		String varRef=e.attributeValue("varref");
		
		String nounQty = (String)qtp.getVarMap().get(varRef);
		
		if(Integer.parseInt(nounQty)>1)
		{
			if(eleNoun.attribute("plural")!=null)
			{
				return eleNoun.attributeValue("plural") ;
			}
		}
		
		return eleNoun.attributeValue("value");
	}
	//Variable tag
	if(e.getName().equals("var"))
	{
		String varValue = (String) qtp.getVarMap().get(e.attributeValue("ref"));
		return varValue;
	}
	
	//Text tag
	if(e.getName().equals("text"))
	{
		String varValue = (String) qtp.getTextMap().get(e.attributeValue("textref"));
		return varValue;
	}
	return null;
}

}
