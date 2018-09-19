package br.com.santander.testeandroid.ui.funds.domain.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FundsScreen {
    private String title;
    private String fundName;
    private String whatIs;
    private String definition;
    private String riskTitle;
    private int risk;
    private String infoTitle;
    private MoreInfoFunds moreInfo;
    private List<InfoDetail> info;
    @SerializedName("downInfo")
    private List<InfoDetail> downloadInfo;

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

    public MoreInfoFunds getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(MoreInfoFunds moreInfo) {
        this.moreInfo = moreInfo;
    }

    public List<InfoDetail> getInfo() {
        return info;
    }

    public void setInfo(List<InfoDetail> info) {
        this.info = info;
    }

    public List<InfoDetail> getDownloadInfo() {
        return downloadInfo;
    }

    public void setDownloadInfo(List<InfoDetail> downloadInfo) {
        this.downloadInfo = downloadInfo;
    }
}
