package com.bruno.santander.santanderchallenge.investimento.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Screen {

    @JsonProperty("title")
    private String title;
    @JsonProperty("fundName")
    private String fundName;
    @JsonProperty("whatIs")
    private String whatIs;
    @JsonProperty("definition")
    private String definition;
    @JsonProperty("riskTitle")
    private String riskTitle;
    @JsonProperty("risk")
    private int risk;
    @JsonProperty("infoTitle")
    private String infoTitle;
    @JsonProperty("moreInfo")
    private MoreInfo moreInfo;
    @JsonProperty("info")
    private ArrayList<Info> listInfo;
    @JsonProperty("downInfo")
    private ArrayList<Info> downInfoList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getWhatIs() {
        return whatIs;
    }

    public void setWhatIs(String whatIs) {
        this.whatIs = whatIs;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getRiskTitle() {
        return riskTitle;
    }

    public void setRiskTitle(String riskTitle) {
        this.riskTitle = riskTitle;
    }

    public int getRisk() {
        return risk;
    }

    public void setRisk(int risk) {
        this.risk = risk;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public MoreInfo getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(MoreInfo moreInfo) {
        this.moreInfo = moreInfo;
    }

    public ArrayList<Info> getListInfo() {
        return listInfo;
    }

    public void setListInfo(ArrayList<Info> listInfo) {
        this.listInfo = listInfo;
    }

    public ArrayList<Info> getDownInfoList() {
        return downInfoList;
    }

    public void setDownInfoList(ArrayList<Info> downInfoList) {
        this.downInfoList = downInfoList;
    }
}
