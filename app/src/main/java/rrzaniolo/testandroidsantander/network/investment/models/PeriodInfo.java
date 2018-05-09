package rrzaniolo.testandroidsantander.network.investment.models;

/*
 * Created by Rodrigo Rodrigues Zaniolo on 5/9/2018.
 * All rights reserved.
 */

import com.google.gson.annotations.SerializedName;

/***
 * Base Model for Month, Year and 12Months.
 * */
public class PeriodInfo {
    //region --- Variable
    @SerializedName("fund")
    private Double fund;
    public Double getFund() {
        return fund;
    }
    public void setFund(Double fund) {
        this.fund = fund;
    }

    @SerializedName("CDI")
    private Double cdi;
    public Double getCdi() {
        return cdi;
    }
    public void setCdi(Double cdi) {
        this.cdi = cdi;
    }
    //endregion

    //region --- Constructor
    public PeriodInfo() { }

    public PeriodInfo(Double fund, Double cdi) {
        this.fund = fund;
        this.cdi = cdi;
    }
    //endregion
}
