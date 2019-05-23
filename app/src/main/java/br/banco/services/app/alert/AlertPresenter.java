package br.banco.services.app.alert;

import android.content.Context;

import br.banco.services.app.message.IMessage;
import br.banco.services.app.message.MessageType;


public class AlertPresenter {

    private Alert alert;
    private View view;

    public AlertPresenter(View view) {
        this.alert = new Alert();
        this.view = view;
    }

    public void loadAlertView(int msgCode, Context context){

        msgCode = (msgCode >= 0 && msgCode <= 5 ) ? msgCode : 0;

        MessageType type = MessageType.values()[msgCode];
        IMessage message = type.returnMessage();
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
