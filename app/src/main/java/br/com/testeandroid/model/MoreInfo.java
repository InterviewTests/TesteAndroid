package br.com.testeandroid.model;

import com.google.gson.annotations.SerializedName;

public class MoreInfo {

    @SerializedName("month")
    private Month month;
    @SerializedName("year")
    private Year Year;
    @SerializedName("12months")
    private Months months;

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public MoreInfo.Year getYear() {
        return Year;
    }

    public void setYear(MoreInfo.Year year) {
        Year = year;
    }

    public Months getMonths() {
        return months;
    }

    public void setMonths(Months months) {
        this.months = months;
    }

    public class Month{
        @SerializedName("fund")
        public double fund;
        @SerializedName("CDI")
        public double cdi;

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

    public class Year{
        @SerializedName("fund")
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
    }

    public class Months{
        @SerializedName("fund")
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
    }
}
