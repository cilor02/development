package test.com.milo.question;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.milo.questionpaper.xml.EquationItemExtractor;
import com.milo.questionpaper.xml.QuestionTemplateProcessor;
import com.milo.questionpaper.xml.QuestionTemplateSubstitutionManager;

public class TestSubstitution {

	public static void main(String[]args) throws Exception
	{
		SAXReader rdr = new SAXReader();
//		Document questionDoc=rdr.read(new InputSource(new StringReader(xmlDoc.toString())));
		Document questionDoc=rdr.read(new File("C:\\xml\\templates\\question-template-test18.xml"));
		
		Element questionElement=questionDoc.getRootElement();

        QuestionTemplateProcessor qtp=new QuestionTemplateProcessor(questionElement);
		qtp.extractVariables();
		qtp.extractNounRefs();
		qtp.extractNames();
		
		qtp.randomlyObtainNames();
		qtp.randomlyObtainNouns();
        
		QuestionTemplateSubstitutionManager qtsm = new QuestionTemplateSubstitutionManager();
		qtsm.substituteValues(qtp);
		qtsm.substitute();
		System.out.println("question template was :"+qtp.getEleQuestion().asXML());
		System.out.println("generated question is :"+qtsm.getQuestionElement().asXML());
	}
	
	
}
