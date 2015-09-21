package com.mandi5.bean;

import com.google.gson.annotations.SerializedName;

public class Banner {
	
	private String id;
	@SerializedName("img")
	private String imageUrl;
	
	
	
	
	public Banner() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	

}
