package com.aline.teste.Models;

import com.google.gson.annotations.SerializedName;


public class InfoItem{

	@SerializedName("data")
	private String data;

	@SerializedName("name")
	private String name;

	public void setData(String data){
		this.data = data;
	}

	public String getData(){
		return data;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}