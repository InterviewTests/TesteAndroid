package com.adapters;

public class MoreInfo {

    private String period;
    private String fund;
    private String CDI;

    public String getCDI() {
        return CDI;
    }

    public String getFund() {
        return fund;
    }

    public String getPeriod() {
        return period;
    }

    public MoreInfo(String period, String fund, String CDI) {
        this.period = period;
        this.fund = fund;
        this.CDI = CDI;
    }
}
