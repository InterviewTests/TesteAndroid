package br.com.iomarsantos.testeandroid.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InvestmentFund implements Serializable {

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
    private Integer risk;
    @JsonProperty("infoTitle")
    private String infoTitle;
    @JsonProperty("moreInfo")
    private MoreInfo moreInfo;
    @JsonProperty("info")
    private List<Info> info = null;
    @JsonProperty("downInfo")
    private List<DownInfo> downInfo = null;

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("fundName")
    public String getFundName() {
        return fundName;
    }

    @JsonProperty("fundName")
    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    @JsonProperty("whatIs")
    public String getWhatIs() {
        return whatIs;
    }

    @JsonProperty("whatIs")
    public void setWhatIs(String whatIs) {
        this.whatIs = whatIs;
    }

    @JsonProperty("definition")
    public String getDefinition() {
        return definition;
    }

    @JsonProperty("definition")
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @JsonProperty("riskTitle")
    public String getRiskTitle() {
        return riskTitle;
    }

    @JsonProperty("riskTitle")
    public void setRiskTitle(String riskTitle) {
        this.riskTitle = riskTitle;
    }

    @JsonProperty("risk")
    public Integer getRisk() {
        return risk;
    }

    @JsonProperty("risk")
    public void setRisk(Integer risk) {
        this.risk = risk;
    }

    @JsonProperty("infoTitle")
    public String getInfoTitle() {
        return infoTitle;
    }

    @JsonProperty("infoTitle")
    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    @JsonProperty("moreInfo")
    public MoreInfo getMoreInfo() {
        return moreInfo;
    }

    @JsonProperty("moreInfo")
    public void setMoreInfo(MoreInfo moreInfo) {
        this.moreInfo = moreInfo;
    }

    @JsonProperty("info")
    public List<Info> getInfo() {
        return info;
    }

    @JsonProperty("info")
    public void setInfo(List<Info> info) {
        this.info = info;
    }

    @JsonProperty("downInfo")
    public List<DownInfo> getDownInfo() {
        return downInfo;
    }

    @JsonProperty("downInfo")
    public void setDownInfo(List<DownInfo> downInfo) {
        this.downInfo = downInfo;
    }

}
