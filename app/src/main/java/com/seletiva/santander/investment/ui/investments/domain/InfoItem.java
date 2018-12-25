package com.seletiva.santander.investment.ui.investments.domain;

import com.google.gson.annotations.SerializedName;

public class InfoItem {
    @SerializedName("name")
    private String name;

    @SerializedName("data")
    private String data;

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }
}
