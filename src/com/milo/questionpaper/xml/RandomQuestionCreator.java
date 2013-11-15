package com.milo.questionpaper.xml;

import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class RandomQuestionCreator implements java.io.FileFilter {

	private File quizFile;
	
	public File getQuizFile() {
		return quizFile;
	}

	public Element getQuestionContainer() {
		return questionContainer;
	}

	public boolean accept(File file) {
		// TODO Auto-generated method stub

		return (!file.isDirectory()) && file.getName().endsWith(".xml");
	}

	private List<Element> rootQuestionElements;
	private List<Element> generatedQuestions;
	private Element questionContainer;
	private File outputQuestionsDir;
	private Random random;

	public RandomQuestionCreator() throws Exception {
		random = new Random();
		ResourceBundle bundle = ResourceBundle.getBundle("equation");
		File templateDir = new File(bundle.getString("template.dir"));
		outputQuestionsDir = new File(bundle.getString("output.questions.dir"));
		rootQuestionElements = new ArrayList<Element>();
		this.questionContainer = DocumentHelper.createElement("question-paper");
		File[] files = templateDir.listFiles(this);
		SAXReader rdr = new SAXReader();
		Document doc = null;
		for (File file : files) {
			doc = rdr.read(file);
			this.rootQuestionElements.add(doc.getRootElement());
		}

	}

	
	public RandomQuestionCreator(File file) throws Exception {
		random = new Random();
		ResourceBundle bundle = ResourceBundle.getBundle("equation");
		File templateDir = new File(bundle.getString("template.dir"));
		outputQuestionsDir = new File(bundle.getString("output.questions.dir"));
		rootQuestionElements = new ArrayList<Element>();
		this.questionContainer = DocumentHelper.createElement("question-paper");
		File[] files = templateDir.listFiles(this);
		SAXReader rdr = new SAXReader();
		Document doc = null;
		doc = rdr.read(file);
		this.rootQuestionElements.add(doc.getRootElement());

	}
	public void generateQuestions(int count) throws Exception {
		createQuiz(count);
		saveQuestions("quiz");
	}

	private void createQuiz(int count) throws Exception {
		for (int i = 0; i < count; i++) {
			int idx = (int) (random.nextDouble() * (this.rootQuestionElements
					.size()));
			Element randomTemplate = this.rootQuestionElements.get(idx).createCopy();
			QuestionTemplateProcessor qtp = new QuestionTemplateProcessor(
					randomTemplate);
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
			this.questionContainer.add(qtsm.getQuestionElement());
			randomTemplate = null;
		}
	}

	public Document outputQuestions(int count) throws Exception {
		this.createQuiz(count);
		Document questionPaper = DocumentHelper
				.createDocument(questionContainer);
		return questionPaper;

	}

	private void saveQuestions(String quizName) throws Exception {
		Document questionPaper = DocumentHelper
				.createDocument(questionContainer);
		System.out.println(questionPaper.asXML());

		DateFormat dFormat = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss");

		quizFile = new File(outputQuestionsDir, quizName
				+ dFormat.format(new Date()) + ".xml");

		final OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		final XMLWriter out = new XMLWriter(new FileWriter(quizFile), format);
		out.write(questionPaper);
		
		out.close();

	}

}
