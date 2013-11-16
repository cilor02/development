package com.milo.questionpaper.equation;

public abstract class BooleanExpression extends OperationExpression {

	@Override
	public double value() {
		// TODO Auto-generated method stub
		return booleanCompare(exp1, exp2)?1:0;
	}

	//all boolean operations will implement this method
	//e.g. gt returns true for e1 > e2
	abstract Boolean booleanCompare(Expression e1,Expression e2);
}
