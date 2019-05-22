package br.banco.services.app.design;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.view.Window;
import android.view.WindowManager;

public class DrawProgressBar {




    public void initProgressBar2(Context context) {

        ProgressBar progressBar;
        int marginTop = 0;
        int marginLeft = 0;

        // size
        DisplayMetrics displayMetrics = new DisplayMetrics();
       // Display display = getWindowManager().getDefaultDisplay();

        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);


        //WindowManager window = new WindowManager();
        // ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);

       // window.getDefaultDisplay().getW
        //window.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);


    }


    public void initProgressBar(Context context) {

        int marginTop = 0;
        int marginLeft = 0;
        ProgressBar progressBar;

        // size
        DisplayMetrics displayMetrics = new DisplayMetrics();
       // getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        //this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);


        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        marginTop = (height > 0) ? (height / 2) - 80 : 0 ;
        marginLeft = (width > 0) ? (width / 2) - 80 : 0 ;

        // bar
        progressBar = new ProgressBar(context, null,
                android.R.attr.progressBarStyleSmall);
        progressBar.getIndeterminateDrawable()
                .setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
        progressBar.setIndeterminate(true);
        progressBar.setBackgroundColor(Color.TRANSPARENT);

        // layout
        RelativeLayout layout = new RelativeLayout(context);

        // params
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(160, 160);
        params.setMargins(marginLeft, marginTop, 0, 0);

       // this.addContentView(progressBar, params);
        // hideProgressBar();
    }



    public void showProgressBar(Context context, ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar(Context context,ProgressBar progressBar) {
        progressBar.setVisibility(View.INVISIBLE);
    }




}


