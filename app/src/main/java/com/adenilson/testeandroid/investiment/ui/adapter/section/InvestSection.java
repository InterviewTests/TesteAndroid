package com.adenilson.testeandroid.investiment.ui.adapter.section;

import org.parceler.Parcel;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

@Parcel(Parcel.Serialization.BEAN)
public class InvestSection extends InvestmentSection {

    @Override
    public int getItemViewType() {
        return TYPE_INVEST;
    }

}
