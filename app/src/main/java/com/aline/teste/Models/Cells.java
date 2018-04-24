package com.aline.teste.Models;


import com.google.gson.annotations.SerializedName;


public class Cells{

	@SerializedName("typefield")
	private Object typefield;

	@SerializedName("hidden")
	private boolean hidden;

	@SerializedName("show")
	private Object show;

	@SerializedName("id")
	private int id;

	@SerializedName("type")
	private int type;

	@SerializedName("message")
	private String message;

	@SerializedName("topSpacing")
	private double topSpacing;

	@SerializedName("required")
	private boolean required;

	public void setTypefield(Object typefield){
		this.typefield = typefield;
	}

	public Object getTypefield(){
		return typefield;
	}

	public void setHidden(boolean hidden){
		this.hidden = hidden;
	}

	public boolean isHidden(){
		return hidden;
	}

	public void setShow(Object show){
		this.show = show;
	}

	public Object getShow(){
		return show;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setType(int type){
		this.type = type;
	}

	public int getType(){
		return type;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setTopSpacing(double topSpacing){
		this.topSpacing = topSpacing;
	}

	public double getTopSpacing(){
		return topSpacing;
	}

	public void setRequired(boolean required){
		this.required = required;
	}

	public boolean isRequired(){
		return required;
	}


}