package br.com.santander.testeandroid.investment.model;

import com.google.gson.annotations.SerializedName;

public class MoreInfo {
    @SerializedName("month")
    private Period month;

    @SerializedName("year")
    private Period year;

    @SerializedName("12months")
    private Period twelveMonths;

    public Period getMonth() {
        return month;
    }

    public void setMonth(Period month) {
        this.month = month;
    }

    public Period getYear() {
        return year;
    }

    public void setYear(Period year) {
        this.year = year;
    }

    public Period getTwelveMonths() {
        return twelveMonths;
    }

    public void setTwelveMonths(Period twelveMonths) {
        this.twelveMonths = twelveMonths;
    }
}
