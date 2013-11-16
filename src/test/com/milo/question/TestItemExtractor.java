package test.com.milo.question;

import com.milo.questionpaper.xml.EquationItemExtractor;

public class TestItemExtractor {

	public static void main(String[]args) throws Exception
	{
		EquationItemExtractor eie= new EquationItemExtractor();
		for(int i=0;i<30;i++){
			System.out.println(eie.getItem("names"));
			System.out.println(eie.getItem("nouns"));
			
		}
		
	}
	
	
}
