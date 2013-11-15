package test.com.milo.question;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Namespace;
import org.dom4j.dom.DOMDocument;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.milo.questionpaper.xml.RandomQuestionCreator;
import com.milo.questionpaper.xml.SVGLayoutDAO;
import com.milo.questionpaper.xml.utils.QuizFormatter;

public class TestRandomQuestionCreator {
public static void main(String[] args)throws Exception
{
	RandomQuestionCreator randQCreator=new RandomQuestionCreator();
	randQCreator.generateQuestions(15);
	SAXReader rdr = new SAXReader();
	
	Document doc = rdr.read(randQCreator.getQuizFile());

	QuizFormatter quizFormatter = new QuizFormatter(doc.getRootElement(),80,150);
	
//	System.out.println(quizFormatter.layQuestionsOut().asXML());
	
	//String svgAsString = quizFormatter.layQuestionsOut().asXML();

//	Document questionPaper = rdr.read(new InputSource(new StringReader(svgAsString)));
	

	String xmlNS = SVGLayoutDAO.getProperty("xmlns");
	
	//Namespace nSpace = new Namespace("",xmlNS);
	//questionPaper.getRootElement().add(nSpace);
//System.out.println(questionPaper.asXML());


//DateFormat dFormat = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss");

//DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
//String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
//org.w3c.dom.Document w3cdoc = impl.createDocument(svgNS, "svg", null);

Document questionPaper = quizFormatter.layQuestionsOut();
InputSource xmlSrc=new InputSource(new StringReader(questionPaper.asXML()));
org.w3c.dom.Document docw3= DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlSrc);
//NodeList nodeList=docw3.getDocumentElement().getChildNodes();

//int nodeCount = nodeList.getLength();
//DocumentFragment docFrag = docw3.createDocumentFragment();
//for (int i=0;i<nodeCount;i++)
//{
//	docFrag.appendChild(nodeList.item(i));
//}
//w3cdoc.getDocumentElement().appendChild(docFrag);

//String svgDocHeight = SVGLayoutDAO.getProperty("svg.doc.height");
//String svgDocWidth  = SVGLayoutDAO.getProperty("svg.doc.width");

//w3cdoc.getDocumentElement().setAttribute("height", svgDocHeight);
//w3cdoc.getDocumentElement().setAttribute("width", svgDocWidth);

//Source source = new DOMSource(w3cdoc);
//questionPaper.getRootElement().addAttribute("xmlns",  xmlNS);
//org.dom4j.dom.DOMDocument ddoc;
//org.w3c.dom.Document ddoc = org.dom4j.dom.DOMNodeHelper.asDOMDocument(questionPaper);
docw3.getDocumentElement().setAttribute("xmlns", xmlNS);
final File quizFile = new File("C:/svg/factors-paper.svg");
Source source = new DOMSource(docw3);
Result result = new StreamResult(new FileOutputStream (quizFile));
Transformer xformer = TransformerFactory.newInstance().newTransformer();
xformer.transform(source, result);
//final OutputFormat format =  OutputFormat.createCompactFormat();
//format.setEncoding("utf-8");

//final XMLWriter out = new XMLWriter(new FileWriter(quizFile),format);
//out.write(questionPaper);
//out.close();
	
//	
//	QuizFormatter quizFormatter1 = new QuizFormatter(randQCreator.generateQuestions(20),0,50);

}
}
