package com.adenilson.testeandroid.investiment.ui.adapter.section;

import com.adenilson.testeandroid.investiment.model.MoreInfo;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;
import org.parceler.ParcelProperty;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

@Parcel(Parcel.Serialization.BEAN)
public class MoreInfoSection extends InvestmentSection {

    @ParcelProperty("moreinfo")
    private MoreInfo mMoreInfo;

    @ParcelConstructor
    public MoreInfoSection( @ParcelProperty("moreinfo")MoreInfo mMoreInfo) {
        this.mMoreInfo = mMoreInfo;
    }

    public MoreInfo getMoreInfo() {
        return mMoreInfo;
    }

    @Override
    public int getItemViewType() {
        return TYPE_MORE_INFO;
    }
}
