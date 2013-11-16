package com.milo.questionpaper.xml;

import java.io.File;
import java.io.StringReader;
import java.util.Iterator;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.InputSource;

public class QuestionTemplateProcessorTest {
	
	@Test
	public void testExtractVariables() throws Exception
	{
		StringBuffer xmlDoc= new StringBuffer();
		xmlDoc.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
		.append("<question><name id=\"1\"/> has <var ref=\"x\"/> <noun id=\"3\"/> ")
		.append( "and <name id=\"2\"/> has <var ref=\"y\"/> <noun linkid=\"3\"/>")
		.append("How many more does <name linkid=\"1\"/> have than ")
		.append("<name linkid=\"2\"/></question>");
		
		
		SAXReader rdr = new SAXReader();
//		Document questionDoc=rdr.read(new InputSource(new StringReader(xmlDoc.toString())));
		Document questionDoc=rdr.read(new File("C:\\xml\\question-template-test.xml"));
		
		Element questionElement=questionDoc.getRootElement();
		QuestionTemplateProcessor qtp=new QuestionTemplateProcessor(questionElement);
		qtp.extractVariables();
		Assert.assertTrue(qtp.getVarMap().keySet().contains("x"));
		Assert.assertFalse(qtp.getVarMap().keySet().contains("a"));
		Assert.assertTrue(qtp.getVarMap().keySet().contains("y"));
	}
	
	@Test
	public void testExtractNouns() throws Exception
	{
		SAXReader rdr = new SAXReader();
//		Document questionDoc=rdr.read(new InputSource(new StringReader(xmlDoc.toString())));
		Document questionDoc=rdr.read(new File("C:\\xml\\question-template-test.xml"));
		Element questionElement=questionDoc.getRootElement();
		QuestionTemplateProcessor qtp=new QuestionTemplateProcessor(questionElement);
		qtp.extractNounRefs();
		Assert.assertTrue(qtp.getNounMap().keySet().size()==2);
		Assert.assertTrue(qtp.getNounMap().keySet().contains("noun2"));
		Assert.assertFalse(qtp.getNounMap().keySet().contains("noun3"));
		Assert.assertTrue(qtp.getNounMap().keySet().contains("noun1"));
		qtp.randomlyObtainNouns();
		Set<String> keySet = qtp.getNounMap().keySet();
		Element noun1,noun2;

       Iterator<String>iterator=keySet.iterator();
       String nounKey1=iterator.next();
       String nounKey2=iterator.next();
       noun1=(Element)qtp.getNounMap().get(nounKey1);
       noun2=(Element)qtp.getNounMap().get(nounKey2);
       Assert.assertTrue(noun2.asXML() +" and \n"+ noun1.asXML(),noun2.attributeValue("id")!=noun1.attributeValue("id"));
       System.out.println(noun2.asXML());
       System.out.println(noun1.asXML());
       
	}
	
	@Test
	public void testNounGroups()throws Exception
	{
		SAXReader rdr = new SAXReader();
//		Document questionDoc=rdr.read(new InputSource(new StringReader(xmlDoc.toString())));
		Document questionDoc=rdr.read(new File("C:\\xml\\templates\\question-template-test9.xml"));
		Element questionElement=questionDoc.getRootElement();
		QuestionTemplateProcessor qtp=new QuestionTemplateProcessor(questionElement);
		qtp.extractNounRefs();	
		Assert.assertTrue(qtp.getNounGroupMap().keySet().size()==1);
		Assert.assertTrue(qtp.getNounGroupMap().containsValue("weight"));
	}
	
	@Test
	public void testExtractNames() throws Exception 
	{
		SAXReader rdr = new SAXReader();
		Document questionDoc=rdr.read(new File("C:\\xml\\question-template-test.xml"));
		Element questionElement=questionDoc.getRootElement();
		QuestionTemplateProcessor qtp=new QuestionTemplateProcessor(questionElement);
        qtp.extractNames();
		Assert.assertTrue(qtp.getNameMap().keySet().size()==2);
		Assert.assertTrue(qtp.getNameMap().keySet().contains("name2"));
		Assert.assertFalse(qtp.getNameMap().keySet().contains("name3"));
		Assert.assertTrue(qtp.getNameMap().keySet().contains("name1"));
        qtp.randomlyObtainNames();
		Set<String> keySet = qtp.getNameMap().keySet();
		Element name1,name2;

       Iterator<String>iterator=keySet.iterator();
       String nameKey1=iterator.next();
       String nameKey2=iterator.next();
       name1=(Element)qtp.getNameMap().get(nameKey1);
       name2=(Element)qtp.getNameMap().get(nameKey2);
       Assert.assertTrue(name2.asXML() +" and \n"+ name1.asXML(),name2.attributeValue("id")!=name1.attributeValue("id"));
       System.out.println(name2.asXML());
       System.out.println(name1.asXML());
        
	}
}
