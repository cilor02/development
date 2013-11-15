package com.milo.questionpaper.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class EquationItemExtractor {
private List<Element> itemList;
private Map<String,Document> documentMap;
private Random random;
private final String ID_SELECTOR="@id="; 
public EquationItemExtractor()throws EquationItemExtractorException
{
	random = new Random();
	documentMap= new HashMap<String,Document>();
	ResourceBundle bundle = ResourceBundle.getBundle("itemextractor");
	Enumeration<String> keys = bundle.getKeys();
	String key =null;
	String fileName=null;
	Document doc=null;
	//load a properties
	// these a5e name of 
	//files to be used for equaion items
	try
	{
	
	while(keys.hasMoreElements())
	{
	 key=keys.nextElement();
	 System.out.println("key-"+key);
	 fileName=bundle.getString(key);
	 SAXReader reader = new SAXReader();
	 doc = reader.read(new File(fileName));
	 //use root element name as key to doc in map
	 documentMap.put(doc.getRootElement().getName(), doc);
	}
	} catch(Exception e)
	{
	throw new EquationItemExtractorException("failed to load property file");	
	}
}

public String getItem(String itemPoolKey)
{
Document document=documentMap.get(itemPoolKey);
String item = getRandomItem(document);
return item;
}

public int getRandomNumber(int low, int high)
{

	int offset=(int)(random.nextDouble()* (high-low));
	
	return low+offset;
}

public Element getElement(String itemPoolKey)
{
Document document=documentMap.get(itemPoolKey);
Element item = getRandomElement(document);
return item;
}

private String getRandomItem(Document doc)
{
	List <Element> items = doc.getRootElement().elements();
	int pos=(int)(random.nextDouble()* (items.size()));
	return items.get(pos).attributeValue("value");
}

private Element getRandomElement(Document doc)
{
	List <Element> items = doc.getRootElement().elements();
	int pos=(int)(random.nextDouble()* (items.size()));
	return items.get(pos);
}

public Element getRandomTextSetElement(List<Element> textSets)
{
	int pos=(int)(random.nextDouble()* (textSets.size()));
	return textSets.get(pos);
}

private String getItemById(Document doc,String id)
{

	Element item = doc.getRootElement().elementByID(ID_SELECTOR+id);
	return item.attributeValue("value");
}

public String getItemById(String itemPoolKey,String id)
{
	Document document=documentMap.get(itemPoolKey);
	String item = getItemById(document,id);
	return item;
}



}
