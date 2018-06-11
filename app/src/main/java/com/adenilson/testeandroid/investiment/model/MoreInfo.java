package com.adenilson.testeandroid.investiment.model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

@Parcel(Parcel.Serialization.BEAN)
public class MoreInfo {

    private String infoTitle;
    private MoreInfoDetail moreInfoMonth;
    private MoreInfoDetail moreInfoYear;
    private MoreInfoDetail moreInfoTwelveMonths;

    @ParcelConstructor
    public MoreInfo(String infoTitle, MoreInfoDetail moreInfoMonth, MoreInfoDetail moreInfoYear, MoreInfoDetail moreInfoTwelveMonths) {
        this.infoTitle = infoTitle;
        this.moreInfoMonth = moreInfoMonth;
        this.moreInfoYear = moreInfoYear;
        this.moreInfoTwelveMonths = moreInfoTwelveMonths;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public MoreInfoDetail getMoreInfoMonth() {
        return moreInfoMonth;
    }

    public void setMoreInfoMonth(MoreInfoDetail moreInfoMonth) {
        this.moreInfoMonth = moreInfoMonth;
    }

    public MoreInfoDetail getMoreInfoYear() {
        return moreInfoYear;
    }

    public void setMoreInfoYear(MoreInfoDetail moreInfoYear) {
        this.moreInfoYear = moreInfoYear;
    }

    public MoreInfoDetail getMoreInfoTwelveMonths() {
        return moreInfoTwelveMonths;
    }

    public void setMoreInfoTwelveMonths(MoreInfoDetail moreInfoTwelveMonths) {
        this.moreInfoTwelveMonths = moreInfoTwelveMonths;
    }
}
