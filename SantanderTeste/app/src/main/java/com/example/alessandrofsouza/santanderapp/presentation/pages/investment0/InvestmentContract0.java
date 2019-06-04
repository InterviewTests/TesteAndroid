package com.example.alessandrofsouza.santanderapp.presentation.pages.investment0;

import com.example.alessandrofsouza.santanderapp.domain.model.Infos;
import com.example.alessandrofsouza.santanderapp.domain.model.Screen;
import com.example.alessandrofsouza.santanderapp.presentation.pages.base.BasePresenter;
import com.example.alessandrofsouza.santanderapp.presentation.pages.base.BaseView;

import java.util.ArrayList;

public interface InvestmentContract0 {

    interface View extends BaseView<Presenter> {
        void showInvestmentScreen(Screen screen);
    }

    interface Presenter extends BasePresenter {
        void getInvestmentScreen();
    }

}
