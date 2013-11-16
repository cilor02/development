package com.milo.questionpaper.xml;

import junit.framework.Assert;

import org.junit.Test;

import com.milo.questionpaper.equation.ConstantTerm;
import com.milo.questionpaper.equation.HCFExpression;

public class HCFExpressionTest {
@Test
public void testValue()
{
	double number1=10;
	double number2=35;
HCFExpression hcfExpression= new HCFExpression();
ConstantTerm cTerm=new ConstantTerm();
cTerm.setNumber(number1);
hcfExpression.setExp1(cTerm);

ConstantTerm cTerm2=new ConstantTerm();
cTerm2.setNumber(number2);
hcfExpression.setExp2(cTerm2);
System.out.println(hcfExpression.value());
Assert.assertTrue(hcfExpression.value()==5);
}
}
