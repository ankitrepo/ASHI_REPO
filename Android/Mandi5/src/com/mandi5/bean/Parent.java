package com.mandi5.bean;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class Parent {
	private String id;
	private String name;
	@SerializedName("icon")
	private String imageIconUrl;
	@SerializedName("child")
	private ArrayList<Child> childCategoryList;
	

	
	public Parent(String id, String name) {
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
	public String getImageIconUrl() {
		return imageIconUrl;
	}
	public void setImageIconUrl(String imageIconUrl) {
		this.imageIconUrl = imageIconUrl;
	}
	public ArrayList<Child> getChildCategoryList() {
		return childCategoryList;
	}
	public void setChildCategoryList(ArrayList<Child> childCategoryList) {
		this.childCategoryList = childCategoryList;
	}
	
	
	

}
