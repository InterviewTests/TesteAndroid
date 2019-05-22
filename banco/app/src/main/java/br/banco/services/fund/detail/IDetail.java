package br.banco.services.fund.detail;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;


public interface IDetail {


    interface PresenterDT{

         void decideLoadContent(Bundle savedInstanceState);
         void loadConfigurations(List<Object> listScreen, String message);
         void loadAlert(int msgCode, Context context);

         //ArrayList<ScreenFund> getScreen();

    }

    interface ModelDT{

         // decide origem dos dados

        void setOrigin(int origin);
        int getOrigin();

        void setLocation(int location);
        int getLocation();

        void processStart(int location ,Context c);
        void processFinish(String location) ;

        ArrayList<DetailModel> getContainer();
        ArrayList<DetailModel> getConfigurations();


    }
    interface ViewDT{

        //data

        String KEY_AREA = "screen"; // screen

        //void onSuccess(int messageCode);
        //void onErrorLoad(int messageCode);
        void updateAlertView(int msgCode, Context c);


    }





}
