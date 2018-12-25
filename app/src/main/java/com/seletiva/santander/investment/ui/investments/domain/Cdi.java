package com.seletiva.santander.investment.ui.investments.domain;

import com.google.gson.annotations.SerializedName;

public class Cdi {
    @SerializedName("fund")
    private float fund;

    @SerializedName("CDI")
    private float CDI;

    public String getFund() {
        return fund + "%";
    }

    public String getCDI() {
        return CDI + "%";
    }
}
