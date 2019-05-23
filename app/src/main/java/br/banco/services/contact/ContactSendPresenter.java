package br.banco.services.contact;

import android.content.Context;
import android.util.Log;

import br.banco.services.app.alert.Alert;
import br.banco.services.app.message.IMessage;
import br.banco.services.app.message.MessageType;
import br.banco.services.contact.message.ContactSenderType;
import br.banco.services.contact.message.IContactMsg;


public class ContactSendPresenter {

    private Alert alert;
    private View view;

    public ContactSendPresenter(View view) {
        this.alert = new Alert();
        this.view = view;
    }

    public void loadAlertView(int msgCode, Context context){

        msgCode = (msgCode >= 0 && msgCode <= 5 ) ? msgCode : 0;

        MessageType type = MessageType.values()[msgCode];
        IMessage message = type.returnMessage();
        String[] alertValues = message.configDesign(context);

       view.updateAlertView(alertValues);

        // Log.d("CONTACT"," / loadAlertView -> SUCESS -> msgCode TESTE = " +  msgCode);
    }


    public void loadAlertContactView(int msgCode, Context context){

        msgCode = (msgCode == 0 || msgCode == 1 ) ? msgCode : 0;

        //ContactSenderType type = ContactSenderType.ERROR;
        ContactSenderType type = ContactSenderType.values()[msgCode];

        IContactMsg message = type.returnMessage();
        String[] alertValues = message.configDesign(context);

        view.updateAlertView(alertValues);
    }


    public interface View{

        boolean checkInternet();
        void drawView();
        void updateAlertView(String alertValues[]);

       // boolean sendEmailTask();

        void initProgressBar();
        void showProgressBar();
        void hideProgressBar();

    }



}
