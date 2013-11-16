package com.milo.questionpaper;

public class PowerBranch extends Branch{
	public PowerBranch(Termold term1,Termold term2)
	{
		super(term1,term2);
	}
	@Override
	public double calculate() {
		// TODO Auto-generated method stub
		return Math.pow(firstTerm.value(),secondTerm.value());
	}

}
