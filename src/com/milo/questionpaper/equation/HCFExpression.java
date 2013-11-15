package com.milo.questionpaper.equation;

public class HCFExpression extends OperationExpression {

	@Override
	public double value() {
		// TODO Auto-generated method stub
		Integer resultInt=hcf( Double.valueOf(exp1.value()).intValue(), Double.valueOf(exp2.value()).intValue());

		return resultInt.doubleValue();
		}
	
	private int hcf(int i,int j)
	{
		int big=0;
		int small=0;
		if(i>j)
		{
			big=i;
			small=j;
		} else
		{
			big=j;
			small=i;
		}
		
		return (big%small)==0?small:hcf(small,big%small);
	}

}
