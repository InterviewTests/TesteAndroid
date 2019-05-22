package br.banco.services.contact.message;

import android.content.Context;

import br.banco.services.app.alert.Alert;


public class ContactMsgPresenter {

    private Alert alert;
    private View view;

    public ContactMsgPresenter(View view) {
        this.alert = new Alert();
        this.view = view;
    }

    public void loadAlertView(int msgCode, Context context){

        msgCode = (msgCode >= 0 && msgCode < 5 ) ? msgCode : 0;

        ContactSenderType type = ContactSenderType.values()[msgCode];
        IContactMsg message = type.returnMessage();
        String[] alertValues = message.configDesign(context);

        view.updateAlertView(alertValues);
    }



    public interface View{

        void drawView();
        void updateAlertView(String alertValues[]);
        void showProgressBar();
        void hideProgressBar();

    }

}
