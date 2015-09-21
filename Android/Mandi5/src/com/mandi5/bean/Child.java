package com.mandi5.bean;

import com.google.gson.annotations.SerializedName;

public class Child {

	private String id;
	private String name;
	@SerializedName("products")
	private int numOfProduts;
	
	
	
	public Child(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumOfProduts() {
		return numOfProduts;
	}
	public void setNumOfProduts(int numOfProduts) {
		this.numOfProduts = numOfProduts;
	}
	
	
	
}
