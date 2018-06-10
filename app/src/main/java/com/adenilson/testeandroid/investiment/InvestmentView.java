package com.adenilson.testeandroid.investiment;

import com.adenilson.testeandroid.base.BaseView;
import com.adenilson.testeandroid.investiment.ui.adapter.section.InvestmentSection;

import java.util.List;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

public interface InvestmentView extends BaseView {


    void setInvestmentAdapterData(List<InvestmentSection> data);
}
