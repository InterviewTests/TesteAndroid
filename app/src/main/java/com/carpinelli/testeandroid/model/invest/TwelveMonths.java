package com.carpinelli.testeandroid.model.invest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class TwelveMonths implements Serializable {

    private double fund;
    @JsonProperty("CDI")
    private double CDI;

    public double getFund() {
        return fund;
    }

    public void setFund(double fund) {
        this.fund = fund;
    }

    public double getCDI() {
        return CDI;
    }

    public void setCDI(double CDI) {
        this.CDI = CDI;
    }
}
