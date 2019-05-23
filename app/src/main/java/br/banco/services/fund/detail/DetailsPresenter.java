package br.banco.services.fund.detail;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;

import java.util.ArrayList;
import java.util.List;

import br.banco.services.app.utils.ReactAplication;
import br.banco.services.fund.domain.national.ScreenFund;

public class DetailsPresenter  implements IDetail.PresenterDT{

     private IDetail.ModelDT model;
     private IDetail.ViewDT views;
     ReactAplication RX = new ReactAplication();
    // private ScreenFund screenFund;

     private ArrayList<ScreenFund> listScreen= new ArrayList<>();
     //Context context;
     private int messageCode = 4; // Error

    public  DetailsPresenter(){

         model = new DetailModel(this);
        // RX.onNext("model->" + (model != null));

    }

    public void setView(IDetail.ViewDT views){ // IDetail.ViewDT views
        this.views = views;
      //  RX.onNext("DetailsPresenter-> setView->" + (views!=null));
    }

    public Context getContext() {
       // RX.onNext("DetailsPresenter-> context->" + (context!=null));
        return (Context) views;
    }

    public void decideLoadContent(Bundle savedInstanceState){ //01
        model.processStart(model.getLocation(), getContext());
    }

  // @Override
    public ArrayList<ScreenFund> getScreen()  {
        return listScreen;
    }


    public void loadConfigurations(List<Object> listScreen, String message)
    {
        RX.onNext("LITA ITENS " + listScreen.size());

        if(listScreen != null) {

            RX.onNext("LITA ITENS " + listScreen.size());

            //views.drawInterface(ScreenFund listFund);
        }else{
             int messageCode = 1;
            //views.onLoadError(int messageCode);
        }

    }

    public void describeContents (){
    }

    public void writeToParcel (Parcel dest, int flags) {

    }

    public void loadAlert(int msgCode, Context context){

       // msgCode = 3;
        RX.onNext("PRESENTER / loadAlert->Error");
        msgCode = (msgCode >= 0 && msgCode <= 5 ) ? msgCode : 0;
        views.updateAlertView(msgCode, context);

    }



}
