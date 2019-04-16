package br.com.ricardo.testeandroid.model;

import com.google.gson.annotations.SerializedName;

public class MoreInfo {

    private Month month;
    private Year year;

    @SerializedName("12months")
    private Twelve twelve;

    public MoreInfo(Month month, Year year, Twelve twelve) {
        this.month = month;
        this.year = year;
        this.twelve = twelve;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Twelve getTwelve() {
        return twelve;
    }

    public void setTwelve(Twelve twelve) {
        this.twelve = twelve;
    }
}
