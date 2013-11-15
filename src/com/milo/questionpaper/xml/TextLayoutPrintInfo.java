package com.milo.questionpaper.xml;

import java.awt.font.TextLayout;

public class TextLayoutPrintInfo
{
private TextLayout layout;
private double offset;
public TextLayoutPrintInfo(TextLayout layout, double offset)
{
    this.layout = layout;
    this.offset = offset;
}
public TextLayout getLayout()
{
    return layout;
}
public double getOffset()
{
    return offset;
}

}
