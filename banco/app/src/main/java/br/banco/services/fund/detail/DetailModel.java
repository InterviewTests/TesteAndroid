package br.banco.services.fund.detail;


import android.content.Context;
import android.os.Parcel;

import java.util.ArrayList;
import java.util.List;

import br.banco.services.app.utils.ReactAplication;
import br.banco.services.fund.data.remote.ILoadFrom;
import br.banco.services.fund.data.remote.LoadFileHttpTask;
import br.banco.services.fund.domain.national.ScreenFund;
import br.banco.services.fund.domain.national.ScreenFundTemplate;
import br.banco.services.fund.interactor.ILoadTask;
import br.banco.services.fund.interactor.LoadInternet;
import br.banco.services.fund.interactor.LoadScreen;

public class DetailModel implements IDetail.ModelDT,  ILoadTask, ILoadFrom
{

    private static IDetail.PresenterDT presenter;
    private static ILoadTask task;

    // data patern

    public static final int SITE_NATIONAL = 0; //origin
    public static final int SITE_INTERNATIONAL = 1;

    public static final int LOAD_INTERNET = 0; // location
    public static final int LOAD_PREFERENCES = 1;
    public static final int LOAD_DATABASE = 2;

    public Context context;
    public static final int INTERNET_CONN_ATTEMPTS = 3;

     // design config

    public static final int FUND_TYPE = 0;
    public static final int MOREINFO_TYPE = 1;
    public static final int INFO_TYPE = 2;
    public static final int DOWNLOAD_TYPE = 3;

    public static final int HEADER_TYPE = 10;
    public static final int BODY_TYPE = 11;
    public static final int FOOTER_TYPE = 12;

    public int origin;
    public int location;

    public int type;
    public int data;
    public String text;

    public ArrayList<DetailModel> container;
    public ArrayList<DetailModel> configurations;
    public ReactAplication RX = new ReactAplication();

    public DetailModel(IDetail.PresenterDT presenter) {
        this.origin = LOAD_INTERNET;
        this.location = SITE_NATIONAL;
        this.presenter = presenter;
       // RX.onNext("MODEL->presenter->"+ (presenter!=null));

    }

    public void processStart(int location ,Context c){
        this.context = c;

       // RX.onNext("MODEL->Context->"+ (c!=null));
         LoadInternet connectInternet = new LoadInternet(c);
         connectInternet.delegate = this;
         connectInternet.execute(""); //INTERNET_CONN_ATTEMPTS
        //RX.onNext("carregar da web");
    }

    public void processFinish(String output){
       // RX.onNext("processFinish->context->" + (context!=null));

         if(output.equals("success")) {
             RX.onNext("MODEL / carregar internet..." + output);

             // LoadFileHttpTask loaadHttp = new LoadFileHttpTask(context);
           //  loaadHttp.delegate = this;
           //  loaadHttp.execute("");

             LoadScreen loadScreen = new LoadScreen(context);
             loadScreen.delegate = this;
             loadScreen.execute("");


         }else{
            // RX.onNext("MODEL / Carregando dados locais...");

             LoadScreen loadScreen = new LoadScreen(context);
             loadScreen.delegate = this;
             loadScreen.execute("");
         }
    }

    public void convertFinish(List<Object> listScreen, String message){

        if(listScreen != null) {
            RX.onNext("MODEL / convertFinish->Sucess");
            // RX.onNext("MODEL / loadConfigurations->Sucess");
           // presenter.loadConfigurations(listScreen, message);


        }else{
             RX.onNext("MODEL / convertFinish->Error");
            // presenter.loadAlert(4, context);
        }

    }


    // ------------------- config data -----------------------

    //@Override


    public void describeContents (){

    }

    public void writeToParcel (Parcel dest, int flags) {

    }

    // --------------


    public int decideLoadData(int decide){
        decide = (decide < 0 || decide > 2  )  ? origin : decide ;
        return  decide;
    }

    // origem dos dados

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {

         origin = (origin < 0 || origin > 2  )  ? 0 : origin ;

        int dataSource[] = {
                LOAD_INTERNET,
                LOAD_PREFERENCES,
                LOAD_DATABASE
        };

        this.origin = dataSource[origin];
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {

        location = (location < 0 || location > 1) ? 0 : location ;

        int dataSource[] = {
                SITE_NATIONAL,
                SITE_INTERNATIONAL
        };

        this.location = dataSource[location];
    }

    // more







    // conteudo

    public ArrayList<DetailModel> getContainer() {
        return container;
    }

    public void setContainer(ArrayList<DetailModel> container) {
        this.container = container;
    }

    //configuracoes

    public ArrayList<DetailModel> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(ArrayList<DetailModel> configurations) {
        this.configurations = configurations;
    }
    //testes

    // ------------------- config data -----------------------


}
