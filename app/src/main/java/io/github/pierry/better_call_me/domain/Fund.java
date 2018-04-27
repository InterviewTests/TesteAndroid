package io.github.pierry.better_call_me.domain;

import io.github.pierry.better_call_me.domain.viewmodels.Data;
import io.github.pierry.better_call_me.domain.viewmodels.MoreInfo;
import java.util.List;

public class Fund {

  private String title;
  private String fundName;
  private String whatIs;
  private String definition;
  private String riskTitle;
  private int risk;
  private String infoTitle;
  private MoreInfo moreInfo;
  private List<Data> info;
  private List<Data> downInfo;

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

  public List<Data> getInfo() {
    return info;
  }

  public void setInfo(List<Data> info) {
    this.info = info;
  }

  public List<Data> getDownInfo() {
    return downInfo;
  }

  public void setDownInfo(List<Data> downInfo) {
    this.downInfo = downInfo;
  }
}
