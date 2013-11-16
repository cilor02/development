package com.milo.questionpaper;

public class DivisionBranch extends Branch{

	public DivisionBranch(Termold term1,Termold term2)
	{
		super(term1,term2);
	}
	@Override
	public double calculate() {
		// TODO Auto-generated method stub
		return firstTerm.value() / secondTerm.value();
	}

}
