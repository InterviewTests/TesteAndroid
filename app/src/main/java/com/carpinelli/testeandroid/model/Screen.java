package com.carpinelli.testeandroid.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class Screen implements Serializable {

    private String title;
    private String fundName;
    private String whatIs;
    private String definition;
    private String riskTitle;
    private int risk;
    private String infoTitle;
    private MoreInfo moreInfo;
    @JsonProperty("info")
    private List<Info> infos;
    @JsonProperty("downInfo")
    private List<DownInfo> downInfos;

    public Screen() {
    }


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

    public void setInfos(List<Info> infos) {
        this.infos = infos;
    }

    public List<Info> getInfos() {
        return infos;
    }

    public List<DownInfo> getDownInfos() {
        return downInfos;
    }

    public void setDownInfos(List<DownInfo> downInfos) {
        this.downInfos = downInfos;
    }
}
