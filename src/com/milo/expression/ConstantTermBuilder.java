package com.milo.expression;

import java.util.Map;

import org.dom4j.Element;

import com.milo.questionpaper.equation.ConstantTerm;
import com.milo.questionpaper.equation.Expression;
import com.milo.questionpaper.equation.Term;

public class ConstantTermBuilder implements ExpressionBuilder {
private double value;

	public Expression resolve(Element e,Map<String,Term> map,Map<String,String> repeatedVarsMap) {
		// TODO Auto-generated method stub
		// set up varName
		
		
		ConstantTerm constantTerm = (ConstantTerm)ExpressionFactory.createAppropriateObj(e);

		return constantTerm;
	}

}
