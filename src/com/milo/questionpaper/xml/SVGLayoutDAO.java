package com.milo.questionpaper.xml;

import java.util.ResourceBundle;

public class SVGLayoutDAO {
public static ResourceBundle bundle;
	static{
        bundle = ResourceBundle.getBundle("presentation");
	}
	public static String getProperty(String property)
	{
		return bundle.getString(property);
	}
}
