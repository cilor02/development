package test.com.milo.question;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.milo.questionpaper.svg.GenerateSVG;
import com.milo.questionpaper.svg.text.FractionFormatter;
import com.milo.questionpaper.xml.EquationItemExtractor;
import com.milo.questionpaper.xml.QuestionTemplateProcessor;
import com.milo.questionpaper.xml.QuestionTemplateSubstitutionManager;

public class TestFractionFormatter {

	public static void main(String[]args) throws Exception
	{
		SAXReader rdr = new SAXReader();
//		Document questionDoc=rdr.read(new InputSource(new StringReader(xmlDoc.toString())));
		Document questionDoc=rdr.read(new File("C:\\xml\\output\\fractiontest.xml"));
		Element fractionEle=(Element)questionDoc.getRootElement().selectSingleNode("//fraction");
		
        FractionFormatter fracFmttr = new FractionFormatter();
        Element fragSVG = fracFmttr.convertToSVG(fractionEle);
        System.out.println(fragSVG.asXML());
	}
	
}
