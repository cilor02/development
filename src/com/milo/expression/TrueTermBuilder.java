package com.milo.expression;

import java.util.Map;

import org.dom4j.Element;

import com.milo.questionpaper.equation.ConstantTerm;
import com.milo.questionpaper.equation.Expression;
import com.milo.questionpaper.equation.Term;
import com.milo.questionpaper.equation.TrueTerm;

public class TrueTermBuilder implements ExpressionBuilder {
private double value;

	public Expression resolve(Element e,Map<String,Term> map, Map<String,String> repeatedVarsMap) {
		// TODO Auto-generated method stub
		// set up varName
		
		
		TrueTerm trueTerm = (TrueTerm)ExpressionFactory.createAppropriateObj(e);

		return trueTerm;
	}

}
