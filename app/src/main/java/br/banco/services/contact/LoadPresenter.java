package br.banco.services.contact;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import br.banco.services.app.utils.ReactAplication;
import br.banco.services.contact.domain.ContactForm;
import br.banco.services.fund.detail.IDetail;

public class LoadPresenter  implements ILoad.Presenter {

     public ILoad.Model model;
     public ILoad.Views views;
     public ContactForm contactForm;
     public ArrayList<ContactForm> listForm = new ArrayList<>();

     ReactAplication RX = new ReactAplication();


     public LoadPresenter(){

         model = new LoadModel(this);

        // RX.onNext("Iniciando...");
         //   model.processStart(model.getLocation(), getContext());
     }





    /**
     *
     * carregar dados
     *
     */



    public void onLoadData(Bundle savedInstanceState, Context context) {

        // task
        model.loadData(contactForm, context);

        //views.updateAlertView(1, context);
    }

    @Nullable
    public void onCompleted(ContactForm form, int msgCode) {


         msgCode = 1;
         if(getContext()==null){

             RX.onNext("->Vixeeeeeeeee ");
         }else{

             RX.onNext("->BOOOA! ");
         }
         //views.updateAlertView(1, null);
         //views.hideProgressBar();
    }


    public void onErrorTask(int msgCode) {

        RX.onNext("->Error");
        msgCode = (msgCode >= 0 && msgCode <= 5 ) ? msgCode : 0;
        views.updateAlertView(msgCode);

    }



    /**
     *
     *  carregar o contexto
     *
     */

    public void setView(ILoad.Views views){
        this.views = views;
    }

    public Context getContext() {
        return (Context) views;
    }


}
