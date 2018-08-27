package br.com.santander.testeandroid.investment.model;

import com.google.gson.annotations.SerializedName;

public class Period {
    @SerializedName("fund")
    private Double fund;

    @SerializedName("CDI")
    private Double cdi;

    public Double getFund() {
        return fund;
    }

    public void setFund(Double fund) {
        this.fund = fund;
    }

    public Double getCdi() {
        return cdi;
    }

    public void setCdi(Double cdi) {
        this.cdi = cdi;
    }
}
