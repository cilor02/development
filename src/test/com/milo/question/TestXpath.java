package test.com.milo.question;

import java.io.File;
import java.util.ResourceBundle;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.milo.questionpaper.xml.EquationItemExtractor;
import com.milo.questionpaper.xml.QuestionTemplateProcessor;
import com.milo.questionpaper.xml.QuestionTemplateSubstitutionManager;

public class TestXpath {

	public static void main(String[]args) throws Exception
	{
		SAXReader rdr = new SAXReader();
//		Document questionDoc=rdr.read(new InputSource(new StringReader(xmlDoc.toString())));
		Document questionDoc=rdr.read(new File("C:\\xml\\templates\\question-template-test18.xml"));
		ResourceBundle bundle = ResourceBundle.getBundle("xpath");
		String xpath=bundle.getString("replaceable.elements.xpath");
		Element questionElement=questionDoc.getRootElement().element("question-text");
		//System.out.println(questionElement.createXPath("*").selectNodes(questionElement));
System.out.println(questionElement.selectNodes(xpath).size());
//System.out.println(questionElement.selectNodes("xpath").size());
	}
	
	
}
