package br.com.itamarlourenco.santandertecnologia_testeandroid.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.CheckBox;

import br.com.itamarlourenco.santandertecnologia_testeandroid.app.App;

public class CustomButton extends Button {
    public CustomButton(Context context) {
        super(context);
        setFont();
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setFont();
    }

    public void setFont(){
        if(getTypeface() != null){
            if(getTypeface().isBold()) {
                this.setTypeface(App.Fonts.normal());
            } else {
                this.setTypeface(App.Fonts.normal());
            }
        }
    }
}

