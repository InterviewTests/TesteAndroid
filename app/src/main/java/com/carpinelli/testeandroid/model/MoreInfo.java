package com.carpinelli.testeandroid.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class MoreInfo implements Serializable {

    private Month month;
    private Year year;
    @JsonProperty("12months")
    private TwelveMonths twelveMonths;

    public MoreInfo() {
    }

    public MoreInfo(Month month, Year year, TwelveMonths twelveMonths) {
        this.month = month;
        this.year = year;
        this.twelveMonths = twelveMonths;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public TwelveMonths getTwelveMonths() {
        return twelveMonths;
    }

    public void setTwelveMonths(TwelveMonths twelveMonths) {
        this.twelveMonths = twelveMonths;
    }

}
