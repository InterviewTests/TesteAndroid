package com.seletiva.santander.investment.ui.investments.domain;

import com.google.gson.annotations.SerializedName;

public class MoreInfo {
    @SerializedName("month")
    private Cdi month;

    @SerializedName("year")
    private Cdi year;

    @SerializedName("12months")
    private Cdi twelveMonths;

    public Cdi getMonth() {
        return month;
    }

    public Cdi getYear() {
        return year;
    }

    public Cdi getTwelveMonths() {
        return twelveMonths;
    }
}
