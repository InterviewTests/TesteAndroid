package br.banco.services.fund.option;

import android.content.Context;
import android.util.Log;

import br.banco.services.app.alert.Alert;
import br.banco.services.app.message.IMessage;
import br.banco.services.app.message.MessageType;
import br.banco.services.fund.FundModelTemplate;

public class FundOptionPresenter {

    private String TAG = "FUND";

    private FundModelTemplate template;
    private IFundOption.ModelImpl modelImpl;
    private IFundOption.ViewImpl viewImpl;
    private Alert alert;


     FundOptionPresenter(IFundOption.ViewImpl views) {

        template = new FundModelTemplate();
        this.viewImpl = views;
        this.alert = new Alert();

    }

    //@Override
    public Context getContext() {
        return (Context) viewImpl;
    }


    public  void  drawOptionList(){

        String[][][] list;
        list = template.getListMoreInfoTemplate();

        if(list != null) {

            //viewImpl.drawOptionListView();
            viewImpl.updateOptionListView(template);

         }else{

         }

    }


    public void loadAlert(int msgCode, Context context){

        msgCode = (msgCode >= 0 && msgCode <= 5 ) ? msgCode : 0;
        viewImpl.updateAlertView(msgCode, context);

    }




}
