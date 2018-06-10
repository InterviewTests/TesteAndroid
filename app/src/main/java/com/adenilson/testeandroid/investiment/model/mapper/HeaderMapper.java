package com.adenilson.testeandroid.investiment.model.mapper;

import com.adenilson.testeandroid.investiment.model.Header;
import com.adenilson.testeandroid.networking.webservices.investiment.ScreenResponse;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

public class HeaderMapper {

    public Header map(ScreenResponse screen) {
        return new Header(screen.getTitle(), screen.getFundName(), screen.getWhatIs(), screen.getDefinition());
    }
}
