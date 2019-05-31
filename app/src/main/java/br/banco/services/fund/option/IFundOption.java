package br.banco.services.fund.option;

import android.content.Context;

import br.banco.services.fund.FundModelTemplate;

public interface IFundOption {


    interface PresenterImpl{

        void loadAlert(int msgCode, Context context);
       // void  checkOptionList();
        //void checkOptionList();
    }
    interface ModelImpl{

       // void saveOptionListModel();
        //void loadOptionListModel();
    }
    interface ViewImpl{

        boolean checkInternet();
        void drawOptionListView();
        void updateOptionListView(FundModelTemplate fund);

        void updateAlertView(int msgCode, Context context);
    }




}
