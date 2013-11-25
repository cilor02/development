package com.milo.draw;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Table {
	
	private List<String> headings;
	
	private Map<String, List<String>> rows;
	
	public Table()
	{
		rows = new TreeMap<String, List<String>>();
	}
	
	public void addHeader(List<String> header)
	{
		headings = header;
	}
	
	public void addRow(String rowName,List<String>rowData)
	{
		if(rowData.size() == headings.size())
		{
		rows.put(rowName, rowData);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
