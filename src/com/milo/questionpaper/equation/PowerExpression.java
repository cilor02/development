package com.milo.questionpaper.equation;

public class PowerExpression extends OperationExpression {

	@Override
	public double value() {
		// TODO Auto-generated method stub
		return Math.pow(exp1.value(), exp2.value());
	}

}
