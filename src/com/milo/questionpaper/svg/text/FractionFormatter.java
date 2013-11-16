package com.milo.questionpaper.svg.text;

import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class FractionFormatter extends TextFormatter {

	@Override
	public Element convertToSVG(Element e) {
		// TODO Auto-generated method stub
		String numerator   = e.element("numerator").getText();
		String denominator = e.element("denominator").getText();
		
//		double nLen=this.fractionFontMetrix.stringWidth(numerator);
//		double dLen=this.fractionFontMetrix.stringWidth(denominator);
		TextLayout txtLyout=null;
	
        FontRenderContext fRndrCtxt = getG().getFontRenderContext();
        txtLyout=new TextLayout(numerator,f,fRndrCtxt);
		double nLen=txtLyout.getBounds().getWidth();
        txtLyout=new TextLayout(denominator,f,fRndrCtxt);
		double dLen=txtLyout.getBounds().getWidth(); 
		
		//line
		StringBuffer sbfr=new StringBuffer();
		for(int i =0;i<denominator.length();i++)
		{
			sbfr.append('_');
		}
		
        txtLyout=new TextLayout(sbfr.toString(),f,fRndrCtxt);
		double lineLen=txtLyout.getBounds().getWidth();
		
		double ndx = (lineLen - nLen)/2;
		double ndy=0;
		double linedx = -(lineLen + nLen)/2;
		double linedy = 0;
		double ddx   =  -(lineLen+dLen)/2;
		double ddy = fractionFontMetrix.getAscent();
		//numerator
		Element nTspan=this.createSVGTspan(numerator, ndx, ndy);
		 nTspan.setText(numerator);

		Element lineTspan=this.createSVGTspan(sbfr.toString(), linedx, linedy);
		lineTspan.setText(sbfr.toString());
		
		System.out.println(fractionFontMetrix.getStringBounds(sbfr.toString(),g).getWidth());
		
		//denominator
		Element dTspan=this.createSVGTspan(denominator, ddx, ddy);
		dTspan.setText(denominator);
		Element fractionElement=DocumentHelper.createElement("fraction");
		fractionElement.add(nTspan);
		fractionElement.add(lineTspan);
		fractionElement.add(dTspan);
		return fractionElement;
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
