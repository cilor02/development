package test.com.milo.question;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.milo.questionpaper.svg.GenerateSVG;
import com.milo.questionpaper.xml.EquationItemExtractor;
import com.milo.questionpaper.xml.QuestionTemplateProcessor;
import com.milo.questionpaper.xml.QuestionTemplateSubstitutionManager;

public class TestSVGGeneration {

	public static void main(String[]args) throws Exception
	{
		SAXReader rdr = new SAXReader();
//		Document questionDoc=rdr.read(new InputSource(new StringReader(xmlDoc.toString())));
		Document questionDoc=rdr.read(new File("C:\\xml\\output\\quiz2009.02.23-14.09.53.xml"));
		
		Element questionElement=questionDoc.getRootElement();
        GenerateSVG genSVG = new GenerateSVG();
        genSVG.generateSVG(questionDoc);
	}
	
}
