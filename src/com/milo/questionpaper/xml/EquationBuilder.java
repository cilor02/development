package com.milo.questionpaper.xml;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Element;
import org.dom4j.Node;

import com.milo.expression.ExpressionBuilder;
import com.milo.expression.ExpressionFactory;
import com.milo.questionpaper.equation.Expression;
import com.milo.questionpaper.equation.Term;

public class EquationBuilder {

private Expression equation;
private Element solution;
private Map<String, Term> mapVariables;
private Map<String, String> repeatmapVariables;


public Map<String, String> getRepeatmapVariables() {
	return repeatmapVariables;
}

public Double solve()
{
	return equation.value();
}

public void injectNamedParameters(Map<String, Double> namedParameters)
{
	Set<String> keys=mapVariables.keySet();
	for(String key:keys)
	{
		Double value = namedParameters.get(key);
		if(value!=null)
		{
		    mapVariables.get(key).setNumber(value);
		}
		else
		{
			mapVariables.get(key).setNumber(0);
		}
	}
	

}

public void injectNamedParametersAsStrings(Map<String, String> namedParameters, Map<String,String> repeatedVarsMap)
{
	Set<String> keys=mapVariables.keySet();
	for(String key:keys)
	{
		
		String stringValue = namedParameters.get(key);
		if(stringValue == null)
		{
			stringValue = namedParameters.get(repeatedVarsMap.get(key));
		}
		
		Double value = Double.valueOf(stringValue);
		if(value!=null)
		{
		    mapVariables.get(key).setNumber(value);
		}
		else
		{
			mapVariables.get(key).setNumber(0);
		}
	}
	

}

public EquationBuilder (Element solution)
{
	this.solution=solution;
	//builds solution
	equation = parse();
}

public Expression parse()
{
	mapVariables = new HashMap<String, Term>();
	repeatmapVariables= new HashMap <String, String>();
//	Element eleExpression = (Element)solution.selectSingleNode("phrase");
	Element eleExpression = solution.element("phrase");
	ExpressionBuilder expressionBuilder = ExpressionFactory.createAppropriateBuilder(eleExpression);
return expressionBuilder.resolve(eleExpression, mapVariables,repeatmapVariables);
}

public Set<String> listVariables()
{
	return mapVariables.keySet();
}

}
