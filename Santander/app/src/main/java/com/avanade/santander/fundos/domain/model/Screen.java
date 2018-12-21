package com.avanade.santander.fundos.domain.model;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.Expose;

import java.util.List;

@Entity(tableName = "screen")
public class Screen {

    @Expose
    private final String title;
    @Expose
    private final String fundName;
    @Expose
    private final String whatIs;
    @Expose
    private final String definition;
    @Expose
    private final String riskTitle;
    @Expose
    private final int risk;
    @Expose
    private final String infoTitle;
    @Expose
    private final MoreInfo moreInfo;
    @Expose
    private final List<Info> info;
    @Expose
    private final List<Info> downInfo;

    public Screen(String title, String fundName, String whatIs, String definition,
                  String riskTitle, int risk, String infoTitle, MoreInfo moreInfo,
                  List<Info> info, List<Info> downInfo) {
        this.title = title;
        this.fundName = fundName;
        this.whatIs = whatIs;
        this.definition = definition;
        this.riskTitle = riskTitle;
        this.risk = risk;
        this.infoTitle = infoTitle;
        this.moreInfo = moreInfo;
        this.info = info;
        this.downInfo = downInfo;
    }

    public String getTitle() {
        return title;
    }

    public String getFundName() {
        return fundName;
    }

    public String getWhatIs() {
        return whatIs;
    }

    public String getDefinition() {
        return definition;
    }

    public String getRiskTitle() {
        return riskTitle;
    }

    public int getRisk() {
        return risk;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public MoreInfo getMoreInfo() {
        return moreInfo;
    }

    public List<Info> getInfo() {
        return info;
    }

    public List<Info> getDownInfo() {
        return downInfo;
    }
}
