package com.adenilson.testeandroid.investiment.model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;
import org.parceler.ParcelProperty;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

@Parcel(Parcel.Serialization.BEAN)
public class MoreInfoDetail {

    @ParcelProperty("cdi")
    private double CDI;
    private double fund;

    @ParcelConstructor
    public MoreInfoDetail( @ParcelProperty("cdi") double CDI, double fund) {
        this.CDI = CDI;
        this.fund = fund;
    }

    public double getCDI() {
        return CDI;
    }

    public void setCDI(double CDI) {
        this.CDI = CDI;
    }

    public double getFund() {
        return fund;
    }

    public void setFund(double fund) {
        this.fund = fund;
    }
}
