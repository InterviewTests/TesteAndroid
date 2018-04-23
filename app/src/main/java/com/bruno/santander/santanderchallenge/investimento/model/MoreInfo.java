package com.bruno.santander.santanderchallenge.investimento.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MoreInfo {

    @JsonProperty("month")
    private MoreInfoPeriod month;
    @JsonProperty("year")
    private MoreInfoPeriod year;
    @JsonProperty("12months")
    private MoreInfoPeriod twelveMonths;

    public MoreInfoPeriod getMonth() {
        return month;
    }

    public void setMonth(MoreInfoPeriod month) {
        this.month = month;
    }

    public MoreInfoPeriod getYear() {
        return year;
    }

    public void setYear(MoreInfoPeriod year) {
        this.year = year;
    }

    public MoreInfoPeriod getTwelveMonths() {
        return twelveMonths;
    }

    public void setTwelveMonths(MoreInfoPeriod twelveMonths) {
        this.twelveMonths = twelveMonths;
    }
}
