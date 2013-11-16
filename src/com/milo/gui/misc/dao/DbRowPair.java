package com.milo.gui.misc.dao;

public class DbRowPair {
	

	public DbRowPair(Integer id, String data) {
		super();
		this.id = id;
		this.data = data;
	}


	private Integer id;
	private String data;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
