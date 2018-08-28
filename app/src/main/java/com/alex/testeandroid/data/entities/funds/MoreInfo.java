package com.alex.testeandroid.data.entities.funds;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 28/08/18.
 */
public class MoreInfo {

    //region FIELDS
    private MoreInfoDetail month;
    private MoreInfoDetail year;
    @SerializedName("12months")
    private MoreInfoDetail months12;
    //endregion

    //region PROPERTIES
    public MoreInfoDetail getMonth() {
        return month;
    }

    public void setMonth(MoreInfoDetail month) {
        this.month = month;
    }

    public MoreInfoDetail getYear() {
        return year;
    }

    public void setYear(MoreInfoDetail year) {
        this.year = year;
    }

    public MoreInfoDetail getMonths12() {
        return months12;
    }

    public void setMonths12(MoreInfoDetail months12) {
        this.months12 = months12;
    }
    //endregion
}
