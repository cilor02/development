package com.milo.questionpaper.equation;

public class Term implements Expression{

	protected double number;
	protected String varName;
	public double getNumber() {
		return number;
	}
	public void setNumber(double number) {
		this.number = number;
	}
	public String getVarName() {
		return varName;
	}
	public void setVarName(String varName) {
		this.varName = varName;
	}
	public double value() {
		// TODO Auto-generated method stub
		return number;
	}

}
