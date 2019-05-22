package br.banco.services.app.design;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;

import android.view.View;
import android.widget.RelativeLayout;

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


