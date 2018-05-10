package com.carpinelli.testeandroid.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Month implements Serializable {

    private double fund;
    @JsonProperty("CDI")
    private double CDI;

    public Month() {
    }

    public Month(double fund, double CDI) {
        this.fund = fund;
        this.CDI = CDI;
    }

    public void setFund(double fund) {
        this.fund = fund;
    }

    public double getFund() {
        return fund;
    }

    public void setCDI(double CDI) {
        this.CDI = CDI;
    }

    public double getCDI() {
        return CDI;
    }
}
