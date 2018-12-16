package com.avanade.santander.fundos.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoreInfo {

    @Expose
    private final More month;

    @Expose
    private final More year;

    @SerializedName(value = "12months")
    private final More lastyear;

    public MoreInfo(More month, More year, More lastyear) {
        this.month = month;
        this.year = year;
        this.lastyear = lastyear;
    }

    public More getMonth() {
        return month;
    }

    public More getYear() {
        return year;
    }

    public More getLastyear() {
        return lastyear;
    }
}
