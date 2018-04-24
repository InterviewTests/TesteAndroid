package br.com.ibm.santander.wallacebaldenebre.model;

public class TwelveMonths {
    private double fund;
    private double CDI;

    public TwelveMonths() {
    }

    public TwelveMonths(double fund, double CDI) {
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