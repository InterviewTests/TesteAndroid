package com.bruno.santander.santanderchallenge.investimento.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MoreInfoPeriod {

    @JsonProperty("fund")
    private double fund;

    @JsonProperty("CDI")
    private double cdi;

    public double getFund() {
        return fund;
    }

    public void setFund(double fund) {
        this.fund = fund;
    }

    public double getCdi() {
        return cdi;
    }

    public void setCdi(double cdi) {
        this.cdi = cdi;
    }
}
