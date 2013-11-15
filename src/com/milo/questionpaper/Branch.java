package com.milo.questionpaper;

public abstract class Branch implements Termold{
protected Termold firstTerm;
protected Termold secondTerm;

public Branch(Termold term1,Termold term2)
{
	firstTerm=term1;
	secondTerm=term2;
}

public double value() {
	// TODO Auto-generated method stub
	return 0;
}

public abstract double calculate();



}
