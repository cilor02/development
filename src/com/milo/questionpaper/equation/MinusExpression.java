package com.milo.questionpaper.equation;

public class MinusExpression extends OperationExpression {

	@Override
	public double value() {
		// TODO Auto-generated method stub
		return exp1.value()- exp2.value();
	}

}
