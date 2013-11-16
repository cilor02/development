package com.milo.questionpaper.xml;

import org.dom4j.Element;

public interface SpecialTextSVGGenerator {

	/**
	 * This method converts special 'decorator' formatting elements into their true
	 * SVG visual representation. Usefu for fractions, Roots and inolved equations matrices etc.   
	 * @param e
	 * @return
	 */
	public Element generateSVGFragment(Element e);
	
}
