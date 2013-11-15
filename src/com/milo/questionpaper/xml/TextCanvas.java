package com.milo.questionpaper.xml;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class TextCanvas extends Canvas
{
private ArrayList<TextLayoutPrintInfo>printInfo;
private double x;
private double y;
public TextCanvas(ArrayList<TextLayoutPrintInfo> printInfo, double x, double y)
{
    this.x=x;
    this.y=y;
    this.printInfo = printInfo;
}

@Override
public void paint(Graphics g)
{


        printInfo.get(0).getLayout().draw((Graphics2D)g, (float)(x + printInfo.get(0).getOffset()), (float)y);
        float descent  = printInfo.get(0).getLayout().getDescent();
        printInfo.get(1).getLayout().draw((Graphics2D)g, (float)(x+printInfo.get(1).getOffset()), (float)y );
        float denomHeight = (float)printInfo.get(2).getLayout().getBounds().getHeight();
        printInfo.get(2).getLayout().draw((Graphics2D)g, (float)(x+printInfo.get(2).getOffset()), (float)y+descent+denomHeight);
}
}
