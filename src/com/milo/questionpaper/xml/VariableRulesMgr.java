package com.milo.questionpaper.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Element;

public class VariableRulesMgr {
	private final String VARIABLE_RULES="question-rule";
	private final String DERIVED_VARIABLE_RULES="derived-variable";
	private List <EquationBuilder>equationVariableRules;
	private Map<String,EquationBuilder>derivedVariablesRules;
	private Element eleDerivedVariables;
	private Map<String, String> derivedVars;
	
	public Map<String, String> getDerivedVars() {
		return derivedVars;
	}

	public void setUpRules(Element rules)
	{
		equationVariableRules=new ArrayList<EquationBuilder>();
		List<Element> questionRules=rules.elements(VARIABLE_RULES);
		for(Element questionRule:questionRules)
		{
			equationVariableRules.add(new EquationBuilder(questionRule));
		}
		
	}
	
	public void setUpDerivedVariables(Element dependentVariables)
	{
		eleDerivedVariables=dependentVariables;
		derivedVariablesRules=new HashMap<String,EquationBuilder>();
		List<Element> listDerivedVariablesRules=dependentVariables.elements(DERIVED_VARIABLE_RULES);
		for(Element derivedVariablesRule:listDerivedVariablesRules)
		{
			derivedVariablesRules.put( derivedVariablesRule.attributeValue("name"),new EquationBuilder(derivedVariablesRule));
		}
		
	}


	public List<EquationBuilder> getEquationVariableRules() {
		return equationVariableRules;
	}
	
	public boolean validateVariables(Map<String, String> varValues,Map<String, String> dupVarValues)
	{
		String variableName;
		for(EquationBuilder equationVariableRule:equationVariableRules )
		{
			equationVariableRule.injectNamedParametersAsStrings(varValues,equationVariableRule.getRepeatmapVariables());
			//retrieve variables in rule
            if(equationVariableRule.solve()<=0)
            	return false;
		}
		return true;
	}
	
	public boolean validateDerivedVariables(Map<String, String>varValues )
	{
		derivedVars = new HashMap<String, String>();
		if(this.derivedVariablesRules == null)
		{
			return true;
		}
			
		String variableName;
		for(String derivedVariableKey:this.derivedVariablesRules.keySet() )
		{
			EquationBuilder derivedVariablesRule = derivedVariablesRules.get(derivedVariableKey);
			derivedVariablesRule.injectNamedParametersAsStrings(varValues, derivedVariablesRule.getRepeatmapVariables());
			
			//retrieve variables in rule
			
			StringBuilder sBuilderXpathDerivedVars = new StringBuilder("derived-variable[@name='").append(derivedVariableKey).append("']");
			
			// get low high limits
			Element eleDerivedVariable = (Element)this.eleDerivedVariables.selectSingleNode(sBuilderXpathDerivedVars.toString());
			String high = eleDerivedVariable.attributeValue("high");
			String low  = eleDerivedVariable.attributeValue("low");
			
			int derivedValue = derivedVariablesRule.solve().intValue();
			
			if(high != null)
			{
				if(derivedValue>Integer.parseInt(high))
				{
					return false;
				}
			}
			
			if(low != null)
			{
				if(derivedValue<Integer.parseInt(low))
				{
					return false;
				}
			}
         derivedVars.put(derivedVariableKey, String.valueOf(derivedValue));
		}
		return true;
	}
}
