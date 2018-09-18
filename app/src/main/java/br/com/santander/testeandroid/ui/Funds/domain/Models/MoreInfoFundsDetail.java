package br.com.santander.testeandroid.ui.Funds.domain.Models;

import com.google.gson.annotations.SerializedName;

public class MoreInfoFundsDetail {
    private double fund;
    @SerializedName("CDI")
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

    public String getFundPercentage() {
        return fund + "%";
    }

    public String getCdiPercentage() {
        return cdi + "%";
    }
}
