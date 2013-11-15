package com.milo.questionpaper.xml;

import java.io.File;
import java.util.List;

import junit.framework.Assert;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import com.milo.questionpaper.xml.utils.BoundingBox;
import com.milo.questionpaper.xml.utils.FractionBoundingBox;
import com.milo.questionpaper.xml.utils.XmlUtility;

public class XmlUtilityTest {
@Test
public void testSplit() throws DocumentException
{
	SAXReader rdr = new SAXReader();
//	Document questionDoc=rdr.read(new InputSource(new StringReader(xmlDoc.toString())));
	Document questionDoc=rdr.read(new File("C:\\xml\\output\\fractiontest.xml"));
    Element qText=questionDoc.getRootElement().element("question-text");
    List<BoundingBox> boxes = XmlUtility.split(qText);
    for(BoundingBox bBox:boxes)
    {
    	System.out.println(bBox +" "+ bBox.toString().length());
    	System.out.println(bBox +" "+ bBox.getBoundary().getWidth());
    	
    }
    System.out.println(boxes);
    System.out.println(boxes.size());
    Assert.assertTrue(boxes.size()>0);
}
}
