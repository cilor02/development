package com.milo.questionpaper.equation;

public class ConstantTerm implements Expression{

	protected double number;

	public double getNumber() {
		return number;
	}
	public void setNumber(double number) {
		this.number = number;
	}

	public double value() {
		// TODO Auto-generated method stub
		return number;
	}

}
