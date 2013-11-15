package com.milo.questionpaper.xml;

import java.awt.font.GlyphVector;
import java.util.ArrayList;

public class FractionUI
{
GlyphVector numerator;
GlyphVector line;
GlyphVector denominator;
GlyphVector gVBiggest;

private double numeratorOffset;
private double lineOffset;
private double denominatorOffset;


public FractionUI(ArrayList<GlyphVector> glyphVectors)
{
this(glyphVectors.get(0),glyphVectors.get(1),glyphVectors.get(2));
}

public FractionUI(GlyphVector numerator, GlyphVector line, GlyphVector denominator)
{
    this.numerator = numerator;
    this.line = line;
    this.denominator = denominator;
    gVBiggest=getWidestDetails();
    numeratorOffset=offSet(numerator);
    lineOffset = offSet(line);
    denominatorOffset = offSet(denominator);
}

private GlyphVector getWidestDetails()
{
GlyphVector gVBig =  returnBiggest(numerator,line);

return returnBiggest(gVBig,denominator);
}

private double offSet(GlyphVector gv)
{
    double offset = gVBiggest.getGlyphMetrics(0).getLSB()+
    gVBiggest.getVisualBounds().getWidth()/2 
    -
    (gv.getGlyphMetrics(0).getLSB() +
    gv.getVisualBounds().getWidth()/2.0);
    return offset;
}

private GlyphVector returnBiggest(GlyphVector gV1, GlyphVector gV2)
{
    if(
       (gV1.getGlyphMetrics(0).getLSB()+
        gV1.getGlyphMetrics(0).getRSB()+
        gV1.getVisualBounds().getWidth()) >
       (gV2.getGlyphMetrics(0).getLSB()+
        gV2.getGlyphMetrics(0).getRSB()+
        gV2.getVisualBounds().getWidth())    
        )
        return gV1;
    
    return gV2;
        
}

public double getDenominatorOffset()
{
    return denominatorOffset;
}

public double getLineOffset()
{
    return lineOffset;
}

public double getNumeratorOffset()
{
    return numeratorOffset;
}

}
