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
     private WeakReference<Context> contextRef;

     public ILoad.Model model;
     public ILoad.Views views;
    // public ContactForm contactForm;
     public ArrayList<ContactForm> listForm = new ArrayList<>();
     public String APLICATION_AREA = "cells";

     ReactAplication RX = new ReactAplication();
     Context context;


     public LoadPresenter(){

       model = new LoadModel(this);

     }



    /**
     *
     * carregar dados
     *
     */



    public void onLoadTask(Bundle savedInstanceState, Context c) {
        RX.onMessage(TAG, "P/onLoadTask", c );

        this.context = c;

        // task
        model.loadData(APLICATION_AREA, c);
        // ((LoadModel) model).setView(this);
       // this.context = c;
        //contextRef = new WeakReference<>(c);

      //  presenter.updateAlertView(1);



    }

    @Nullable
    public void onCompletedTask(String ouput) {

         //msgCode = 1;

        RX.onMessage(TAG, "P/onCompletedTask", context );

         //RX.onNext("[context=" + (context!=null) +"]" );
         //contextRef = new WeakReference<>(context);
         //views.updateAlertView(1, null);
         views.hideProgressBar();


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
        contextRef = new WeakReference<>(getContext());
    }

    public Context getContext() {
        return (Context) views;
    }


}
