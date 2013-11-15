package com.milo.expression;

import java.util.Map;

import org.dom4j.Element;

import com.milo.questionpaper.equation.Expression;
import com.milo.questionpaper.equation.Term;

public interface ExpressionBuilder {
public Expression resolve(Element e,Map<String,Term> map,Map<String,String> repeatedVarsMap);
}
