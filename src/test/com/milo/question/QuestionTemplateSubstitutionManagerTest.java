package test.com.milo.question;

import java.io.StringReader;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.InputSource;

import com.milo.questionpaper.xml.QuestionTemplateProcessor;
import com.milo.questionpaper.xml.QuestionTemplateSubstitutionManager;

public class QuestionTemplateSubstitutionManagerTest {
	@Test
	public void testReplaceTagwithText() throws Exception
	{
		String text = "what is the time";
		StringBuffer xmlDoc= new StringBuffer();
		xmlDoc.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
		.append("<question><time/></question>");

		SAXReader rdr = new SAXReader();
  		Document questionDoc=rdr.read(new InputSource(new StringReader(xmlDoc.toString())));
        Element questionElement = questionDoc.getRootElement();
        QuestionTemplateSubstitutionManager qtsm= new QuestionTemplateSubstitutionManager();
        qtsm.replaceTagwithText(questionElement.element("time"), text);
        String converted = questionElement.asXML();
        String control   = new StringBuilder("<question>")
        .append(text)
        .append("</question>").toString();
        
        Assert.assertTrue("converted XML fragment: "+converted, control.equals(converted));
	    System.out.println(converted);
	}
}
