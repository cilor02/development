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
import com.milo.questionpaper.xml.utils.XmlUtility;

public class TestLineMaker {

	public static void main(String[]args) throws Exception
	{
		SAXReader rdr = new SAXReader();
//		Document questionDoc=rdr.read(new InputSource(new StringReader(xmlDoc.toString())));
		Document questionDoc=rdr.read(new File("C:\\xml\\output\\fractiontest.xml"));
	    Element qText=questionDoc.getRootElement().element("question-text");
	    List<BoundingBox> boxes = XmlUtility.split(qText);
	    System.out.println("boxes.size() "+ boxes.size());
	    for(BoundingBox bBox:boxes)
	    {
//	    	System.out.println(bBox);
	       	System.out.println(bBox +" "+ bBox.getAsNode().asXML().length());

	    }
	    LineMaker lMaker = new LineMaker();
	    Element eleSVG = lMaker.wrapLines(boxes);
	    System.out.println(eleSVG.asXML());
	    LineLayoutPresenter llp  = new LineLayoutPresenter(eleSVG);
	    llp.setX(0);
	    llp.setY(100);
	    List<Node>nodes = eleSVG.selectNodes("text");
	    Element lastLine = (Element)nodes.get(nodes.size()-1);
	    System.out.println(llp.layoutTextLines().asXML());  
	}
	
	
}
