package com.carpinelli.testeandroid.ui.invest;

import com.carpinelli.testeandroid.model.Info;
import com.carpinelli.testeandroid.model.MoreInfo;
import com.carpinelli.testeandroid.model.Screen;
import com.carpinelli.testeandroid.ui.base.Mvp;

import java.util.List;

public interface MvpInvest {

    interface View extends Mvp.View {

        void onScreenReady(Screen screen);

    }

    interface Presenter extends Mvp.Presenter {
        
        void onStart();

        android.view.View.OnClickListener onInvestment();

    }

}
