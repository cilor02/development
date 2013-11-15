package test.com.milo.question;

import java.io.File;
import java.util.List;
import java.util.ResourceBundle;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.milo.questionpaper.xml.EquationItemExtractor;
import com.milo.questionpaper.xml.QuestionTemplateProcessor;
import com.milo.questionpaper.xml.QuestionTemplateSubstitutionManager;
import com.milo.questionpaper.xml.utils.BoundingBox;
import com.milo.questionpaper.xml.utils.LineLayoutPresenter;
import com.milo.questionpaper.xml.utils.LineMaker;
import com.milo.questionpaper.xml.utils.QuizFormatter;
import com.milo.questionpaper.xml.utils.XmlUtility;

public class TestQuizFormatter {

	public static void main(String[]args) throws Exception
	{
		SAXReader rdr = new SAXReader();
		Document quizDoc=rdr.read(new File("C:\\xml\\output\\quiz2009.04.15-02.02.54.xml"));   
		QuizFormatter quizFormatter = new QuizFormatter(quizDoc.getRootElement(),0,50);
		System.out.println(quizFormatter.layQuestionsOut().asXML());
		
	}
	
	
}
