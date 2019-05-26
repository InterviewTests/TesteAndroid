package br.banco.services.contact;

import android.content.Context;
import android.os.Bundle;

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


     public  LoadPresenter(){

         model = new LoadModel(this);

         RX.onNext(" UMMM " );
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

    public void onSuccessLoad(ContactForm form, int msgCode) {

        //views.updateAlertView(1, context);
    }


    public void onErrorLoad(Context context, int msgCode) {

        RX.onNext("->Error");
        msgCode = (msgCode >= 0 && msgCode <= 5 ) ? msgCode : 0;
        views.updateAlertView(msgCode, context);

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
