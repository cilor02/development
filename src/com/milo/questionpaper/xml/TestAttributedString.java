package com.milo.questionpaper.xml;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphMetrics;
import java.awt.font.GlyphVector;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestAttributedString
{
    static ArrayList<TextLayout> layouts;
    static ArrayList<GlyphVector> fractionParts;
    static ArrayList<TextLayoutPrintInfo>layoutInfo;
public static  void main(String []args)
{
layouts       =  new ArrayList();
fractionParts =  new ArrayList();   
layoutInfo    =  new ArrayList();
    String s = "2__13"; 	
    
    
//    AttributedString as1 = new AttributedString(s.substring(0,1));
    AttributedString as1 = new AttributedString("1");
    double width1=breakString(as1);
    print(width1);
    print(testStringMetrix(as1));
//    AttributedString as2 = new AttributedString(s.substring(1,3));
    AttributedString as2 = new AttributedString("___");
    double width2=breakString(as2);
    print(width2); 
    print(testStringMetrix(as2));
//    AttributedString as3 = new AttributedString(s.substring(3,5));
    AttributedString as3 = new AttributedString("809");
    double width3=breakString(as3);
    print(width3);  
    print(testStringMetrix(as3));

    FractionUI fractionUI = new FractionUI(fractionParts);
layoutInfo.add(new TextLayoutPrintInfo(layouts.get(0),fractionUI.getNumeratorOffset()));
layoutInfo.add(new TextLayoutPrintInfo(layouts.get(1),fractionUI.getLineOffset()));
layoutInfo.add(new TextLayoutPrintInfo(layouts.get(2),fractionUI.getDenominatorOffset()));
    

SVGFractionTextSpan txtspn = new SVGFractionTextSpan(layoutInfo,fractionParts);

print("numX:" +  txtspn.getDxNum());
print("numY:"+txtspn.getDyNum());

print("LineX:"+txtspn.getDxLine());
print("LineY:"+txtspn.getDyLine());

print("den X"+txtspn.getDxDen());
print("den Y "+txtspn.getDyDen());

    Frame frame = new Frame("test"); 
    TextCanvas textCanvas = new TextCanvas(layoutInfo,100,100);
    textCanvas.setSize(new Dimension(400,500));
    frame.add(textCanvas);
    frame.setVisible(true);
//    frame.add(layouts.get(index))
    
}

private static double testStringMetrix(AttributedString as)
{
    BufferedImage im = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
    Graphics2D g = (Graphics2D)im.getGraphics();

   

    Map<Attribute, Object> attrMap = new HashMap<Attribute,Object>();
    attrMap.put(TextAttribute.FAMILY, "Times New Roman");
    attrMap.put(TextAttribute.SIZE, 24.0f);
    
    as.addAttribute(TextAttribute.FAMILY, "Times New Roman");
    as.addAttribute(TextAttribute.SIZE, 24.0f);
    
    Font font = new Font(attrMap);
    g.setFont(font);
    FontRenderContext frc = g.getFontRenderContext();
    
    
    TextLayout txtLyout = new TextLayout(as.getIterator(),frc);
    layouts.add(txtLyout);
    
//    print(txtLyout.getAdvance());
    
//    as.addAttribute(TextAttribute.FAMILY, "Arial", 12,18);
    
print("txtLayout : " +txtLyout.getBounds());
    
    return txtLyout.getAdvance();
}

private static double breakString(AttributedString as)
{
    BufferedImage im = new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
    Graphics2D g = (Graphics2D)im.getGraphics();

   

    Map<Attribute, Object> attrMap = new HashMap<Attribute,Object>();
    attrMap.put(TextAttribute.FAMILY, "Times New Roman");
    attrMap.put(TextAttribute.SIZE, 24.0f);
    
    as.addAttribute(TextAttribute.FAMILY, "Times New Roman");
    as.addAttribute(TextAttribute.SIZE, 24.0f);
    
    Font font = new Font(attrMap);
    g.setFont(font);
    FontRenderContext frc = g.getFontRenderContext();
//    as.addAttribute(TextAttribute.FAMILY, "Arial", 12,18);
    
    AttributedCharacterIterator aci = as.getIterator();
    

    GlyphVector glyphVector = font.createGlyphVector(frc,aci);
    print(glyphVector.getLogicalBounds());
    print(glyphVector.getVisualBounds());
    double w=0;
    for(int i = 0;i<glyphVector.getNumGlyphs();i++)
    {
        w+=glyphVector.getGlyphMetrics(i).getAdvanceX();
        print(glyphVector.getGlyphPosition(i)+ " lsb "+ glyphVector.getGlyphMetrics(i).getLSB()
            +  " rsb "+ glyphVector.getGlyphMetrics(i).getRSB());

    }

    fractionParts.add(glyphVector);
    return glyphVector.getLogicalBounds().getWidth();
}

private static void print(Object o)
{
    System.out.println( o);
}
}
