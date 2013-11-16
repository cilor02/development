package test.com.milo.question;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
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

public class TestXMLOutput {
public static void main(String[] args)throws Exception
{

	SAXReader rdr = new SAXReader();
	
	Document doc = rdr.read(new File("C:/svg/quiz2.svg"));
InputSource xmlSrc=new InputSource(new StringReader(doc.asXML()));
org.w3c.dom.Document docw3= DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlSrc);
//System.out.println()
final File quizFile = new File("C:/svg/quiz8.svg");
Source source = new DOMSource(docw3);
Result result = new StreamResult(new FileOutputStream(quizFile));
Transformer xformer = TransformerFactory.newInstance().newTransformer();
xformer.transform(source, result);

}
}
