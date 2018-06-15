package br.com.accenture.santander.wallacebaldenebre.model;

import java.util.List;

public class Screen {
    private String title;
    private String fundName;
    private String whatIs;
    private String definition;
    private String riskTitle;
    private int risk;
    private String infoTitle;
    private List<MoreInfo> moreInfo;
    private Info[] infos;
    private DownInfo[] downInfos;

    public Screen() {
    }

    public Screen(String title) {
        this.title = title;
    }

    public Screen(String title, String fundName, String whatIs, String definition, String riskTitle, int risk, String infoTitle, List<MoreInfo> moreInfo, Info[] infos, DownInfo[] downInfos) {
        this.title = title;
        this.fundName = fundName;
        this.whatIs = whatIs;
        this.definition = definition;
        this.riskTitle = riskTitle;
        this.risk = risk;
        this.infoTitle = infoTitle;
        this.moreInfo = moreInfo;
        this.infos = infos;
        this.downInfos = downInfos;
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

    public List<MoreInfo> getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(List<MoreInfo> moreInfo) {
        this.moreInfo = moreInfo;
    }

    public Info[] getInfos() {
        return infos;
    }

    public void setInfos(Info[] infos) {
        this.infos = infos;
    }

    public DownInfo[] getDownInfos() {
        return downInfos;
    }

    public void setDownInfos(DownInfo[] downInfos) {
        this.downInfos = downInfos;
    }

}
