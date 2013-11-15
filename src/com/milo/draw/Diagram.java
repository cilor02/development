package com.milo.draw;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.w3c.dom.DOMImplementation;

import com.milo.geom.Axis;
import com.milo.geom.BallChain;
import com.milo.geom.BlockedShape;
import com.milo.geom.Circle;
import com.milo.geom.EnclosingRectangle;
import com.milo.geom.Grid;
import com.milo.geom.ReflectionLine;
import com.milo.geom.TransformShape;
import com.milo.geom.Triangle;
import com.milo.questionpaper.xml.QuestionTemplateProcessor;

public class Diagram {

	private SVGGraphics2D svgG;
	private double pixelsPerUnit;
	private int diaHeight;
	private int diaWidth;
	private Grid grid;
	private Axis axis;
	private ReflectionLine reflectionLine;
	private QuestionTemplateProcessor qtp;
	
	public Diagram (QuestionTemplateProcessor qtp)
	{
		this.qtp = qtp;
	}
	
	public Diagram ()
	{
	
	}
	public Element parse (Element diagram) throws Exception
	{
	    
	    DOMImplementation domImpl =
            GenericDOMImplementation.getDOMImplementation();

        // Create an instance of org.w3c.dom.Document.
        String svgNS = "http://www.w3.org/2000/svg";
        
        org.w3c.dom.Document document = domImpl.createDocument(svgNS, "svg", null);

        // Create an instance of the SVG Generator.
        svgG = new SVGGraphics2D(document);
        svgG.setColor(Color.BLUE);
	    
        String numericAtt = diagram.attributeValue("pixelperunit");
        String gridAtt = diagram.attributeValue("grid");

                
        this.pixelsPerUnit = Double.valueOf(numericAtt);
		this.drawBorder(diagram);
        if(gridAtt!=null)
        {
        	grid = new Grid(Integer.valueOf(numericAtt) ,diaHeight, diaWidth);
        	grid.draw(svgG);
        }
        
        String axisAtt = diagram.attributeValue("axis");
        if(axisAtt!=null)
        {
        	axis = new Axis(Integer.valueOf(numericAtt) ,diaHeight, diaWidth);
        	axis.draw(svgG);
        }
        
        String reflectAtt = diagram.attributeValue("reflection");
        if(reflectAtt!=null)
        {
        	reflectionLine = new ReflectionLine(Integer.valueOf(numericAtt) ,diaHeight, diaWidth);
        	reflectionLine.draw(svgG);
        }
        
		List<Element> items = diagram.elements();
		for(Element component : items)
		{
		if (component.getName().equals("circle"))
		{
			Double radius = scaleAttribute(component,"radius");
			Double cx = scaleAttribute(component,"cx");
			Double cy = scaleAttribute(component,"cy");
			
			Circle circle = new Circle(new Point2D.Double(cx,cy),radius);
			circle.draw(svgG);
		}
		
		if (component.getName().equals("transformshape"))
		{
			
			Double x = scaleAttribute(component,"x");
			Double y = scaleAttribute(component,"y");
			
			TransformShape transformShape = new TransformShape(Integer.valueOf(numericAtt),diaHeight, diaWidth,x,y);
			transformShape.draw(svgG);

		}
		if (component.getName().equals("blockshape"))
		{
			
			Double x = scaleAttribute(component,"x");
			Double y = scaleAttribute(component,"y");
			
			BlockedShape blockedShape = new BlockedShape(Integer.valueOf(numericAtt),diaHeight, diaWidth,x,y);
			blockedShape.draw(svgG);

		}
		
		if (component.getName().equals("triangle"))
		{
			Double side = scaleAttribute(component,"biggest-side");
			// angles are not scaled
			Double angle1 = unScaledAttribute(component,"angle1");
			Double angle2 = unScaledAttribute(component,"angle2");
            String unknown = component.attributeValue("unknown");
			Double ptX = scaleAttribute(component,"ptX");
			Double ptY = scaleAttribute(component,"ptY");

			Triangle triangle = new Triangle(new Point2D.Double(ptX,ptY),angle1,angle2,unknown,side);
	        triangle.draw(svgG);
		}
		
		if (component.getName().equals("rectangle"))
		{
			Double topLeftX = scaleAttribute(component,"top-leftX");
			Double topLeftY = scaleAttribute(component,"top-leftY");
			Double height = scaleAttribute(component,"height");
			Double width = scaleAttribute(component,"width");
			Rectangle2D rect = new Rectangle2D.Double(topLeftX,topLeftY,width,height);
			svgG.draw(rect);
		}

		if (component.getName().equals("enclosing-rectangles"))
		{
			Double topLeftX = scaleAttribute(component,"top-leftX");
			Double topLeftY = scaleAttribute(component,"top-leftY");
			Double height = scaleAttribute(component,"height");
			Double width = scaleAttribute(component,"width");
			int numberRects =  this.unScaledAttribute(component, "number-rects").intValue();
			Point2D topLeft = new Point2D.Double(topLeftX, topLeftY);
			EnclosingRectangle enclosingRectangle = new EnclosingRectangle(Double.valueOf(this.pixelsPerUnit), topLeft, numberRects, height.intValue(), width.intValue());
			enclosingRectangle.draw(svgG);
		}
		

		if (component.getName().equals("ballchain"))
		{
			Double topLeftX = scaleAttribute(component,"top-leftX");
			Double topLeftY = scaleAttribute(component,"top-leftY");
			int radius = scaleAttribute(component,"radius").intValue();
			int numBlack = unScaledAttribute(component,"numBlack").intValue();
			int numRed = unScaledAttribute(component,"numRed").intValue();
			Point2D topLeft = new Point2D.Double(topLeftX, topLeftY);
			BallChain ballChain = new BallChain( topLeft, numBlack, numRed, radius);
			ballChain.draw(svgG);
		}
		
		
		}
		
		
//		  PipedInputStream inPipe = new PipedInputStream();
//		  PipedOutputStream outPipe = new PipedOutputStream(inPipe);		  
//          final Writer out = new OutputStreamWriter(outPipe, "UTF-8");
//          final XMLWriter xmlWriter = new XMLWriter(out);
//          new Thread(
//				  new Runnable(){
//		      public void run(){
//		    	try{  
//		        svgG.stream(out,false);
//		    	}
//		    	catch (Exception e)
//		    	{
//		    		
//		    	}
//		    	
//		      }
//		    }
//		  ).start();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		Writer out = new OutputStreamWriter(outputStream);
		svgG.stream(out,false);
        
        SAXReader reader = new SAXReader();
        Document outDoc = reader.read(new ByteArrayInputStream(outputStream.toByteArray()));
        return outDoc.getRootElement();
        
	}
	
