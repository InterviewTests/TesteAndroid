package com.UI;

public class MoreInfo {

    private String period;
    private double fund;
    private double CDI;

    public double getCDI() {
        return CDI;
    }

    public double getFund() {
        return fund;
    }

    public String getPeriod() {
        return period;
    }

    public MoreInfo(String period, double fund, double CDI) {
        this.period = period;
        this.fund = fund;
        this.CDI = CDI;
    }
}
