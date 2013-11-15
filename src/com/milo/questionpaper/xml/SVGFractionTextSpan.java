package com.milo.questionpaper.xml;

import java.awt.font.GlyphVector;
import java.util.List;

public class SVGFractionTextSpan
{
private List<TextLayoutPrintInfo>layoutInfo;
private List<GlyphVector> glyphVectors;
private double dxNum;
private double dyNum;
private double dxLine;
private double dyLine;
private double dxDen;
private double dyDen;

public SVGFractionTextSpan(List<TextLayoutPrintInfo> layoutInfo, List<GlyphVector> glyphVectors)
{
    this.layoutInfo = layoutInfo;
    this.glyphVectors = glyphVectors;
    calculateOffsets();
}


public void calculateOffsets()
{
//Numerator

    //Line w/2 + rsb(num) + w/2 + lsb(line)
    GlyphVector numGlyph =  glyphVectors.get(0);
    int numOfGlyphsInNum =  numGlyph.getNumGlyphs();
    double rsbNum=numGlyph.getGlyphMetrics(numOfGlyphsInNum -1).getRSB();
    double widthNum = layoutInfo.get(0).getLayout().getBounds().getWidth();
    double numDesc  = layoutInfo.get(0).getLayout().getDescent();
    
    GlyphVector lineGlyph =  glyphVectors.get(1);
    int numOfGlyphsInLine =  lineGlyph.getNumGlyphs();
    double rsbLine=lineGlyph.getGlyphMetrics(numOfGlyphsInLine -1).getRSB();
    double lsbLine=lineGlyph.getGlyphMetrics(numOfGlyphsInLine -1).getLSB();
    double widthLine = layoutInfo.get(1).getLayout().getBounds().getWidth();
    
    GlyphVector denGlyph =  glyphVectors.get(2);
    int numOfGlyphsInDen =  denGlyph.getNumGlyphs();
    double rsbDen=denGlyph.getGlyphMetrics(numOfGlyphsInDen -1).getRSB();
    double lsbDen=denGlyph.getGlyphMetrics(numOfGlyphsInDen -1).getLSB();
    double widthDen = layoutInfo.get(2).getLayout().getBounds().getWidth();
    
    
    dxNum = layoutInfo.get(0).getOffset();
    dyNum=0;
    
    dxLine = -1 * (widthNum/2 + rsbNum + widthLine/2 + lsbLine);
    dyLine = 0;
    
    dxDen = -1 * (widthDen/2 + rsbLine + widthLine/2 + lsbDen);
    //account for descending numerator
    dyDen = denGlyph.getVisualBounds().getHeight() + lineGlyph.getVisualBounds().getHeight()+numDesc;
    //revisit dyNum to centre vertically the fraction glyph
    dyNum = -1 * dyDen;
}


public double getDxDen()
{
    return dxDen;
}


public void setDxDen(double dxDen)
{
    this.dxDen = dxDen;
}


public double getDxLine()
{
    return dxLine;
}


public void setDxLine(double dxLine)
{
    this.dxLine = dxLine;
}


public double getDxNum()
{
    return dxNum;
}


public void setDxNum(double dxNum)
{
    this.dxNum = dxNum;
}


public double getDyDen()
{
    return dyDen;
}


public void setDyDen(double dyDen)
{
    this.dyDen = dyDen;
}


public double getDyLine()
{
    return dyLine;
}


public void setDyLine(double dyLine)
{
    this.dyLine = dyLine;
}


public double getDyNum()
{
    return dyNum;
}


public void setDyNum(double dyNum)
{
    this.dyNum = dyNum;
}
}
