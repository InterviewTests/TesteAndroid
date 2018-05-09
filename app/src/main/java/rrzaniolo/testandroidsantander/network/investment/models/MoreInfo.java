package rrzaniolo.testandroidsantander.network.investment.models;

/*
 * Created by Rodrigo Rodrigues Zaniolo on 5/9/2018.
 * All rights reserved.
 */

import com.google.gson.annotations.SerializedName;

/**
 * Model for a group pf periods. */
public class MoreInfo {
    //region --- Variables
    @SerializedName("month")
    private PeriodInfo month;
    public PeriodInfo getMonth() {
        return month;
    }
    public void setMonth(PeriodInfo month) {
        this.month = month;
    }

    @SerializedName("year")
    private PeriodInfo year;
    public PeriodInfo getYear() {
        return year;
    }
    public void setYear(PeriodInfo year) {
        this.year = year;
    }

    @SerializedName("12months")
    private PeriodInfo twelveMonths;
    public PeriodInfo getTwelveMonths() {
        return twelveMonths;
    }
    public void setTwelveMonths(PeriodInfo twelveMonths) {
        this.twelveMonths = twelveMonths;
    }
    //endregion

    //region --- Constructor
    public MoreInfo() { }

    public MoreInfo(PeriodInfo month, PeriodInfo year, PeriodInfo twelveMonths) {
        this.month = month;
        this.year = year;
        this.twelveMonths = twelveMonths;
    }
    //endregion
}
