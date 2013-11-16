package test.com.milo.question;

import java.io.File;
import java.util.List;

import junit.framework.Assert;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.milo.questionpaper.xml.EquationItemExtractor;
import com.milo.questionpaper.xml.QuestionTemplateProcessor;
import com.milo.questionpaper.xml.QuestionTemplateSubstitutionManager;
import com.milo.questionpaper.xml.utils.BoundingBox;
import com.milo.questionpaper.xml.utils.XmlUtility;

public class TestTableLinesSubstitution {

	public static void main(String[]args) throws Exception
	{
		SAXReader rdr = new SAXReader();
//		Document questionDoc=rdr.read(new InputSource(new StringReader(xmlDoc.toString())));
		Document questionDoc=rdr.read(new File("C:\\quiz-templates\\test.xml"));
		
		Element questionElement=questionDoc.getRootElement();

        QuestionTemplateProcessor qtp=new QuestionTemplateProcessor(questionElement);
		qtp.extractVariables();
		qtp.extractText();
		qtp.extractNounRefs();
		qtp.extractNames();
		qtp.extractTableVariables();
		
		qtp.randomlyObtainNames();
		qtp.randomlyObtainNouns();

		QuestionTemplateSubstitutionManager qtsm = new QuestionTemplateSubstitutionManager();
		qtsm.substituteValues(qtp);
		qtsm.substitute();
		qtsm.substituteTextSet();
		qtsm.substituteDiagram();

		System.out.println("question template was :"+qtp.getEleQuestion().asXML());
		System.out.println("generated question is :"+qtsm.getQuestionElement().asXML());
	    List<BoundingBox> boxes = XmlUtility.split(qtsm.getQuestionElement());
	    for(BoundingBox bBox:boxes)
	    {
	    	//System.out.println(bBox +" "+ bBox.toString().length());
	    	//System.out.println(bBox +" "+ bBox.getBoundary().getWidth());
	    	
	    }
	    System.out.println(boxes);
	    System.out.println(boxes.size());
	    Assert.assertTrue(boxes.size()>0);
	
	
	}
	
	
}
