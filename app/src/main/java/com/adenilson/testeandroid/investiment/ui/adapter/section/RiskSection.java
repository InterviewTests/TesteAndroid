package com.adenilson.testeandroid.investiment.ui.adapter.section;

import com.adenilson.testeandroid.investiment.model.Risk;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

@Parcel(Parcel.Serialization.BEAN)
public class RiskSection extends InvestmentSection{

    private Risk mRisk;

    @ParcelConstructor
    public RiskSection(Risk risk) {
        this.mRisk = risk;
    }

    public Risk getRisk() {
        return mRisk;
    }

    @Override
    public int getItemViewType() {
        return TYPE_RISK;
    }


}
