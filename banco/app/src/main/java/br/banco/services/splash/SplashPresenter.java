package br.banco.services.splash;

import android.content.Context;

import br.banco.services.app.message.IMessage;
import br.banco.services.app.message.MessageType;


public class SplashPresenter {

    private Splash splash;
    private View view;

    public SplashPresenter(View view) {
        this.splash = new Splash();
        this.view = view;
    }

    public void loadAlertView(int msgCode, Context context){

        msgCode = (msgCode > 0 && msgCode < 5 ) ? msgCode : 0;

        MessageType type = MessageType.values()[msgCode];
        IMessage message = type.returnMessage();
        String[] alertValues = message.configDesign(context);

        view.updateSplashView(alertValues);
    }


    public interface View{

        void drawSplashView();
        void updateSplashView(String alertValues[]);

    }

}
