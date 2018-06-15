package br.com.accenture.santander.wallacebaldenebre.model;

public class Year {
    private double fund;
    private double CDI;

    public Year() {
    }

    public Year(double fund, double CDI) {
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