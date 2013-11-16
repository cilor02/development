package com.milo.questionpaper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import com.milo.questionpaper.xml.RandomQuestionCreator;
import com.milo.questionpaper.xml.SVGLayoutDAO;
import com.milo.questionpaper.xml.utils.QuizFormatter;

public class Punctuator {
public static void main(String[] args)throws Exception
{
 //File file = new File("tttttttt'kffk*kf");
	FileInputStream fis = new FileInputStream("C:\\punctuation\\punctuation.txt");
	BufferedReader dis = new BufferedReader( new InputStreamReader(fis));
    //BufferedWriter bffWriter = new BufferedWriter(new FileWriter("C:\\punctuation\\plain.txt"));
    PrintStream pStr = new PrintStream (new FileOutputStream("C:\\punctuation\\plain.txt"));
    String line = null;
	do
	{
    line=dis.readLine();
    if(line!=null)
    {
    	pStr.println(line.replaceAll("[^A-Za-z0-9\\s]", "").toLowerCase());
    }
	}while(line !=null);
	pStr.close();
 //String text = new String(
//		 "‘Calm down,’ he tells himself. He takes a few deep breaths, then scratches his bug bites, around but not on the itchiest places, taking care not to knock off any scabs: blood poisoning is the last thing he needs. Then he scans the ground below for wildlife: all quiet, no scales and tails. Left hand, right foot, right hand, left foot, he makes his way down from the tree. After brushing off the twigs and bark, he winds his dirty bedsheet around himself like a toga. He's hung his authentic-replica Red Sox baseball cap on a branch overnight for safekeeping; he checks inside it, flicks out a spider, puts it on."		 
//		 );
// System.out.println(text.replaceAll("[^A-Za-z0-9\\s]", ""));
}
}
