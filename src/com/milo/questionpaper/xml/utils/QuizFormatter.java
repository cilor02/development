package com.milo.questionpaper.xml.utils;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.milo.questionpaper.xml.SVGLayoutDAO;

public class QuizFormatter {
	private int interQuestionDistance;
	private int pictureQuestionDistance;
	private int x;
	private int y;
	private Element quiz;
	private Document questionsTemplateDoc;

	public QuizFormatter(Element quiz, int x, int y) throws Exception {
		this.quiz = quiz;
		String questionGap = SVGLayoutDAO
				.getProperty("inter.question.distance");
		String pictureQuestionGap = SVGLayoutDAO.getProperty("picture.question.gap");
		String fileName = SVGLayoutDAO.getProperty("svg.question.template");
		//String xmlNS = SVGLayoutDAO.getProperty("xmlns");
		String xmlNSLink = SVGLayoutDAO.getProperty("xmlns.link");
		String svgDocHeight = SVGLayoutDAO.getProperty("svg.doc.height");
		String svgDocWidth  = SVGLayoutDAO.getProperty("svg.doc.width");
		SAXReader rdr = new SAXReader();
		// questionsTemplateDoc=rdr.read(new File(fileName));
		//Namespace nSpace = new Namespace("",xmlNS);
		questionsTemplateDoc = DocumentHelper.createDocument();
		questionsTemplateDoc.add(DocumentHelper.createElement("svg"));
		if(quiz.element("svg")!=null)
		{
			questionsTemplateDoc.getRootElement().add(quiz.element("svg"));
		}
		
		//questionsTemplateDoc.getRootElement().add(nSpace);
		questionsTemplateDoc.getRootElement().addAttribute("xmlns:xlink", xmlNSLink);
		questionsTemplateDoc.getRootElement().addAttribute("height", svgDocHeight);
		questionsTemplateDoc.getRootElement().addAttribute("width", svgDocWidth);
		
		interQuestionDistance = Integer.parseInt(questionGap);
		pictureQuestionDistance = Integer.parseInt(pictureQuestionGap);
		this.x=x;
		this.y=y;
		
	}

	public Document layQuestionsOut() {
		// get list of questions
		List<Element> questionElements = quiz.elements();

		int x1 = x;
		int y1 = y;
        int questionCount=0;
		for (Element questionElement : questionElements) {
			questionCount++;
			if (questionElement.element("drawing")!=null){
		    Element drwgElement = questionElement.element("drawing");
			Element diaDefs = DocumentHelper.createElement("defs");
			diaDefs.add(drwgElement.element("g").detach());
			
			String diaId = new StringBuilder("dia").append(questionCount).toString();
			diaDefs.element("g").addAttribute("id", diaId);
			Element diaUseDefs = DocumentHelper.createElement("use");
			diaUseDefs.addAttribute("xlink:href" ,"#"+diaId);
			diaUseDefs.addAttribute("x" ,"0");
			String vert = String.valueOf(y1);
			diaUseDefs.addAttribute("y" ,vert);

			questionsTemplateDoc.getRootElement().add(diaDefs);
			questionsTemplateDoc.getRootElement().add(diaUseDefs);
			y1 = y1 + pictureQuestionDistance + Integer.parseInt(drwgElement.attributeValue("height"));
			
			}
			List<BoundingBox> boxes = XmlUtility.split(questionElement);

			LineMaker lMaker = new LineMaker();
			Element eleSVG = lMaker.wrapLines(boxes);

			LineLayoutPresenter llp = new LineLayoutPresenter(eleSVG, x1, y1);

			// add wrapped lines to template document
			questionsTemplateDoc.getRootElement().add(llp.layoutTextLines());

			List<Node> nodes = eleSVG.selectNodes("text");
			Element lastLine = (Element) nodes.get(nodes.size() - 1);
			// extract the bottom line dimension of the last line o the question
			String lastLineY = lastLine.attributeValue("y");
			double lastLineInty = Double.valueOf(lastLineY);
			// calculate location of next question
			// height of line + inter question block distance
			y1 = (int) lastLineInty + llp.getLineHeight()
					+ this.interQuestionDistance;
			
			
			
		}
		return questionsTemplateDoc;
	}
}
