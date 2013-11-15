package com.milo.questionpaper.xml;

import java.util.HashMap;

import com.milo.questionpaper.Branch;

public class Equation {
	//once located store references to leaves here
	//theywil be set by name /id
private HashMap<String, Object> variables;
private Branch branch;

public double Resolve()
{
	return branch.value();
}
}
