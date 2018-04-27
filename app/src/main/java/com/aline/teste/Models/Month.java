package com.aline.teste.Models;


import com.google.gson.annotations.SerializedName;


public class Month{

	@SerializedName("fund")
	private double fund;

	@SerializedName("CDI")
	private double cDI;

	public void setFund(double fund){
		this.fund = fund;
	}

	public double getFund(){
		return fund;
	}

	public void setCDI(double cDI){
		this.cDI = cDI;
	}

	public double getCDI(){
		return cDI;
	}
}