package br.banco.services.contact;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import br.banco.services.app.utils.ReactAplication;
import br.banco.services.contact.domain.ContactForm;

public class LoadPresenter  implements ILoad.Presenter {

    public final String TAG = "LOADR";
    // private WeakReference<Context> contextRef;

     public ILoad.Model2 model;
     public ILoad.Views views;

     public ArrayList<ContactForm> listForm = new ArrayList<>();
     public String APLICATION_AREA = "cells";

     ReactAplication RX = new ReactAplication();
     Context context;


     public LoadPresenter(){

        model = new LoadModel2(this);
       // this.context =

     }



    /**
     *
     * carregar dados
     *
     */



    public void onLoadTask(Bundle savedInstanceState, Context c) {

        this.context = c;
        RX.onMessage(TAG, "P/onLoadTask/context", context);
        model.onStartLoad(c);
    }

    @Nullable
    public void onCompletedTask(String ouput) {


        if(views!=null){
            RX.onMessage(TAG, "P/onCompletedTask/SUCESSO/", (context) );
            views.onSuccessView(ouput);
        }else{
            RX.onMessage(TAG, "P/onCompletedTask/ERRO/", (context) );
        }

    }


    public void onErrorTask(String message, int msgCode) {

        //RX.onNext("->Error");
         msgCode = 0;

        msgCode = (msgCode >= 0 && msgCode < 6 ) ? msgCode : 0;
        views.onErrorView(msgCode);

    }


    /**
     *
     *  carregar o contexto
     *
     */

    public void setView(ILoad.Views views){
        this.views = views;
       // contextRef = new WeakReference<>(getContext());
    }

    public Context getContext() {
        return (Context) views;
    }


}
