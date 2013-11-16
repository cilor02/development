package com.milo.questionpaper.xml;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class VariableRulesMgrTest {
@Test
	public void testSetUpRules()throws Exception
	{
	
	SAXReader rdr = new SAXReader();
//	Document questionDoc=rdr.read(new InputSource(new StringReader(xmlDoc.toString())));
	Document questionDoc=rdr.read(new File("C:\\xml\\question-template-test3.xml"));
	
	Element questionElement=questionDoc.getRootElement();
	Element elementRules=questionElement.element("question-rules");
	VariableRulesMgr vRMgr=new VariableRulesMgr();
	vRMgr.setUpRules(elementRules);
	
	Assert.assertTrue(vRMgr.getEquationVariableRules().size()==2);
	}
	
@Test
public void testVaidateVariables()throws Exception
{

SAXReader rdr = new SAXReader();
//Document questionDoc=rdr.read(new InputSource(new StringReader(xmlDoc.toString())));
Document questionDoc=rdr.read(new File("C:\\xml\\question-template-test4.xml"));

Element questionElement=questionDoc.getRootElement();
Element elementRules=questionElement.element("question-rules");
VariableRulesMgr vRMgr=new VariableRulesMgr();
Map<String,String> map=new HashMap();

vRMgr.setUpRules(elementRules);

map.put("x","30");
map.put("y","10");

Assert.assertTrue(vRMgr.validateVariables(map));

map.put("x","18");
map.put("y","7");

Assert.assertFalse(vRMgr.validateVariables(map));
}
}
