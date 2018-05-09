package rrzaniolo.testandroidsantander.network.investment.models;

/*
 * Created by Rodrigo Rodrigues Zaniolo on 5/9/2018.
 * All rights reserved.
 */

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Model for the investment screen.
 * */
public class Screen {
    //region --- Variables
    @SerializedName("title")
    private String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @SerializedName("fundName")
    private String fundName;
    public String getFundName() {
        return fundName;
    }
    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    @SerializedName("whatIs")
    private String subTitle;
    public String getSubTitle() {
        return subTitle;
    }
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    @SerializedName("definition")
    private  String definition;
    public String getDefinition() {
        return definition;
    }
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @SerializedName("riskTitle")
    private String riskTitle;
    public String getRiskTitle() {
        return riskTitle;
    }
    public void setRiskTitle(String riskTitle) {
        this.riskTitle = riskTitle;
    }

    @SerializedName("risk")
    private Integer risk;
    public Integer getRisk() {
        return risk;
    }
    public void setRisk(Integer risk) {
        this.risk = risk;
    }

    @SerializedName("infoTitle")
    private String infoTitle;
    public String getInfoTitle() {
        return infoTitle;
    }
    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    @SerializedName("moreInfo")
    private MoreInfo moreInfo;
    public MoreInfo getMoreInfo() {
        return moreInfo;
    }
    public void setMoreInfo(MoreInfo moreInfo) {
        this.moreInfo = moreInfo;
    }

    @SerializedName("info")
    private List<BaseInfo> infoList;
    public List<BaseInfo> getInfoList() {
        return infoList;
    }
    public void setInfoList(List<BaseInfo> infoList) {
        this.infoList = infoList;
    }

    @SerializedName("downInfo")
    private List<BaseInfo> downInfoList;
    public List<BaseInfo> getDownInfoList() {
        return downInfoList;
    }
    public void setDownInfoList(List<BaseInfo> downInfoList) {
        this.downInfoList = downInfoList;
    }
    //endregion

    //region --- Constructor
    public Screen() { }

    public Screen(String title, String fundName, String subTitle, String definition, String riskTitle,
                  Integer risk, String infoTitle, MoreInfo moreInfo, List<BaseInfo> infoList, List<BaseInfo> downInfoList) {
        this.title = title;
        this.fundName = fundName;
        this.subTitle = subTitle;
        this.definition = definition;
        this.riskTitle = riskTitle;
        this.risk = risk;
        this.infoTitle = infoTitle;
        this.moreInfo = moreInfo;
        this.infoList = infoList;
        this.downInfoList = downInfoList;
    }
    //endregion
}
