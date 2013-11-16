package com.milo.questionpaper.equation;

abstract public class OperationExpression implements Expression{
public Expression getExp1() {
		return exp1;
	}

	public void setExp1(Expression exp1) {
		this.exp1 = exp1;
	}

	public Expression getExp2() {
		return exp2;
	}

	public void setExp2(Expression exp2) {
		this.exp2 = exp2;
	}




protected Expression exp1;
protected Expression exp2;

abstract public double value();

}
