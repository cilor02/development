package test.com.milo.question;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import com.milo.questionpaper.equation.Expression;
import com.milo.questionpaper.xml.EquationBuilder;

public class TestEquationBuilder {
	

	public static void main(String [] args)throws Exception
	{
		ResourceBundle bundle = ResourceBundle.getBundle("equation");
		String xmlLocation = bundle.getString("xml.location");
		SAXReader reader = new SAXReader();
		Document equDoc =  reader.read(new File(xmlLocation));
		
		EquationBuilder equationBuilder = new EquationBuilder(equDoc.getRootElement());
		//Expression expression = equationBuilder.parse();
		
		Map<String, Double> values = new HashMap<String, Double>();
		values.put("x", 45.0);
		values.put("y",35.0);
		values.put("z", 25.0);
		equationBuilder.injectNamedParameters(values);
		Double solution = equationBuilder.solve();
		System.out.println("solution :-" + solution);
		
		//Double answer = expression.value();
		
		
		
	}
}
