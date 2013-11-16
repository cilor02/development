package test.com.milo.question;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.io.File;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.milo.questionpaper.svg.GenerateSVG;
import com.milo.questionpaper.svg.text.FractionFormatter;
import com.milo.questionpaper.xml.EquationItemExtractor;
import com.milo.questionpaper.xml.QuestionTemplateProcessor;
import com.milo.questionpaper.xml.QuestionTemplateSubstitutionManager;

public class TestFontLengths {

	public static void main(String[]args) throws Exception
	{
		
        FractionFormatter fracFmttr = new FractionFormatter();
        String oneLine=new String("3__23");
        
//        System.out.println(fracFmttr.getFractionFontMetrix().getStringBounds(oneLine,0, 1, fracFmttr.getG()).getWidth());
//        System.out.println(fracFmttr.getFractionFontMetrix().getStringBounds(oneLine,1, 3, fracFmttr.getG()).getWidth());
//        System.out.println(fracFmttr.getFractionFontMetrix().getStringBounds(oneLine,3, 5, fracFmttr.getG()).getWidth());
        
        displayLengths(fracFmttr, "3");
        displayLengths(fracFmttr, "__");
        displayLengths(fracFmttr, "23");
        displayLengths(fracFmttr, "_");
	}

	private static void displayLengths(FractionFormatter fracFmttr,
			String oneLine) {
		System.out.println(fracFmttr.getFractionFontMetrix().getStringBounds(oneLine, fracFmttr.getG()).getBounds2D().getWidth());
        Font f= fracFmttr.getF();
        System.out.println("f "+f);
        FontRenderContext fRndrCtxt = fracFmttr.getG().getFontRenderContext();
        TextLayout txtLyout= new TextLayout(oneLine,f,fRndrCtxt);
        System.out.println("len 1 rect2d: " +txtLyout.getBounds().getWidth());
        System.out.println("len 1: " + txtLyout.getAdvance());
        System.out.println("len 2x: " + txtLyout.getVisibleAdvance());
	}
	
}
