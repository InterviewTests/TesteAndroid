package com.adenilson.testeandroid.networking.webservices.investiment;

import com.google.gson.annotations.SerializedName;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 09/06/2018
 */

public class MoreInfoResponse {

    private MoreInfoDetailResponse month;
    private MoreInfoDetailResponse year;
    @SerializedName("12months")
    private MoreInfoDetailResponse twelveMonths;

    public MoreInfoDetailResponse getMonth() {
        return month;
    }

    public MoreInfoDetailResponse getYear() {
        return year;
    }

    public MoreInfoDetailResponse getTwelveMonths() {
        return twelveMonths;
    }

}
