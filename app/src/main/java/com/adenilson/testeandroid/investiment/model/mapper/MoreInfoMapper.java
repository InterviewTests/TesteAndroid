package com.adenilson.testeandroid.investiment.model.mapper;

import com.adenilson.testeandroid.investiment.model.MoreInfo;
import com.adenilson.testeandroid.investiment.model.MoreInfoDetail;
import com.adenilson.testeandroid.networking.webservices.investiment.MoreInfoDetailResponse;
import com.adenilson.testeandroid.networking.webservices.investiment.ScreenResponse;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

public class MoreInfoMapper {

    public MoreInfo map(ScreenResponse screen){

        MoreInfoDetailResponse month = screen.getMoreInfo().getMonth();
        double monthCDI = month.getCDI();
        double monthFund = month.getFund();
        MoreInfoDetail moreInfoMonth = new MoreInfoDetail(monthCDI, monthFund);

        MoreInfoDetailResponse year = screen.getMoreInfo().getMonth();
        double yearCDI = year.getCDI();
        double yearFund = year.getFund();
        MoreInfoDetail moreInfoYear = new MoreInfoDetail(yearCDI, yearFund);

        MoreInfoDetailResponse twelveMonths = screen.getMoreInfo().getMonth();
        double twelveMonthsCDI = twelveMonths.getCDI();
        double twelveMonthsFund = twelveMonths.getFund();
        MoreInfoDetail moreInfoTwelveMonths = new MoreInfoDetail(twelveMonthsCDI, twelveMonthsFund);

        return new MoreInfo(screen.getInfoTitle(), moreInfoMonth, moreInfoYear, moreInfoTwelveMonths);
    }
}
