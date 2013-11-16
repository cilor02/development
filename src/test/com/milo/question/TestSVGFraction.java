package test.com.milo.question;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.milo.questionpaper.svg.GenerateSVG;
import com.milo.questionpaper.svg.text.FractionFormatter;
import com.milo.questionpaper.xml.EquationItemExtractor;
import com.milo.questionpaper.xml.FractionSVGGenerator;
import com.milo.questionpaper.xml.QuestionTemplateProcessor;
import com.milo.questionpaper.xml.QuestionTemplateSubstitutionManager;
import com.milo.questionpaper.xml.utils.XmlUtility;

public class TestSVGFraction {

	public static void main(String[] args) throws Exception {
		SAXReader rdr = new SAXReader();
		// Document questionDoc=rdr.read(new InputSource(new
		// StringReader(xmlDoc.toString())));
		Document questionDoc = rdr.read(new File(
				"C:\\xml\\output\\fractiontest2.xml"));

		List<Element>fractions = questionDoc.getRootElement()
		.selectNodes("//fraction");

		for(Element fractionEle: fractions)
		{
		FractionSVGGenerator fracSVGGenerator = new FractionSVGGenerator();
		Element fragSVG = fracSVGGenerator.generateSVGFragment(fractionEle);
		System.out.println(fragSVG.asXML());
		
		XmlUtility.replaceWithContent(fractionEle, fragSVG.content());
		
		}
		System.out.println(questionDoc.getRootElement().asXML());
	}

}
