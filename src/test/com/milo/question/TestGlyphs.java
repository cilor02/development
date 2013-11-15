package test.com.milo.question;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.io.File;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.milo.questionpaper.svg.GenerateSVG;
import com.milo.questionpaper.svg.text.FractionFormatter;
import com.milo.questionpaper.xml.EquationItemExtractor;
import com.milo.questionpaper.xml.QuestionTemplateProcessor;
import com.milo.questionpaper.xml.QuestionTemplateSubstitutionManager;

public class TestGlyphs {

	public static void main(String[]args) throws Exception
	{
		
        FractionFormatter fracFmttr = new FractionFormatter();
        Font f = fracFmttr.getF();
        FontRenderContext frc = fracFmttr.getG().getFontRenderContext();
        String s = "here i go! I  fly";
        GlyphVector glyfVector = f.createGlyphVector(frc,s);
        
        System.out.println("num of glyphs "+ glyfVector.getNumGlyphs());
        int glyfcount=glyfVector.getNumGlyphs();
        for(int i =0;i<glyfcount;i++)
        {
        print("width "+glyfVector.getGlyphVisualBounds(i).getBounds2D().getWidth());
        print("char s "+s.charAt(i));
        }

	}

	private static void print(Object o) {
		System.out.println(o);
	}
	
}
