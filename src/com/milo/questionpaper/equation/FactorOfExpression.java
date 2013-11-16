package com.milo.questionpaper.equation;

public class FactorOfExpression extends BooleanExpression {

	@Override
	public double value() {
		// TODO Auto-generated method stub
		return booleanCompare(exp1, exp2)?1:0;
	}

	//all boolean operations will implement this method
	//e.g. gt returns true for e1 > e2
	public Boolean booleanCompare(Expression e1,Expression e2)
	{
		double d1  = e1.value();
		double d2  = e2.value();
		
		return d1<=d2 && (d2%d1==0);
	}
}
