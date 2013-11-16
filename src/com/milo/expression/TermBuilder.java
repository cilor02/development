package com.milo.expression;

import java.util.Map;

import org.dom4j.Element;

import com.milo.questionpaper.equation.Expression;
import com.milo.questionpaper.equation.Term;

public class TermBuilder implements ExpressionBuilder {
private double value;

	public Expression resolve(Element e,Map<String,Term> map, Map<String,String> repeatedVarsMap) {
		// TODO Auto-generated method stub
		// set up varName
				
		Term term = (Term)ExpressionFactory.createAppropriateObj(e);
		// see if term already exists or stored
		if(map.containsKey(term.getVarName()))
		{
			int i = 0;
			for(;;)
			{
			 //replace duplicate name with substitute when a slot is found
				String duplicateReplacmentTermName = term.getVarName()+ i;
				if(!map.containsKey(duplicateReplacmentTermName))
				{
					map.put(duplicateReplacmentTermName, term);
					repeatedVarsMap.put(duplicateReplacmentTermName, term.getVarName());
					return term;
				}
				i++;
			}
		}
		else
		{
		   map.put(term.getVarName(),term);
		}
		
		return term;
	}

}