	public Element parseSVG (Element diagram) throws Exception
	{
	    
	    DOMImplementation domImpl =
            GenericDOMImplementation.getDOMImplementation();

        // Create an instance of org.w3c.dom.Document.
       String svgNS = "http://www.w3.org/2000/svg";
        //String svgNS = "http://127.0.0.1/2000/svg";
        org.w3c.dom.Document document = domImpl.createDocument(svgNS, "svg", null);

        // Create an instance of the SVG Generator.
        svgG = new SVGGraphics2D(document);
        svgG.setColor(Color.BLUE);
	    
        String numericAtt = diagram.attributeValue("pixelperunit");
                
        this.pixelsPerUnit = Double.valueOf(numericAtt);
		this.drawBorder(diagram);
		List<Element> items = diagram.elements();
		for(Element component : items)
		{
		if (component.getName().equals("circle"))
		{
			Double radius = scaleAttribute(component,"radius");
			Double cx = scaleAttribute(component,"cx");
			Double cy = scaleAttribute(component,"cy");
			
			Circle circle = new Circle(new Point2D.Double(cx,cy),radius);
			circle.draw(svgG);
		}
		
		
		if (component.getName().equals("rectangle"))
		{
			Double topLeftX = scaleAttribute(component,"top-leftX");
			Double topLeftY = scaleAttribute(component,"top-leftY");
			Double height = scaleAttribute(component,"height");
			Double width = scaleAttribute(component,"width");
			Rectangle2D rect = new Rectangle2D.Double(topLeftX,topLeftY,width,height);
			svgG.draw(rect);
		}

		
		}
		
		
//		  PipedInputStream inPipe = new PipedInputStream();
//		  PipedOutputStream outPipe = new PipedOutputStream(inPipe);		  
//          final Writer out = new OutputStreamWriter(outPipe, "UTF-8");
//          final XMLWriter xmlWriter = new XMLWriter(out);
//          new Thread(
//				  new Runnable(){
//		      public void run(){
//		    	try{  
//		        svgG.stream(out,false);
//		    	}
//		    	catch (Exception e)
//		    	{
//		    		
//		    	}
//		    	
//		      }
//		    }
//		  ).start();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		Writer out = new OutputStreamWriter(outputStream);
		svgG.stream(out,false);
        
        SAXReader reader = new SAXReader();
        Document outDoc = reader.read(new ByteArrayInputStream(outputStream.toByteArray()));
        Element svgDiagram = outDoc.getRootElement();
    	Element eleDrawing = DocumentHelper.createElement("drawing");
    	
    	
    	eleDrawing.add(svgDiagram.element("g"));
        return eleDrawing;
        
	}
	
	
	private void drawBorder(Element diagram)
	{

		Double height = scaleAttribute(diagram,"height");
		Double width = scaleAttribute(diagram,"width");
		
		
		Rectangle2D rect = new Rectangle2D.Double(0,0,width,height);
		svgG.draw(rect);
        svgG.setColor(Color.GREEN);
        
        diaHeight = height.intValue();
        diaWidth = width.intValue();
        svgG.clipRect(0,0,diaWidth,diaHeight);
		
	}
	public int getDiaHeight() {
		return diaHeight;
	}

