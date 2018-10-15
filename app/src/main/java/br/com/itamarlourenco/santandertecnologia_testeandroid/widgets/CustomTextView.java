package br.com.itamarlourenco.santandertecnologia_testeandroid.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

import br.com.itamarlourenco.santandertecnologia_testeandroid.app.App;

public class CustomTextView extends TextView {
    public CustomTextView(Context context) {
        super(context);
        setFont();
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setFont();
    }

    public void setFont(){
        if(getTypeface() != null){
            if(getTypeface().isBold()) {
                this.setTypeface(App.Fonts.bold());
            } else {
                this.setTypeface(App.Fonts.normal());
            }
        }
    }
}

