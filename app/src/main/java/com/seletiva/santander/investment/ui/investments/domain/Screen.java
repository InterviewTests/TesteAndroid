package com.seletiva.santander.investment.ui.investments.domain;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
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
    private int risk;

    @SerializedName("infoTitle")
    private String infoTitle;

    @SerializedName("moreInfo")
    private MoreInfo moreInfo;

    @SerializedName("info")
    private List<InfoItem> info;


    @SerializedName("downInfo")
    private List<InfoItem> downInfo;

    public List<InfoItem> getAllInfoItems() {
        List<InfoItem> allItems = new ArrayList<>();

        if (info != null) {
            allItems.addAll(info);
        }

        if (downInfo != null) {
            allItems.addAll(downInfo);
        }

        return allItems;
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

    public List<InfoItem> getInfo() {
        return info;
    }

    public List<InfoItem> getDownInfo() {
        return downInfo;
    }
}
