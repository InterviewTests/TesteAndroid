package com.aline.teste.MVP.presenter;

import com.aline.teste.MVP.MVP;
import com.aline.teste.MVP.model.ModelFundInfo;
import com.aline.teste.Models.Screen;
import com.aline.teste.eventbus.EventFund;

import org.greenrobot.eventbus.EventBus;

import java.util.List;



public class PresenterFund implements MVP.PresenterFund {

    private MVP.ModelFund model;

    public PresenterFund(){
        model = new ModelFundInfo(this);
    }

    @Override
    public void callNetworkFund() {
        model.callRetrofitFund();
    }

    @Override
    public void updateFund(Screen screen) {
        EventBus.getDefault().post(new EventFund(screen));
    }
}
