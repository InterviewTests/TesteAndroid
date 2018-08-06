package br.com.iomarsantos.testeandroid.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MoreInfo implements Serializable {

    @JsonProperty("month")
    private Month month;
    @JsonProperty("year")
    private Year year;

    @JsonProperty("12months")
    private TwelveMonths twelveMonths;

    @JsonProperty("month")
    public Month getMonth() {
        return month;
    }

    @JsonProperty("month")
    public void setMonth(Month month) {
        this.month = month;
    }

    @JsonProperty("year")
    public Year getYear() {
        return year;
    }

    @JsonProperty("year")
    public void setYear(Year year) {
        this.year = year;
    }

    @JsonProperty("12months")
    public TwelveMonths getTwelveMonths() {
        return twelveMonths;
    }

    @JsonProperty("12months")
    public void setTwelveMonths(TwelveMonths twelveMonths) {
        this.twelveMonths = twelveMonths;
    }

}