	public void setDiaHeight(int diaHeight) {
		this.diaHeight = diaHeight;
	}

	public int getDiaWidth() {
		return diaWidth;
	}

	public void setDiaWidth(int diaWidth) {
		this.diaWidth = diaWidth;
	}

	private Double scaleAttribute(Element component, String attName) {
		String attrString = component.attributeValue(attName);
		if(attrString.startsWith("@ref:"))
		{
			String attrKey = attrString.split("@ref:")[1];
			String attrValue = (String)qtp.getVarMap().get(attrKey);
			if(attrValue == null)
			{
				attrValue =	(String)qtp.getDerivedVarMap().get(attrKey);
			}
			return Double.valueOf(attrValue)* this.pixelsPerUnit;
		}
		return Double.valueOf(attrString)* this.pixelsPerUnit;
	}
	
	private Double unScaledAttribute(Element component, String attName) {
		String attrString = component.attributeValue(attName);
		if(attrString.startsWith("@ref:"))
		{
			String attrKey = attrString.split("@ref:")[1];
			String attrValue = (String)qtp.getVarMap().get(attrKey);
			if(attrValue == null)
			{
				attrValue =	(String)qtp.getDerivedVarMap().get(attrKey);
			}
			return Double.valueOf(attrValue);
		}
		return Double.valueOf(attrString);
	}
public static void main (String[] args) throws Exception
{
	SAXReader reader = new SAXReader();
    Document outDoc = reader.read("C:\\circle.xml");
    Diagram dia = new Diagram ();
    Element svg = dia.parse(outDoc.getRootElement());
    System.out.println(svg.asXML());
    
}
	
}
