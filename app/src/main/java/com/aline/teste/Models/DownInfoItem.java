package com.aline.teste.Models;


import com.google.gson.annotations.SerializedName;


public class DownInfoItem{

	@SerializedName("data")
	private Object data;

	@SerializedName("name")
	private String name;

	public void setData(Object data){
		this.data = data;
	}

	public Object getData(){
		return data;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}