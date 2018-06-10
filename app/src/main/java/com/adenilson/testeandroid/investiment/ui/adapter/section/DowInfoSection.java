package com.adenilson.testeandroid.investiment.ui.adapter.section;

import com.adenilson.testeandroid.investiment.model.Info;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;
import org.parceler.ParcelProperty;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

@Parcel(Parcel.Serialization.BEAN)
public class DowInfoSection extends InvestmentSection {

    @ParcelProperty("info")
    private Info mInfo;

    @ParcelConstructor
    public DowInfoSection(@ParcelProperty("info") Info mInfo) {
        this.mInfo = mInfo;
    }

    public Info getInfo() {
        return mInfo;
    }

    @Override
    public int getItemViewType() {
        return TYPE_DOW_INFO;
    }
}
