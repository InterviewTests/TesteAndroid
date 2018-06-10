package com.adenilson.testeandroid.investiment.model.mapper;

import com.adenilson.testeandroid.investiment.model.Info;
import com.adenilson.testeandroid.networking.webservices.investiment.InfoResponse;
import com.adenilson.testeandroid.networking.webservices.investiment.ScreenResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

public class InfoMapper {

    public List<Info> mapInfoList(ScreenResponse screen) {
        ArrayList<Info> infos = new ArrayList<>();
        for (InfoResponse info : screen.getInfo()) {
            infos.add(new Info(info.getName(), info.getData()));
        }
        return infos;
    }

    public List<Info> mapDowInfoList(ScreenResponse screen) {
        ArrayList<Info> infos = new ArrayList<>();
        for (InfoResponse info : screen.getDownInfo()) {
            infos.add(new Info(info.getName(), info.getData()));
        }
        return infos;
    }
}
