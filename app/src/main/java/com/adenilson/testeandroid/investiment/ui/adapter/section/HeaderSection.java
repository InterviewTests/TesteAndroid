package com.adenilson.testeandroid.investiment.ui.adapter.section;

import com.adenilson.testeandroid.investiment.model.Header;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;
import org.parceler.ParcelProperty;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

@Parcel(Parcel.Serialization.BEAN)
public class HeaderSection extends InvestmentSection {

    @ParcelProperty("header")
    private Header mHeader;

    @ParcelConstructor
    public HeaderSection( @ParcelProperty("header")Header mHeader) {
        this.mHeader = mHeader;
    }

    @Override
    public int getItemViewType() {
        return TYPE_HEADER;
    }

    public Header getHeader() {
        return mHeader;
    }
}
