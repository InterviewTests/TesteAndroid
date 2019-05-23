package br.banco.services.contact;

/*
    @FAZER:  Gravar preferencias, Banco de Dados, etc
 */

import android.content.Context;

import java.util.ArrayList;

public class ContactPresenter implements IContactImpl.PresenterImpl {

    private String TAG = "CONTACT";

    private Contact contact;
    private IContactImpl.ModelImpl modelImpl;
    private IContactImpl.ViewImpl viewImpl;

    public ContactPresenter(IContactImpl.ViewImpl views) {

        modelImpl = new ContactModel();
        contact = new Contact();
        this.viewImpl = views;
    }

    public Context getContext() {
        return (Context) viewImpl;
    }

     public void sendForm(Contact contactForm){

        modelImpl.savePreferecnces(contactForm);
       // viewImpl.sendFormView(contactForm);
        viewImpl.sendFormView2(contactForm);

       // contact.showObjetc(TAG, getClass().getName() + "drawFormView" ,contactForm);
    }


}
