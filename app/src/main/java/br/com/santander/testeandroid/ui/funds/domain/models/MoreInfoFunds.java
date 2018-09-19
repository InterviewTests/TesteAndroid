package br.com.santander.testeandroid.ui.funds.domain.models;

import com.google.gson.annotations.SerializedName;

public class MoreInfoFunds {
    private MoreInfoFundsDetail month;
    private MoreInfoFundsDetail year;
    @SerializedName("12months")
    private MoreInfoFundsDetail twelveMonths;

    public MoreInfoFundsDetail getMonth() {
        return month;
    }

    public void setMonth(MoreInfoFundsDetail month) {
        this.month = month;
    }

    public MoreInfoFundsDetail getYear() {
        return year;
    }

    public void setYear(MoreInfoFundsDetail year) {
        this.year = year;
    }

    public MoreInfoFundsDetail getTwelveMonths() {
        return twelveMonths;
    }

    public void setTwelveMonths(MoreInfoFundsDetail twelveMonths) {
        this.twelveMonths = twelveMonths;
    }
}
