package com.nataliafavero.santander.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nataliafavero on 10/09/18.
 */

public class Screen {

    @Expose
    private String title;
    @Expose
    private String fundName;
    @Expose
    private String whatIs;
    @Expose
    private String definition;
    @Expose
    private String riskTitle;
    @Expose
    private int risk;
    @Expose
    private String infoTitle;
    @Expose
    private MoreInfo moreInfo;
    @Expose
    private List<Info> info;
    @Expose
    private List<Info> downInfo;

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

