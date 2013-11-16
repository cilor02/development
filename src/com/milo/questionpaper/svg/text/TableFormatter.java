package com.milo.questionpaper.svg.text;

import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.util.List;
import java.util.ResourceBundle;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class TableFormatter extends TextFormatter {

	@Override
	public Element convertToSVG(Element e) {
		// TODO Auto-generated method stub
		ResourceBundle bundle = ResourceBundle.getBundle("presentation");
        int rowHeight = Integer.parseInt( bundle.getString("table.row.height"));
		int colWidth =  Integer.parseInt( bundle.getString("table.col.width"));
		int tableLeft =  Integer.parseInt( bundle.getString("table.left.side"));


	    Element gElement = DocumentHelper.createElement("table");
		
		List<Element> rows   = e.elements("row");
        
        
        int rowPos = 0; 
		for(Element eleRow : rows)
		{
			Element svgText = DocumentHelper.createElement("text");
			svgText.addAttribute("x", String.valueOf(tableLeft));
			svgText.addAttribute("y", String.valueOf(rowPos * rowHeight));
			
			List<Element> columns = eleRow.elements("column");
			int colPos = tableLeft;
			for(Element column :columns)
			{			
				Element svgTspan = DocumentHelper.createElement("tspan");
				svgTspan.addAttribute("x", String.valueOf(colPos));
				svgTspan.addAttribute("dy", String.valueOf(colPos==tableLeft?rowHeight:0));
				svgTspan.addText(column.getText());
				svgText.add(svgTspan);
				colPos += colWidth; 				
			}
			rowPos++;
			gElement.add(svgText);
			
		}

	    
		return gElement;
	}
	
	
	public Element createSVGTspan(String val, double textdx,double textdy)
	{
		Element tspan = DocumentHelper.createElement("tspan");
		tspan.addAttribute("dx",Double.toString(textdx));
		tspan.addAttribute("dy",Double.toString(textdy));
		tspan.addAttribute("font-size", String.valueOf(getFractionFontSize()));
		return tspan;
	}

	@Override
	public int getFontSize() {
		// TODO Auto-generated method stub
		return super.getFontSize();
	}

	@Override
	public int getFractionFontSize() {
		// TODO Auto-generated method stub
		return super.getFractionFontSize();
	}

	@Override
	public double getLength(String str) {
		// TODO Auto-generated method stub
		return super.getLength(str);
	}

	@Override
	public void setFontSize(int fontSize) {
		// TODO Auto-generated method stub
		super.setFontSize(fontSize);
	}

	@Override
	public void setFractionFontSize(int fractionFontSize) {
		// TODO Auto-generated method stub
		super.setFractionFontSize(fractionFontSize);
	}

}
