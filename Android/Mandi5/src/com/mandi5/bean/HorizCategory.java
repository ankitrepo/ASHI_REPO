package com.mandi5.bean;

public class HorizCategory {
	private String cat_id;
	private String cat_name;
	private String cat_img;
	
	
	
	public HorizCategory(String cat_id, String cat_name) {
		super();
		this.cat_id = cat_id;
		this.cat_name = cat_name;
	}
	
	public String getCat_id() {
		return cat_id;
	}
	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	public String getCat_img() {
		return cat_img;
	}
	public void setCat_img(String cat_img) {
		this.cat_img = cat_img;
	}
	
	
	

}
