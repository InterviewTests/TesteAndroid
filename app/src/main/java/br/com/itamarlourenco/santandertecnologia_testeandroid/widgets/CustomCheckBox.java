package br.com.itamarlourenco.santandertecnologia_testeandroid.widgets;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.EditText;

import br.com.itamarlourenco.santandertecnologia_testeandroid.app.App;

public class CustomCheckBox extends CheckBox {
    public CustomCheckBox(Context context) {
        super(context);
        setFont();
    }

    public CustomCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }

    public CustomCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomCheckBox(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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

