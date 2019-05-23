package br.banco.services.app.design;

import android.content.Context;
import android.view.View;

public class DrawView {





    public View initView(Context context, View view) {

     return  view;
    }



    public View showinitView(Context context, View view) {
        view.setVisibility(View.VISIBLE);
        return  view;
    }

    public View hideinitView(Context context,View view) {
        view.setVisibility(View.INVISIBLE);
        return  view;
    }




}


