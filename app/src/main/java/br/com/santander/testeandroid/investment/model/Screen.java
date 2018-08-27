package br.com.santander.testeandroid.investment.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Screen {
    @SerializedName("title")
    private String title;

    @SerializedName("fundName")
    private String fundName;

    @SerializedName("whatIs")
    private String whatIs;

    @SerializedName("definition")
    private String definition;

    @SerializedName("riskTitle")
    private String riskTitle;

    @SerializedName("risk")
    private Integer risk;

    @SerializedName("infoTitle")
    private String infoTitle;

    @SerializedName("moreInfo")
    private MoreInfo moreInfo;

    @SerializedName("info")
    private List<BaseInfo> infoList;

    @SerializedName("downInfo")
    private List<BaseInfo> downInfoList;

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

    public Integer getRisk() {
        return risk;
    }

    public void setRisk(Integer risk) {
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

    public List<BaseInfo> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<BaseInfo> infoList) {
        this.infoList = infoList;
    }

    public List<BaseInfo> getDownInfoList() {
        return downInfoList;
    }

    public void setDownInfoList(List<BaseInfo> downInfoList) {
        this.downInfoList = downInfoList;
    }
}
