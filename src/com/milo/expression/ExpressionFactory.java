package com.milo.expression;

import org.dom4j.Element;

import com.milo.questionpaper.equation.AddExpression;
import com.milo.questionpaper.equation.ConstantTerm;
import com.milo.questionpaper.equation.DivideExpression;
import com.milo.questionpaper.equation.EqualExpression;
import com.milo.questionpaper.equation.Expression;
import com.milo.questionpaper.equation.FactorOfExpression;
import com.milo.questionpaper.equation.GreaterThanExpression;
import com.milo.questionpaper.equation.HCFExpression;
import com.milo.questionpaper.equation.LessThanExpression;
import com.milo.questionpaper.equation.MinusExpression;
import com.milo.questionpaper.equation.MultipleOfExpression;
import com.milo.questionpaper.equation.MultiplyExpression;
import com.milo.questionpaper.equation.NotExpression;
import com.milo.questionpaper.equation.PowerExpression;
import com.milo.questionpaper.equation.Term;
import com.milo.questionpaper.equation.TrueTerm;

public class ExpressionFactory {
public static Expression createAppropriateObj(Element e)
{
	
	if(e.getName().equals("phrase"))
	{
		
		String operator = e.attribute("operator").getText();
		
		if(operator.equals("+"))
		{
			return new AddExpression();
		}
		
		if(operator.equals("*"))
		{
			return new MultiplyExpression();
		}
		
		if(operator.equals("-"))
		{
			return new MinusExpression();
		}
		
		if(operator.equals("/"))
		{
			return new DivideExpression();
		}
		
		if(operator.equals("gt"))
		{
			return new GreaterThanExpression();
		}
		
		
		if(operator.equals("hcf"))
		{
			return new HCFExpression();
		}
		
		if(operator.equals("not"))
		{
			return new NotExpression();
		}
		
		if(operator.equals("="))
		{
			return new EqualExpression();
		}
		
		if(operator.equals("lt"))
		{
			return new LessThanExpression();
		}
		
		if(operator.equals("factorOf"))
		{
			return new FactorOfExpression();
		}
		
		if(operator.equals("multipleOf"))
		{
			return new MultipleOfExpression();
		}
		
		if(operator.equals("power"))
		{
			return new PowerExpression();
		}
	}
	
	
	if(e.getName().equals("term"))
	{
		Term term = new Term();
		term.setVarName(e.attributeValue("varname"));
		return term;
	}	
	
	
	if(e.getName().equals("constant"))
	{
		ConstantTerm constantTerm = new ConstantTerm();
		double number = Double.valueOf(e.attributeValue("value"));
		constantTerm.setNumber(number);
		return constantTerm;
	}
	
	if(e.getName().equals("true"))
	{
		TrueTerm trueTerm = new TrueTerm();
		return trueTerm;
	}
	
	return null;
}


public static ExpressionBuilder createAppropriateBuilder(Element e)
{
	
	if(e.getName().equals("phrase"))
	{
			return new PhraseBuilder();

	}
	
	if(e.getName().equals("constant"))
	{
		return new ConstantTermBuilder();
	}
	
	if(e.getName().equals("true"))
	{
		return new TrueTermBuilder();
	}
	
	
	if(e.getName().equals("term"))
	{
		return new TermBuilder();
	}	
	return null;
}

}
