package com.aline.teste.Models;

import com.google.gson.annotations.SerializedName;


public class MoreInfo{

	@SerializedName("month")
	private Month month;

	@SerializedName("year")
	private Year year;

	@SerializedName("12months")
	private JsonMember12months jsonMember12months;

	public void setMonth(Month month){
		this.month = month;
	}

	public Month getMonth(){
		return month;
	}

	public void setYear(Year year){
		this.year = year;
	}

	public Year getYear(){
		return year;
	}

	public void setJsonMember12months(JsonMember12months jsonMember12months){
		this.jsonMember12months = jsonMember12months;
	}

	public JsonMember12months getJsonMember12months(){
		return jsonMember12months;
	}
}