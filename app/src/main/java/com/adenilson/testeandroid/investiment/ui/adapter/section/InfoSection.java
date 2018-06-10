package com.adenilson.testeandroid.investiment.ui.adapter.section;

import com.adenilson.testeandroid.investiment.model.Info;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

@Parcel(Parcel.Serialization.BEAN)
public class InfoSection extends InvestmentSection {

    private Info mInfo;

    @ParcelConstructor
    public InfoSection(Info info) {
        this.mInfo = info;
    }

    public Info getInfo() {
        return mInfo;
    }

    @Override
    public int getItemViewType() {
        return TYPE_INFO;
    }
}
