package br.banco.services.app.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

public final class ScreenSizes extends Activity {

    private  int width = 0;
    private  int height = 0;

    //Activity activity = new Activity().getParent();

    public ScreenSizes(Activity activity,Context context){

        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        //((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.height = displayMetrics.heightPixels;
        this.width = displayMetrics.widthPixels;

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


}
