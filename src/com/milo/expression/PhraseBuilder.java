package com.milo.expression;

import java.util.List;
import java.util.Map;

import org.dom4j.Element;

import com.milo.questionpaper.equation.Expression;
import com.milo.questionpaper.equation.OperationExpression;
import com.milo.questionpaper.equation.Term;

 public class PhraseBuilder implements ExpressionBuilder{

	public Expression resolve(Element expr,Map<String,Term> map,Map<String,String> repeatedVarsMap) {
		// TODO Auto-generated method stub
		
//		List<Element> lstExprElements=expr.selectNodes("*"); 
		List<Element> lstExprElements=expr.elements(); 		
		Element eleChild1=lstExprElements.get(0);
		Element eleChild2=lstExprElements.get(1);
		OperationExpression opExpr = (OperationExpression)ExpressionFactory.createAppropriateObj(expr) ;
		ExpressionBuilder exprBld1 = ExpressionFactory.createAppropriateBuilder(eleChild1);
		ExpressionBuilder exprBld2 = ExpressionFactory.createAppropriateBuilder(eleChild2);
		opExpr.setExp1(exprBld1.resolve(eleChild1,map,repeatedVarsMap));
		opExpr.setExp2(exprBld2.resolve(eleChild2,map,repeatedVarsMap));
		return opExpr;
	}

}
