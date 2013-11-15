package com.milo.questionpaper.xml;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.dom4j.Attribute;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

public class QuestionTemplateProcessor {
private final String VARIABLE_ELEMENTS="var";
private final String TABLE_ELEMENT="table";
private final String TEXT_ELEMENTS="text";
private final String VARIABLE_REFS="ref";
private final String TEXT_REFS="ref";
private final String TEXT_NAME="name";
private final String TEXT_VALUE="value";
private final String VARIABLE_LOW="low";
private final String VARIABLE_HIGH="high";
private final String NOUN_ELEMENTS="noun";
private final String NOUN_REFS="ref";
private final String NAME_ELEMENTS="name";
private final String QUESTION_RULES="question-rules";
private final String DERIVED_VARIABLES="derived-variables";
private final String QUESTION_TEXT="question-text";
private final String DIAGRAM_ELEMENT="drawing";
private final String TEXT_SETS="text-sets";
private final String TEXT_ITEM="text-item";

private Element eleQuestion;
private Element eleRules;
private Element eleDerivedVars;
private Element eleTextSets;
private Element eleDiagram;
private Element eleTable;

public Element getEleTable() {
	return eleTable;
}

public Element getEleDiagram() {
	return eleDiagram;
}

private Map<String,String> derivedVarMap;
private Map<String,String> varMap;
private Map<String,String> textMap;
private Map<String,String> dupVarMap;
private Map <String,Element>nounMap;
private Map <String,Element> nameMap;
private Map <String,String>nounGroupMap;
private Set <String> nounIdSet;
private Set <String> nameIdSet;
private EquationItemExtractor eqItmXtrctr;
private String xpath;
private String textXPath;
private VariableRulesMgr varRuleMgr;

public QuestionTemplateProcessor(Element eleQuestionTemplate) throws Exception
{

	ResourceBundle bundle = ResourceBundle.getBundle("xpath");
	xpath=bundle.getString("variables.xpath");
	textXPath=bundle.getString("text.xpath");

	this.varMap = new HashMap<String, String> ();
	this.textMap = new HashMap<String, String> ();
	this.dupVarMap = new HashMap<String, String> ();
	this.nameMap= new HashMap<String, Element> ();
	this.nounMap= new HashMap<String, Element>();
	this.nounGroupMap= new HashMap<String,String>();
	this.eqItmXtrctr=new EquationItemExtractor();

	nounIdSet=new HashSet<String>();
	nameIdSet=new HashSet<String>();
	eleQuestion=eleQuestionTemplate.element(QUESTION_TEXT);
	eleDiagram =eleQuestionTemplate.element(DIAGRAM_ELEMENT);
	eleRules=eleQuestionTemplate.element(QUESTION_RULES);
	eleDerivedVars=eleQuestionTemplate.element(DERIVED_VARIABLES);
	eleTextSets=eleQuestionTemplate.element(TEXT_SETS);
	eleTable = eleQuestion.element(TABLE_ELEMENT);
	//store any rules used for equation
	varRuleMgr=new VariableRulesMgr();
	varRuleMgr.setUpRules(eleRules);
	if(eleDerivedVars!=null){
	  varRuleMgr.setUpDerivedVariables(eleDerivedVars);
	}
}

public String getXpath() {
	return xpath;
}

public void setXpath(String xpath) {
	this.xpath = xpath;
}

public Map getNounMap() {
	return nounMap;
}

public Map getNameMap() {
	return nameMap;
}

public Map getVarMap() {
	return varMap;
}

public void processTemplate()
{
	this.extractVariables();
	this.extractNames();
	this.extractNounRefs();
	this.extractText();
	this.extractTableVariables();
}

private void storeVariables(Element eVar)
{
	this.varMap.put(eVar.getName(), eVar.getText());
}

private void storeNounRefs(Element eVar)
{
	this.nounMap.put(eVar.attributeValue("ref"), null);
}

protected void storeNounGroups(Element eVar)
{
	if(eVar.attributeValue("type")!=null)
	{
	this.nounGroupMap.put(eVar.attributeValue("ref"), eVar.attributeValue("type"));
	}
}
public Map<String, String> getNounGroupMap() {
	return nounGroupMap;
}

private void storeNames(Element eVar)
{
	this.nameMap.put(eVar.attributeValue("ref"), null);
}

public void randomlyObtainNames()
{
	Set<String>keys = this.nameMap.keySet();
	for(String key:keys)
	{
		Element name = this.eqItmXtrctr.getElement("names");
		//does this id already exist? if so store id in list
		while(nameIdSet.contains(name.attributeValue("id")))
			{
			name = this.eqItmXtrctr.getElement("names");
			}
		nameIdSet.add(name.attributeValue("id"));

		this.nameMap.put(key, name);
	}
}

public void randomlyObtainNouns()
{
	Set<String>keys = this.nounMap.keySet();
	for(String key:keys)
	{
		Element noun = this.eqItmXtrctr.getElement("nouns");
		//does this id already exist? if so store id in list

		while(nounIdSet.contains(noun.attributeValue("id"))||(this.nounGroupMap.containsKey(key)&&!this.nounGroupMap.get(key).equals(noun.attributeValue("type"))))
			{
			noun = this.eqItmXtrctr.getElement("nouns");
			}
		nounIdSet.add(noun.attributeValue("id"));

		this.nounMap.put(key, noun);
	}
}
private void extractNouns()
{
	List<Node> nounNodes = eleQuestion.selectNodes(this.NOUN_ELEMENTS);
	for(Node nounNode:nounNodes)
	{
		Element nodeElement = (Element)nounNode;
//		this.storeNouns(nodeElement);
	}
}

public void extractText()
{
	List<Node> varNodes = eleQuestion.selectNodes(textXPath);

    //refresh variable list
    textMap.clear();
    //get set of text randomly
    
	if(eleTextSets != null)
	{
	Element textSet = eqItmXtrctr.getRandomTextSetElement(eleTextSets.elements());

	List<Element> textItems = textSet.elements(this.TEXT_ITEM);
	
	for(Element textItem:textItems)
	{		
		String textName=textItem.attributeValue(TEXT_NAME);
		String textValue=textItem.attributeValue(TEXT_VALUE);
		textMap.put(textName, String.valueOf(textValue));
	}
	}
}

public void extractNounRefs()
{
	List<Element> nounElements = eleQuestion.elements(this.NOUN_ELEMENTS);
	HashSet<String>setNounRefs=new HashSet<String>();
	for(Element nounElement:nounElements)
	{
     storeNounRefs(nounElement);
     storeNounGroups(nounElement);
	}
}

public Map<String, String> getTextMap() {
	return textMap;
}

public void extractNames()
{
	List<Node> nameNodes = eleQuestion.elements(this.NAME_ELEMENTS);
	for(Node nameNode:nameNodes)
	{
		Element nameElement = (Element)nameNode;
		this.storeNames(nameElement);
	}
}


public Element getEleQuestion() {
	return eleQuestion;
}

public void extractVariables()
{

	List<Node> varNodes = eleQuestion.selectNodes(xpath);

	//List<Node> varNodes = eleQuestion.elements(this.VARIABLE_ELEMENTS);
    do
    {
    	//refresh variable list
    	varMap.clear();
	for(Node varNode:varNodes)
	{
		Element varElement = (Element)varNode;

		String varRef=varElement.attributeValue(VARIABLE_REFS);
		int low=1;
		String lowValue=varElement.attributeValue(VARIABLE_LOW);
		if(lowValue!=null)
		{
			low=Integer.valueOf(lowValue);
		}

		int high=3000;
		String highValue=varElement.attributeValue(VARIABLE_HIGH);
		if(highValue!=null)
		{
			high=Integer.valueOf(highValue);
		}
		int value=eqItmXtrctr.getRandomNumber(low, high);



		varMap.put(varRef, String.valueOf(value));
	}
    }while(varRuleMgr !=null&&!(varRuleMgr.validateVariables(varMap,dupVarMap)&&varRuleMgr.validateDerivedVariables(varMap)));

    derivedVarMap = varRuleMgr.getDerivedVars();
}

private Element generateDynamicTableVariables()
{
	
	Integer maxRows = 10;
	Integer minRows = 2;
	
	String strMaxRows = eleTable.attributeValue("max_rows");
	String strMinRows = eleTable.attributeValue("min_rows");
	
	if(strMaxRows != null )
	{
		try
		{
			maxRows = Integer.parseInt(strMaxRows);
		}catch(Exception e)
		{
			
		}
	}
	
	if(strMinRows != null )
	{
		try
		{
			minRows = Integer.parseInt(strMinRows);
		}catch(Exception e)
		{
			
		}
	}
	
	int no_of_rows = eqItmXtrctr.getRandomNumber(minRows, maxRows);
	
	Element newTableElement = DocumentHelper.createElement("table");
	int numCols = eleTable.elements("col-var").size();
	List<Element> listEleCols = eleTable.elements("col-var");
	
	// check headings
	Element headingRowElement = DocumentHelper.createElement("row");
	for(Element col : listEleCols)
	{
	   if(col.attribute("heading") != null)
	    {
		   Element headingCol = DocumentHelper.createElement("column");
		   headingCol.addText(col.attribute("heading").getText());
		   headingRowElement.add(headingCol);
	    }
	}
	newTableElement.add(headingRowElement);
	for(int row=0; row<no_of_rows; row++)
	{
		
		Element newRowElement = DocumentHelper.createElement("row");
		
		for(int col = 0; col < numCols; col++)
		{
			Element newColElement = DocumentHelper.createElement("column");
			Element eleSymbolicColVar = (Element)listEleCols.get(col);
			//generate name from row:col
			String name = "var_row"+(row + 1) +"_col" + (col + 1);
			Element eleColVar = DocumentHelper.createElement("var");
			eleColVar.addAttribute("ref", name);
			eleColVar.addAttribute("col", Integer.toString(col+1));
			eleColVar.addAttribute("row", Integer.toString(row+1));
			
			List<Attribute> oldAtts = eleSymbolicColVar.attributes();
			for(Attribute att : oldAtts)
			{
				if(!att.getName().equalsIgnoreCase("heading"))
				{
				  eleColVar.addAttribute(att.getName(), att.getStringValue());
				}
			}
//			eleColVar.addAttribute(VARIABLE_LOW,eleSymbolicColVar.attributeValue(VARIABLE_LOW));
//			eleColVar.addAttribute(VARIABLE_HIGH,eleSymbolicColVar.attributeValue(VARIABLE_HIGH));
			newColElement.add(eleColVar);
			newRowElement.add(newColElement);
		}		
		 newTableElement.add(newRowElement);
		 
	}
    return newTableElement;
}

public void extractTableVariables()
{
	if(this.eleTable != null)
	{
	Map<Integer, Set<Integer> > uniqueLists = new HashMap<Integer, Set<Integer>>();
// extract col info from table
	List<Element> varCols = eleTable.elements("col-var");
	
	int posCol = 0;
	for(Element varCol: varCols)
	{
		if(varCol.attributeValue("unique")!=null && (varCol.attributeValue("unique").equals("true")))
		{			
			uniqueLists.put(posCol, new HashSet<Integer>());
		}
		posCol++;
	}
	Element expandedTable = generateDynamicTableVariables();
	System.out.println(expandedTable.asXML());
	List<Node> varNodes = expandedTable.selectNodes(xpath);
		
	// load substitutions into expanded table
	//List<Node> varNodes = eleQuestion.elements(this.VARIABLE_ELEMENTS);

	for(Node varNode:varNodes)
	{
		Element varElement = (Element)varNode;

		//check if element is unique
		if(varElement.attribute("unique")!= null && varElement.attribute("unique").getStringValue().equals("true"))
		{
			//extract col attribute
			String strCol = varElement.attribute("col").getStringValue();
			Integer intCol = Integer.getInteger(strCol);
		}
		
		String varRef=varElement.attributeValue(VARIABLE_REFS);
		int low=1;
		String lowValue=varElement.attributeValue(VARIABLE_LOW);
		if(lowValue!=null)
		{
			low=Integer.valueOf(lowValue);
		}

		int high=3000;
		String highValue=varElement.attributeValue(VARIABLE_HIGH);
		if(highValue!=null)
		{
			high=Integer.valueOf(highValue);
		}
		
		int value=eqItmXtrctr.getRandomNumber(low, high);

		//check if element is unique
		if(varElement.attribute("unique")!= null && varElement.attribute("unique").getStringValue().equals("true"))
		{
			//extract col attribute
			String strCol = varElement.attribute("col").getStringValue();
			Integer intCol = Integer.parseInt(strCol);
			Set<Integer> setWithUniqueValues = uniqueLists.get(intCol - 1 );
			while (setWithUniqueValues.contains(new Integer(value)))
			{
				value=eqItmXtrctr.getRandomNumber(low, high);
			}
			setWithUniqueValues.add(new Integer(value));
		}

		varMap.put(varRef, String.valueOf(value));
	}

	//replace template table with expanded table
	Element parent = eleTable.getParent();

	eleTable.detach();

	parent.add(expandedTable);

	}
}


public VariableRulesMgr getVarRuleMgr() {
	return varRuleMgr;
}

public void setVarRuleMgr(VariableRulesMgr varRuleMgr) {
	this.varRuleMgr = varRuleMgr;
}

public Map<String, String> getDerivedVarMap() {
	return derivedVarMap;
}



}
