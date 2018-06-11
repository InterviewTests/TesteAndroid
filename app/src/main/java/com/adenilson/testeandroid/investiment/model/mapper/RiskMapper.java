package com.adenilson.testeandroid.investiment.model.mapper;

import com.adenilson.testeandroid.investiment.model.Risk;
import com.adenilson.testeandroid.networking.webservices.investiment.ScreenResponse;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

public class RiskMapper {

    public Risk map(ScreenResponse screen) {
        return new Risk(screen.getRiskTitle(), screen.getRisk().risk);
    }
}
