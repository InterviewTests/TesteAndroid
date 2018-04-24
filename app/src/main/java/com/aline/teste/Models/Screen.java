package com.aline.teste.Models;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class Screen{

	@SerializedName("riskTitle")
	private String riskTitle;

	@SerializedName("infoTitle")
	private String infoTitle;

	@SerializedName("whatIs")
	private String whatIs;

	@SerializedName("definition")
	private String definition;

	@SerializedName("risk")
	private int risk;

	@SerializedName("downInfo")
	private List<DownInfoItem> downInfo;

	@SerializedName("title")
	private String title;

	@SerializedName("fundName")
	private String fundName;

	@SerializedName("moreInfo")
	private MoreInfo moreInfo;

	@SerializedName("info")
	private List<InfoItem> info;

	public void setRiskTitle(String riskTitle){
		this.riskTitle = riskTitle;
	}

	public String getRiskTitle(){
		return riskTitle;
	}

	public void setInfoTitle(String infoTitle){
		this.infoTitle = infoTitle;
	}

	public String getInfoTitle(){
		return infoTitle;
	}

	public void setWhatIs(String whatIs){
		this.whatIs = whatIs;
	}

	public String getWhatIs(){
		return whatIs;
	}

	public void setDefinition(String definition){
		this.definition = definition;
	}

	public String getDefinition(){
		return definition;
	}

	public void setRisk(int risk){
		this.risk = risk;
	}

	public int getRisk(){
		return risk;
	}

	public void setDownInfo(List<DownInfoItem> downInfo){
		this.downInfo = downInfo;
	}

	public List<DownInfoItem> getDownInfo(){
		return downInfo;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setFundName(String fundName){
		this.fundName = fundName;
	}

	public String getFundName(){
		return fundName;
	}

	public void setMoreInfo(MoreInfo moreInfo){
		this.moreInfo = moreInfo;
	}

	public MoreInfo getMoreInfo(){
		return moreInfo;
	}

	public void setInfo(List<InfoItem> info){
		this.info = info;
	}

	public List<InfoItem> getInfo(){
		return info;
	}
}