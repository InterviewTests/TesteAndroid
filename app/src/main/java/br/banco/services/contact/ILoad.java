package br.banco.services.contact;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import br.banco.services.contact.domain.ContactForm;

public interface ILoad {


    interface Presenter {

        void onLoadTask(Bundle savedInstanceState, Context c);
        void onCompletedTask(String output) ;
        void onErrorTask(String message, int code);
    }

    interface Model{

        void loadData(ArrayList<ContactForm> list, Context c);
        //String loadData(String area, Context c);
       // void saveData(ContactForm form, Context c);
       // void clearData(ContactForm form, Context c);
        // void onCompletedData(boolean status, Context context);

         // modelo 2



    }

    interface Model2{
        void onStartLoad(Context c);
        //String loadData(String area);
        //void onCompleted(boolean status, Context context);

    }


    interface Views{

        void onErrorView(int msgCode);
        void onSuccessView(String area);
        void showProgressBar();
        void hideProgressBar();
    }




}
