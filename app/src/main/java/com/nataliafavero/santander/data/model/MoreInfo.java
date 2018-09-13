package com.nataliafavero.santander.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MoreInfo {
    @Expose
    private MoreInfoDetail month;
    @Expose
    private MoreInfoDetail year;

    @SerializedName("12months")
    @Expose
    private MoreInfoDetail twelveMoths;

    public MoreInfoDetail getMonth() {
        return month;
    }

    public MoreInfoDetail getYear() {
        return year;
    }

    public MoreInfoDetail getTwelveMoths() {
        return twelveMoths;
    }
}
